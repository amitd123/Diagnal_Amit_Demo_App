package com.diagnal.models

import com.google.gson.annotations.SerializedName

data class PageDetail (

    @SerializedName("title"               ) var title               : String?           = null,
    @SerializedName("total-content-items" ) var totalContentItems   : String?           = null,
    @SerializedName("page-num"            ) var pageNum             : String?           = null,
    @SerializedName("page-size"           ) var pageSize            : String?           = null,
    @SerializedName("content-items"       ) var contentItems        : ContentItems?     = ContentItems()

)

data class Page (
    @SerializedName("page") var pageDetail : PageDetail? = null
)