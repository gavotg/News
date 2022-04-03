package com.seniah.news

import com.seniah.news.helper.relativeTime
import org.junit.Assert.assertEquals
import org.junit.Test

class NewsUnitTest {
    @Test
    fun time_format_wrong() {
        assertEquals("wrong formatting".relativeTime(), "")
    }

    @Test
    fun time_format_null() {
        assertEquals(null.relativeTime(), "")
    }
}
