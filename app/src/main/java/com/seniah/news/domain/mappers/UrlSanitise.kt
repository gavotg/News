package com.seniah.news.domain.mappers

import javax.inject.Inject

private const val JPG = ".jpg"
private const val JPEG = ".jpeg"
private const val PNG = ".png"

class UrlSanitise @Inject constructor() {
    operator fun invoke(url: String?): String =
        when {
            url.isNullOrEmpty() -> ""
            url.endsWith(JPG) || url.endsWith(PNG) || url.endsWith(JPEG) -> url
            url.contains(PNG) -> url.substringBeforeLast(PNG) + PNG
            url.contains(JPG) -> url.substringBeforeLast(JPG) + JPG
            url.contains(JPEG) -> url.substringBeforeLast(JPEG) + JPEG
            else -> url
        }
}
