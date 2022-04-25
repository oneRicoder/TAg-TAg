package com.example.tagtag.json

data class VideoDetails(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val pageInfo: PageInfo
)