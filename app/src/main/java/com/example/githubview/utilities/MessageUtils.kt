package com.example.githubview.utilities

sealed class MessageUtils {
    class ErrorMessage(val errorString: String) : MessageUtils()
}