package com.example.tagtag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tagtag.json.VideoDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getJsonDataUsingRetrofit()
    }

    private fun getJsonDataUsingRetrofit(){
        val retrofit = Retrofit.Builder().baseUrl("https://youtube.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service: CallApi = retrofit.create<CallApi>(CallApi::class.java)
        val listCall: Call<VideoDetails> = service.callVideoApi()
        listCall.enqueue(object : Callback<VideoDetails> {
            override fun onResponse(call: Call<VideoDetails>, response: Response<VideoDetails>) {
                if (response.isSuccessful){
                    val check : VideoDetails = response.body() as VideoDetails
                    Log.i("success_success",check.items[0].snippet.tags.toString())
                }
            }

            override fun onFailure(call: Call<VideoDetails>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun videoId() : String {
        val result: String = "2k7aHIDQc1s"
        return result
    }
}