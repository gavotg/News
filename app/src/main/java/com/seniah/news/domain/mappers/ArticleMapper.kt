package com.seniah.news.domain.mappers

import com.seniah.news.data.models.NewsResponse
import com.seniah.news.domain.models.Article
import com.seniah.news.helper.Logger
import javax.inject.Inject

class ArticleMapper @Inject constructor(
    private val logger: Logger,
    private val urlSanitise: UrlSanitise
) {
    operator fun invoke(network: List<NewsResponse.Articles>): List<Article> =
        network.map { result ->
            logger.log("B4: " + result.urlToImage.orEmpty())
            logger.log("AF: " + urlSanitise(result.urlToImage))
            Article(
                title = result.author.orEmpty(),
                imageUrl = urlSanitise(result.urlToImage),
                date = result.publishedAt.orEmpty(),
                source = result.source?.Name.orEmpty(),
                url = result.url.orEmpty()
            )
        }
}
