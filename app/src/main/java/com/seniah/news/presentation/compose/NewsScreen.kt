package com.seniah.news.presentation.compose

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.seniah.news.domain.models.Article
import com.seniah.news.presentation.MainViewModel
import com.seniah.news.presentation.NewsUIState
import com.seniah.news.ui.theme.NewsTheme

@Composable
fun NewsScreen(viewModel: MainViewModel) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val newsFlowLifecycleAware = remember(viewModel.uiState, lifecycleOwner) {
        viewModel.uiState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val uiState by newsFlowLifecycleAware.collectAsState(NewsUIState())

    NewsList(uiState.list) { viewModel.urlShow(it) }

    if (uiState.errorMessage.isNotEmpty()) {
        ShowError(uiState.errorMessage)
        viewModel.errorShown()
    }
}

@Composable
fun ShowError(errorText: String) {
    Toast.makeText(
        LocalContext.current,
        errorText,
        Toast.LENGTH_LONG
    ).show()
}

@Composable
fun NewsList(articles: List<Article>, onItemClick: ((String) -> Unit)) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { HeaderView() }
        items(articles) {
            ArticleView(it, onItemClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsTheme {
        NewsList(
            listOf(previewArticle, previewArticle, previewArticle)
        ) {}
    }
}

val previewArticle = Article(
    title = "This is the title",
    date = "2022-03-31T14:16:00Z",
    source = "BBC",
    imageUrl = "image"
)
