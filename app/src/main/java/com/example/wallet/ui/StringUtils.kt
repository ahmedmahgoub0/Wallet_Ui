package com.example.wallet.ui

fun formatValidThru(validThru: String): String {
    val first = validThru.substring(0, 2)
    val second = validThru.substring(2)
    return "$first / $second"
}

fun formatCardNumber(cardNumber: String): String {
    return "${cardNumber.substring(0, 4)} ${cardNumber.substring(4, 8)} ${
        cardNumber.substring(
            8,
            12
        )
    } ${cardNumber.substring(12)}"
}