package com.dzakdzaks.github_api.common

import android.content.Context
import android.widget.Toast
import java.util.regex.Matcher
import java.util.regex.Pattern


fun Context.toast(message: String) =
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()


fun extractUrls(text: String): List<String>? {
    val containedUrls: MutableList<String> = ArrayList()
    val urlRegex =
        "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?+-=\\\\.&]*)"
    val pattern: Pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE)
    val urlMatcher: Matcher = pattern.matcher(text)
    while (urlMatcher.find()) {
        containedUrls.add(
            text.substring(
                urlMatcher.start(0),
                urlMatcher.end(0)
            )
        )
    }
    return containedUrls
}

fun sinceUsersCreator(data: String): String {
    return "users?" + extractUrls(data)?.get(0)?.substringAfter("?")
}
