package com.creditcard.library

import androidx.annotation.DrawableRes

enum class CreditCardType(
    val cardName: String,
    val regex: Regex,
    @DrawableRes val icon: Int
) {
    VISA("Visa", Regex("^4[0-9]*$"), R.drawable.visa),
    MASTER_CARD("MasterCard", Regex("^5[1-5][0-9]*$"), R.drawable.mastercard),
    AMERICAN_EXPRESS("American Express", Regex("^3[47][0-9]*$"), R.drawable.amex),
    UNKNOWN("Unknown", Regex(""), R.drawable.credit_card);

    companion object {
        fun detect(number: String): CreditCardType {
            return values().find { it != UNKNOWN && number.matches(it.regex) } ?: UNKNOWN
        }
    }
}