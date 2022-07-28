package com.seniah.news.data.models

import androidx.annotation.Keep

@Keep
class NewsResponse(
    val articles: List<Articles>
) {
    @Keep
    class Articles(
        val author: String?,
        val urlToImage: String?,
        val source: Source?,
        val publishedAt: String?,
        val url: String?
    )

    @Keep
    class Source(
        val Name: String?
    )
}
