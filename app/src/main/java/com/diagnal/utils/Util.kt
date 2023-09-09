package com.diagnal.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.diagnal.R
import com.diagnal.models.Page
import com.diagnal.models.VideoDetail
import com.google.gson.Gson
import java.io.InputStream

fun getImageDrawableByName(context: Context, drawableName: String?): Int {
    val resources = context.resources
    val resourceId = resources.getIdentifier(
        (drawableName ?: "placeholder_for_missing_posters").split('.')[0],
        "drawable",
        context.packageName
    )
    return if (resourceId != 0) {
        resourceId
    } else {
        R.drawable.placeholder_for_missing_posters
    }
}

val JsonSourceNames = arrayOf(
    "CONTENTLISTINGPAGE-PAGE1",
    "CONTENTLISTINGPAGE-PAGE2",
    "CONTENTLISTINGPAGE-PAGE3"
)

fun readJSONFromAsset(assetName:String,context: Context): ArrayList<VideoDetail>? {
    var data: ArrayList<VideoDetail>? = null
    try {
        val  inputStream: InputStream = context.assets.open("$assetName.json")
        val json = inputStream.bufferedReader().use{it.readText()}
        val page = Gson().fromJson(json, Page::class.java)
        data = page?.pageDetail?.contentItems?.content
    } catch (ex: Exception) {
        ex.printStackTrace()
        return null
    }
    return data
}