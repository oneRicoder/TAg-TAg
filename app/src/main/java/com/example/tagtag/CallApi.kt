package com.example.tagtag

import com.example.tagtag.json.VideoDetails
import retrofit2.Call
import retrofit2.http.GET

interface CallApi {
    @GET(Constants.url)
    fun callVideoApi(): Call<VideoDetails>
}