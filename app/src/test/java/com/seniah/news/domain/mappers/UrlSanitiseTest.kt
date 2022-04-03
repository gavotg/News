package com.seniah.news.domain.mappers

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class UrlSanitiseTest(
    private val serverUrl: String,
    private val parsedUrl: String
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "Given before={0}, after={1}")
        fun data(): Collection<Array<*>> = listOf(
            arrayOf(
                "https://thehill.com/wp-content/uploads/sites/2/2022/04/ukraine-soldiers_kyiv_040122ap-rodrigo-abd_putins-war.jpg?w=1280",
                "https://thehill.com/wp-content/uploads/sites/2/2022/04/ukraine-soldiers_kyiv_040122ap-rodrigo-abd_putins-war.jpg"
            ),
            arrayOf(
                "https://static.independent.co.uk/2022/03/31/01/OSCAR-WILL_SMITH_26446.jpg?quality=75&width=1200&auto=webp",
                "https://static.independent.co.uk/2022/03/31/01/OSCAR-WILL_SMITH_26446.jpg"
            ),
            arrayOf(
                "https://a.espncdn.com/combiner/i?img=%2Fphoto%2F2022%2F0402%2Fr994266_1296x729_16%2D9.jpg",
                "https://a.espncdn.com/combiner/i?img=%2Fphoto%2F2022%2F0402%2Fr994266_1296x729_16%2D9.jpg"
            ),
            arrayOf(
                "https://static.independent.co.uk/2022/04/01/19/SEI96598468.jpg?quality=75&width=1200&auto=webp",
                "https://static.independent.co.uk/2022/04/01/19/SEI96598468.jpg"
            ),
            arrayOf(
                "https://variety.com/wp-content/uploads/2022/04/Morbius.jpg?w=1024",
                "https://variety.com/wp-content/uploads/2022/04/Morbius.jpg"
            ),
            arrayOf(
                "https://d.newsweek.com/en/full/1887860/anthony-sabatini-florida-rep-trump-audit-election.png",
                "https://d.newsweek.com/en/full/1887860/anthony-sabatini-florida-rep-trump-audit-election.png"
            ),
            arrayOf(
                "https://img.ksl.com/slc/2870/287085/28708517.jpg?filter=kslv2/responsive_story_lg",
                "https://img.ksl.com/slc/2870/287085/28708517.jpg"
            ),
            arrayOf(
                "",
                ""
            )
        )
    }

    private lateinit var classUnitTest: UrlSanitise

    @Before
    fun setUp() {
        classUnitTest = UrlSanitise()
    }

    @Test
    fun `Sanitise url parses image urls stripping off sizing and formatting parameters leaving jpg or png so image loading works`() {
        val actualValue = classUnitTest(serverUrl)
        assertEquals(parsedUrl, actualValue)
    }
}
