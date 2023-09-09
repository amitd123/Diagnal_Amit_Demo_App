import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.diagnal.models.VideoDetail
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.diagnal.MainActivity
import com.diagnal.R
import com.diagnal.presentation.VideoRowColumn
import com.diagnal.presentation.normalTopAppBar
import com.diagnal.presentation.searchTopAppBar
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoDetailScreen(
    videoDetails: Flow<PagingData<VideoDetail>>,
    videoSearchDetails: Flow<PagingData<VideoDetail>>,
    searchStringState: MutableState<String>,
    searchState: MutableState<Boolean>,
    searchTextState: MutableState<String>,
    mainActivity: MainActivity
){
    var isSearchActive = remember { searchState }
    val activeFlow: Flow<PagingData<VideoDetail>> = when (isSearchActive.value) {
        true -> videoSearchDetails
        false -> {
            searchStringState.value = ""
            videoDetails
        }
    }
    val lazyPagingItems: LazyPagingItems<VideoDetail> = activeFlow.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            Image(
                painter = painterResource(R.drawable.nav_bar),
                contentDescription = "Back",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillBounds,
            )

            if (isSearchActive.value) {
                searchTopAppBar(
                    onBackClick = { isSearchActive.value = false },
                    onSearchTextChange = { searchText ->
                        searchStringState.value = searchText
                        // Perform search or update search results based on the search text
                    },
                    searchTextState = searchTextState
                )
            } else {
                // handled on back press from home screen where list of movies shown
                normalTopAppBar(
                    onSearchClick = { isSearchActive.value = true },
                    onBackIconPress = {
                        mainActivity.onBackPressed()
                    }
                )
            }
        },

        // loading list of items in screen
        content = {
            VideoRowColumn(videoDetails = lazyPagingItems,searchStringState,it)
        }
    )
}