package com.diagnal.presentation

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.diagnal.local.LocalVideoSearchSource
import com.diagnal.models.VideoDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * viewmodel for preparing and managing the data
 * */
@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext context: Context,pager: Pager<Int, VideoDetail>) : ViewModel() {

    val videoPagingFlow  = pager.flow.cachedIn(viewModelScope)

    private val _searchStringState = mutableStateOf("")
    val searchStringState: MutableState<String> = _searchStringState

    private val _searchState = mutableStateOf(false)
    val searchState: MutableState<Boolean> = _searchState

    private val _searchTextState = mutableStateOf("")
    val searchTextState: MutableState<String> = _searchTextState

    private lateinit var pagingSourceSearch : LocalVideoSearchSource

    val videoSearchPagingFlow = Pager(PagingConfig(pageSize = 20)) {
        val query = when(_searchStringState.value.length>=3){
            true -> _searchStringState.value
            false -> ""
        }
        LocalVideoSearchSource(context = context,query ).also { pagingSourceSearch = it }
    }.flow

    init {
        snapshotFlow { searchStringState.value }
            .onEach {
                if(!it.isNullOrEmpty()){
                    pagingSourceSearch.invalidate()
                }
                _searchStringState.value = it
            }
            .launchIn(viewModelScope)
    }
}