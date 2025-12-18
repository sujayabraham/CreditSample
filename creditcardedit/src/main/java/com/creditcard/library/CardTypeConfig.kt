package com.creditcard.library

data class CardTypeConfig(
    val name: String,
    val regex: String,
    val logoRes: Int, // Resource ID from the app
    val maxLength: Int = 16
)
