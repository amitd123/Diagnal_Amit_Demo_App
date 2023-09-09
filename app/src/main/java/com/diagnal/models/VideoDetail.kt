package com.diagnal.models

import com.google.gson.annotations.SerializedName

data class VideoDetail(
    @SerializedName("name"         ) var name           : String? = null,
    @SerializedName("poster-image" ) var posterImage    : String? = null
)