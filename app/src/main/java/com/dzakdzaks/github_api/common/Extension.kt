package com.dzakdzaks.github_api.common

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import timber.log.Timber
import java.util.regex.Matcher
import java.util.regex.Pattern


fun Context.toast(message: String) =
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()


fun extractUrls(text: String): List<String>? {
    val containedUrls: MutableList<String> = ArrayList()
    /** Can use custom regex or default pattern*/
    val urlRegex =
        "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?+-=\\\\.&]*)"
    val pattern: Pattern = Pattern.compile(Patterns.WEB_URL.pattern(), Pattern.CASE_INSENSITIVE)
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
    Timber.d("wakwaw: ${extractUrls(data)?.get(0)}")
    return "users?" + extractUrls(data)?.get(0)?.substringAfter("?")
}
