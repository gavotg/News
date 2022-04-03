package com.seniah.news.domain

import com.seniah.news.data.NewsApi
import com.seniah.news.domain.mappers.ArticleMapper
import com.seniah.news.domain.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi,
    private val mapper: ArticleMapper
) {
    val latestNews: Flow<List<Article>> = flow {
        val result = mapper(api.getTopHeadlines(country = "us").articles)
        emit(result)
    }
}
