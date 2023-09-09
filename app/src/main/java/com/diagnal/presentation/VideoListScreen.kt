package com.diagnal.presentation

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.diagnal.models.VideoDetail
import com.diagnal.ui.theme.LightGray80
import com.diagnal.ui.theme.titilliumWebFont
import com.diagnal.utils.getImageDrawableByName
import com.diagnal.utils.pixToDp
/**
showing list of items in screen
*/
@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun VideoRowColumn(
    videoDetails: LazyPagingItems<VideoDetail>,
    searchStringState: MutableState<String>,
    contentPadding: PaddingValues
) {
    val configuration = LocalConfiguration.current
    val numColumns =
        if (videoDetails.itemCount == 0 && searchStringState.value.length>=3) 1 else
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 7 else 3
    val contentPadding = PaddingValues(15.pixToDp(),contentPadding.calculateTopPadding()+36.pixToDp(),15.pixToDp(),0.dp)

    val context = LocalContext.current
    LaunchedEffect(key1 = videoDetails.loadState) {
        if(videoDetails.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (videoDetails.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                keyboardController?.hide()
                return Offset.Zero
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize().nestedScroll(nestedScrollConnection)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(numColumns),
            contentPadding = contentPadding,
            modifier = Modifier.background(Color.Black)
        ) {
            if (videoDetails.itemCount == 0 && searchStringState.value.length>=3) {
                item {
                    // Display "not found" message
                    Text(
                        text = "No results found",
                        color = Color.White,
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
                return@LazyVerticalGrid
            }
            items(videoDetails.itemCount) { index ->
                videoItem(videoDetails,index)
            }
        }
    }
}