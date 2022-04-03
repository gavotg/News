package com.seniah.news.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seniah.news.domain.NewsRepository
import com.seniah.news.helper.ConnectivityHelper
import com.seniah.news.helper.CoroutineContextDispatcher
import com.seniah.news.helper.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val logger: Logger,
    private val coroutineDispatcher: CoroutineContextDispatcher,
    private val connectivityHelper: ConnectivityHelper,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsUIState())
    val uiState: StateFlow<NewsUIState> = _uiState

    fun getNetwork() {
        connectivityHelper(object : ConnectivityHelper.NetworkListener {
            override fun available() {
                getNews()
            }

            override fun error() {
                logger.log("No internet")
            }
        })
    }

    private fun getNews() {
        viewModelScope.launch(coroutineDispatcher.io) {
            newsRepository.latestNews
                .catch { exception ->
                    _uiState.value =
                        NewsUIState(errorMessage = exception.localizedMessage.orEmpty())
                }
                .collect { latest ->
                    _uiState.value = NewsUIState(list = latest)
                }
        }
    }

    fun urlShow(url: String) {
        _uiState.update { currentUiState ->
            currentUiState.copy(showUrl = url)
        }
    }

    fun urlShown() {
        _uiState.update { currentUiState ->
            currentUiState.copy(showUrl = "")
        }
    }

    fun errorShown() {
        _uiState.update { currentUiState ->
            currentUiState.copy(errorMessage = "")
        }
    }
}
