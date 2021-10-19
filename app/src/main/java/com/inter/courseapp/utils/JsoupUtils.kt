package com.inter.courseapp.utils

import org.jsoup.Jsoup

fun fromHtmlToText(text: String): String =
    Jsoup.parse(text).text()