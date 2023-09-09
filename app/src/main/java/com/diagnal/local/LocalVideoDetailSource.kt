package com.diagnal.local

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.diagnal.models.Page
import com.diagnal.models.VideoDetail
import com.diagnal.utils.JsonSourceNames
import com.diagnal.utils.readJSONFromAsset
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStream
import javax.inject.Inject

class LocalVideoDetailSource @Inject constructor(@ApplicationContext val context:Context) : PagingSource<Int, VideoDetail>(){

    override fun getRefreshKey(state: PagingState<Int, VideoDetail>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoDetail> {
        return try {
            val position = params.key ?: 1
            val response = readJSONFromAsset(JsonSourceNames[position-1],context)
            return LoadResult.Page(
                data = response!!.toList(),
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == 3) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}