package com.example.wallet.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallet.R

val InterBold = FontFamily(
    Font(R.font.inter_bold)
)

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

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        GreetingSection()

        Spacer(modifier = Modifier.height(24.dp))

        PaymentSection()

        Spacer(modifier = Modifier.height(36.dp))

        RecentTransactions(onClick = { })

        Spacer(modifier = Modifier.height(36.dp))

        CardsSection()
    }
}

@Composable
fun GreetingSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Hello, Ahmed",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = InterBold,
            fontSize = 24.sp,
            modifier = Modifier.weight(1f)
        )

        Image(
            painter = painterResource(id = R.drawable.luffy),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(62.dp)
                .clip(CircleShape)
                .border(3.dp, Color.Black, CircleShape)
                .clickable {

                }
        )
    }
}

@Composable
fun PaymentSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Action(title = "Send", icon = Icons.Default.ArrowForward, onClick = { })
        Action(
            title = "Receive",
            icon = ImageVector.vectorResource(id = R.drawable.ic_arrow_down),
            onClick = { })
        Action(
            title = "Payment",
            icon = ImageVector.vectorResource(id = R.drawable.ic_payment),
            onClick = { })
    }
}

@Composable
fun Action(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(Color.Gray.copy(alpha = 0.1f))
                .clickable { onClick() }
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = InterBold
        )
    }
}

@Composable
fun RecentTransactions(
    onClick: () -> Unit
) {
    val recentTransactions = listOf(
        R.drawable.rogger,
        R.drawable.ace,
        R.drawable.zoro,
        R.drawable.nami,
        R.drawable.law,
    )

    Column {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = "Recent Transactions",
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 16.sp,
            fontFamily = InterBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(recentTransactions) { resourceId ->
                Image(
                    painter = painterResource(id = resourceId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .size(58.dp)
                        .clip(CircleShape)
                        .clickable { onClick() }
                        .border(2.dp, Color.Gray.copy(alpha = 0.6f), CircleShape)
                )
            }
        }
    }
}

@Composable
fun CardsSection() {

    val cards = listOf(
        Card(
            id = 6264,
            cardType = "Debit",
            cardNumber = "1234567890123456",
            validThru = "0423",
            ownerName = "Ahmed Mahgoub",
            pin = "462",
            backgroundColor = Color.Black,
            amount = "2500.0"
        ),
        Card(
            id = 5771,
            cardType = "Prepaid",
            cardNumber = "3698512303246256",
            validThru = "0619",
            ownerName = "Ahmed Mahgoub",
            pin = "501",
            backgroundColor = Color.Red,
            amount = "3650.0"
        )
    )

    Column {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = "Cards",
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 16.sp,
            fontFamily = InterBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 24.dp),
        ) {

            item {
                Box(
                    modifier = Modifier
                        .width(56.dp)
                        .height(175.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Gray.copy(alpha = 0.1f))
                        .clickable {

                        },
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Transparent)
                            .border(2.dp, Color.Blue.copy(alpha = 0.6f), CircleShape)

                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.Blue.copy(alpha = 0.6f)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(24.dp))
            }

            items(cards) { card ->
                CardForm(card = card, onClick = { })
            }
        }
    }
}

@Composable
fun CardForm(
    card: Card,
    onClick: () -> Unit
) {
    Box(modifier = Modifier
        .padding(end = 24.dp)
        .fillMaxWidth()
        .height(175.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(card.backgroundColor.copy(alpha = 0.9f))
        .clickable { onClick() }
        .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = card.cardType,
                style = MaterialTheme.typography.labelLarge,
                fontFamily = InterBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 12.dp),
                text = formatCardNumber(card.cardNumber),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                fontFamily = InterBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "VALID THRU",
                style = MaterialTheme.typography.labelSmall,
                fontFamily = InterBold,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )

            Text(
                text = formatValidThru(card.validThru),
                style = MaterialTheme.typography.labelMedium,
                fontFamily = InterBold,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )

            Text(
                text = card.ownerName,
                style = MaterialTheme.typography.labelLarge,
                fontFamily = InterBold,
                color = Color.White
            )
        }
    }
}