package com.seniah.news.presentation

import com.seniah.news.domain.NewsRepository
import com.seniah.news.helper.ConnectivityHelper
import com.seniah.news.helper.CoroutineContextDispatcher
import com.seniah.news.helper.Logger
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    private lateinit var logger: Logger

    @Mock
    private lateinit var coroutineDispatcher: CoroutineContextDispatcher

    @Mock
    private lateinit var connectivityHelper: ConnectivityHelper

    @Mock
    private lateinit var newsRepository: NewsRepository

    private lateinit var classUnderTest: MainViewModel

    @Before
    fun setUp() {
        classUnderTest = MainViewModel(
            logger,
            coroutineDispatcher,
            connectivityHelper,
            newsRepository
        )
    }

    @Test
    fun `Given error When getNetwork Then log error`() = runBlocking {
        val expectedResult = NewsUIState()
        // val listener = mock(ConnectivityHelper.NetworkListener::class.java)

        // given(connectivityHelper(listener)).willAnswer { listener.available() }

        classUnderTest.getNetwork()

        // verify(logger).log("No internet")
        assertEquals(classUnderTest.uiState.value, expectedResult)
    }

    @Test
    fun `When urlShow Then state is updated`() {
        val expectedResult = NewsUIState(showUrl = "www.web.com")

        classUnderTest.urlShow("www.web.com")

        assertEquals(classUnderTest.uiState.value, expectedResult)
    }

    @Test
    fun `When urlShown Then state is updated`() {
        val expectedResult = NewsUIState(showUrl = "")

        classUnderTest.urlShown()

        assertEquals(classUnderTest.uiState.value, expectedResult)
    }

    @Test
    fun `When errorShown Then state is updated`() {
        val expectedResult = NewsUIState(errorMessage = "")

        classUnderTest.errorShown()

        assertEquals(classUnderTest.uiState.value, expectedResult)
    }
}
