package com.example.tagtag

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tagtag.json.VideoDetails
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private var VIDEO_ID: String = ""
    private var URl = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        search_btn.setOnClickListener {
            URl = editText.text.toString()
            VIDEO_ID = getYouTubeId(URl)
            Log.i("foundndnd",VIDEO_ID)
            if (VIDEO_ID.isEmpty() || VIDEO_ID.equals("error")){
                Toast.makeText(this, "Video Id Not Found!", Toast.LENGTH_SHORT).show()
            }else{
                getJsonDataUsingRetrofit(VIDEO_ID)
            }


        }


    }

    private fun getYouTubeId(youTubeUrl: String): String {
        val pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
        val compiledPattern = Pattern.compile(pattern)
        val matcher = compiledPattern.matcher(youTubeUrl)
        return if (matcher.find()) {
            matcher.group()
        } else {
            "error"
        }
    }

    private fun getJsonDataUsingRetrofit(videoId: String){
        val retrofit = Retrofit.Builder().baseUrl("https://youtube.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service: CallApi = retrofit.create(CallApi::class.java)
        val listCall: Call<VideoDetails> = service.callVideoApi("snippet", videoId,"AIzaSyDcMzWLqlRMYqH3LaYih3ykwp25zcvhVA0")
        listCall.enqueue(object : Callback<VideoDetails> {
            override fun onResponse(call: Call<VideoDetails>, response: Response<VideoDetails>) {
                if (response.isSuccessful){
                    val check : VideoDetails = response.body() as VideoDetails
                    showTagsInRecyclerView(check.items[0].snippet.tags as ArrayList<String>)
                }
            }

            override fun onFailure(call: Call<VideoDetails>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Sorry Something Went Wrong!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showTagsInRecyclerView(tagList: ArrayList<String>){
        if (tagList.isNullOrEmpty()){
            Log.i("success_success","No Tags Available")
        }else{
            for (i in tagList){
                Log.i("success_success",i)
            }
        }
    }
}