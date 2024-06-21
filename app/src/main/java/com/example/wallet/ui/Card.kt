package com.example.wallet.ui

import androidx.compose.ui.graphics.Color

data class Card(
    val id: Int,
    val cardType: String,
    val cardNumber: String,
    val validThru: String,
    val ownerName: String,
    val pin: String,
    val backgroundColor: Color,
    val amount: String,
)