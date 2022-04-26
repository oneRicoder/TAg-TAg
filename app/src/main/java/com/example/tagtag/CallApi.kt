package com.example.tagtag

import com.example.tagtag.json.VideoDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CallApi {
    @GET("youtube/v3/videos")
    fun callVideoApi(
        @Query("part") part: String,
        @Query("id") id: String,
        @Query("key") apiKey: String
    ): Call<VideoDetails>
}