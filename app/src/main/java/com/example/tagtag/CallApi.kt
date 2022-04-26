package com.example.tagtag

import com.example.tagtag.json.VideoDetails
import retrofit2.Call
import retrofit2.http.GET

interface CallApi {
    @GET("youtube/v3/videos?part=snippet&id=2k7aHIDQc1s&key=AIzaSyDcMzWLqlRMYqH3LaYih3ykwp25zcvhVA0")
    fun callVideoApi(): Call<VideoDetails>
}