package com.diagnal.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.diagnal.models.VideoDetail
import com.diagnal.ui.theme.LightGray80
import com.diagnal.ui.theme.titilliumWebFont
import com.diagnal.utils.getImageDrawableByName
import com.diagnal.utils.pixToDp
/**
 *  item in movie list
 * */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun videoItem(videoDetails: LazyPagingItems<VideoDetail>,index:Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                15.pixToDp(),
                0.dp,
                15.pixToDp(),
                90.pixToDp()
            )){
        Image(
            painter = painterResource(getImageDrawableByName(LocalContext.current,videoDetails[index]?.posterImage)),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(24.pixToDp()).padding(0.dp))
        Text(
            modifier = Modifier.padding(0.dp).basicMarquee(),
            color = LightGray80,
            text = videoDetails[index]?.name ?: "",
            maxLines = 1,
            overflow = TextOverflow.Visible,
            style = MaterialTheme.typography.titleMedium.merge(
                TextStyle(fontFamily = titilliumWebFont)
            ))

    }
}