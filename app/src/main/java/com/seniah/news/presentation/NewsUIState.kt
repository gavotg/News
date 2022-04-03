package com.seniah.news.presentation

import com.seniah.news.domain.models.Article

data class NewsUIState(
    val list: List<Article> = emptyList(),
    val showUrl: String = "",
    val errorMessage: String = ""
)
