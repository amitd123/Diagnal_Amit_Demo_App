package com.diagnal.local

import android.content.Context
import android.icu.text.StringSearch
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.diagnal.models.Page
import com.diagnal.models.VideoDetail
import com.diagnal.utils.JsonSourceNames
import com.diagnal.utils.readJSONFromAsset
import com.google.gson.Gson
import java.io.InputStream

/**
 * searching movies in list
 * */
class LocalVideoSearchSource constructor(private val context: Context, private val searchString: String) : PagingSource<Int, VideoDetail>(){

    private val videoList : ArrayList<VideoDetail>? by lazy {
        var list : ArrayList<VideoDetail> = ArrayList()
        JsonSourceNames.forEach {
            list.addAll(readJSONFromAsset(it,context) ?: emptyList())
        }
        return@lazy list
    }

    override fun getRefreshKey(state: PagingState<Int, VideoDetail>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoDetail> {
        return try {

            val requestedPage = params.key ?: 0

            val response = when(searchString.isNullOrEmpty()) {
                true -> emptyList()
                false -> searchNames(searchString) ?: emptyList()
            }

            val startIndex = params.loadSize * requestedPage
            val endIndex = minOf(startIndex + params.loadSize, response.size)
            val data = response.subList(startIndex, endIndex)

            // Determine the previous and next page keys
            val prevKey = if (requestedPage > 0) requestedPage - 1 else null
            val nextKey = if (endIndex < response.size) requestedPage + 1 else null

            return LoadResult.Page(data, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun searchNames(query: String): List<VideoDetail>? {

        val lowercaseQuery = query.toLowerCase().trim()

        return (videoList ?: emptyList()).map { item ->
            val name = (item.name ?: "").toLowerCase()
            if(name.startsWith(lowercaseQuery))
                Pair(item,3)
            else if(lowercaseQuery in name)
                Pair(item,2)
            else if(name.contains(lowercaseQuery))
                Pair(item,1)
            else Pair(item,0)
        }.filter { it.second > 0 } // Filter based on a threshold for relevance
            .sortedBy { it.second } // Sort by search score (lower score indicates higher relevance)
            .map { it.first }
    }
}