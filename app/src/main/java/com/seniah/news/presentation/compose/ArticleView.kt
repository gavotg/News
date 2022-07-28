package com.seniah.news.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.seniah.news.domain.models.Article
import com.seniah.news.helper.relativeTime
import news.R

@Composable
fun ArticleView(article: Article, onItemClick: ((String) -> Unit)) {
    Card(
        modifier = Modifier
            .clickable(onClick = { onItemClick(article.url) })
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = colors.surface
    ) {
        Row {
            ImageView(item = article.imageUrl)
            Column(
                modifier = Modifier.padding(12.dp, 4.dp, 8.dp, 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = article.title,
                    style = typography.subtitle1
                )
                Text(
                    text = article.source,
                    style = typography.caption,
                    color = colors.primary
                )
                Text(
                    text = article.date.relativeTime(),
                    style = typography.caption
                )
            }
        }
    }
}

@Composable
fun ImageView(item: String) {
    val thirdOfScreenWidth = LocalConfiguration.current.screenWidthDp / 3
    if (item.isNotEmpty()) {
        Image(
            modifier = Modifier
                .width(thirdOfScreenWidth.dp)
                .aspectRatio(1f),
            painter = rememberImagePainter(
                data = item,
                builder = {
                    transformations(RoundedCornersTransformation(2f))
                    crossfade(true)
                }
            ),
            contentDescription = null
        )
    } else {
        Image(
            modifier = Modifier
                .width(thirdOfScreenWidth.dp)
                .aspectRatio(1.3f),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun ArticleViewPreview() {
    ArticleView(previewArticle) {}
}
