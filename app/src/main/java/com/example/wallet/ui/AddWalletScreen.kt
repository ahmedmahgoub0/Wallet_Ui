package com.example.wallet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallet.ui.theme.InterBold

val colors = listOf(
    Color.Red.copy(alpha = 0.75f),
    Color.Blue.copy(alpha = 0.75f),
    Color.Black.copy(alpha = 0.85f),
    Color.Magenta.copy(alpha = 0.85f)
)

@Composable
fun AddWalletScreen(
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {

    var cardType by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var validThru by remember { mutableStateOf("") }
    var ownerName by remember { mutableStateOf("") }
    var cardPin by remember { mutableStateOf("") }
    var cardBackgroundColor by remember { mutableStateOf(Color.Red) }
    var colorSelected by remember {
        mutableStateOf(colors[0])
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddCardTopBar(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            onBackClick()
        }

        Spacer(modifier = Modifier.height(32.dp))

        CardDesign(
            cardType = cardType,
            cardNumber = cardNumber,
            validThru = validThru,
            ownerName = ownerName,
            cardBackgroundColor = cardBackgroundColor,
        )

        Column(
            modifier = Modifier
                .padding(top = 6.dp)
                .verticalScroll(rememberScrollState())
                .padding(top = 24.dp, start = 24.dp, end = 24.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (color in colors) {
                    Box(
                        modifier = Modifier
                            .size(62.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(color)
                            .clickable {
                                colorSelected = color
                                cardBackgroundColor = color
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (colorSelected == color) {
                            Icon(
                                modifier = Modifier
                                    .padding(18.dp)
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.White),
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            CustomOutLinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = ownerName,
                placeholder = "Full name",
                keyBoardType = KeyboardType.Text
            ) {
                ownerName = it
            }

            CustomOutLinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = cardType,
                placeholder = "Card type",
                keyBoardType = KeyboardType.Text
            ) {
                cardType = it
            }

            CustomOutLinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = cardNumber,
                placeholder = "Card number",
                keyBoardType = KeyboardType.Number,
                visualTransformation = { number ->
                    formatCardNumber(number)
                },
            ) {
                cardNumber = if (it.length > 16) it.substring(0, 16)
                else it
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomOutLinedTextField(
                    modifier = Modifier.weight(1f),
                    value = validThru,
                    placeholder = "Expiry (MM / YY)",
                    keyBoardType = KeyboardType.Number
                ) {
                    validThru = if (it.length > 5) it.substring(0, 5)
                    else it
                }

                Spacer(modifier = Modifier.width(16.dp))

                CustomOutLinedTextField(
                    modifier = Modifier.weight(1f),
                    value = cardPin,
                    placeholder = "Cvv",
                    keyBoardType = KeyboardType.Number
                ) {
                    cardPin = if (it.length > 3) it.substring(0, 3)
                    else it
                }
            }

            Box(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Blue.copy(alpha = 0.7f))
                    .clickable { onNextClick() }
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = InterBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun AddCardTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(42.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color.Gray.copy(alpha = 0.1f))
                .clickable { onBackClick() }
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.Black
            )
        }

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Add Card",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = InterBold,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun CardDesign(
    cardType: String,
    cardNumber: String,
    validThru: String,
    ownerName: String,
    cardBackgroundColor: Color,
) {
    Box(
        modifier = Modifier
            .width(312.dp)
            .height(175.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(cardBackgroundColor)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            if (cardType.isEmpty()) {
                Text(
                    text = "Card type",
                    style = MaterialTheme.typography.labelLarge,
                    fontFamily = InterBold,
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.7f),
                )
            } else {
                Text(
                    text = cardType,
                    style = MaterialTheme.typography.labelLarge,
                    fontFamily = InterBold,
                    fontSize = 14.sp,
                    color = Color.White,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (cardNumber.isEmpty()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = "____ ____ ____ ____",
                    letterSpacing = 3.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = InterBold
                )
            } else {
                Box(
                    modifier = Modifier
                        .width(218.dp)
                        .align(Alignment.CenterHorizontally),
                ) {
                    Row(
                    ) {
                        for ((index, char) in cardNumber.withIndex()) {
                            Text(
                                text = char.toString(),
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.bodyLarge,
                                fontFamily = InterBold,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(end = if ((index + 1) % 4 == 0) 8.dp else 0.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "VALID THRU",
                style = MaterialTheme.typography.labelSmall,
                fontFamily = InterBold,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                fontSize = 8.sp
            )

            if (validThru.isEmpty()) {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "-- / --",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 10.sp,
                    fontFamily = InterBold,
                    color = Color.White
                )
            } else {
                Row {
                    for ((index, char) in validThru.withIndex()) {
                        Text(
                            text = char.toString(),
                            fontSize = 10.sp,
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.bodyLarge,
                            fontFamily = InterBold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(
                                    top = 4.dp,
                                    start = if ((index + 1) == 3) 4.dp else 0.dp,
                                    end = if ((index + 1) == 3) 4.dp else 0.dp
                                )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            if (ownerName.isEmpty()) {
                Text(
                    text = "Full name",
                    style = MaterialTheme.typography.labelLarge,
                    fontFamily = InterBold,
                    color = Color.White.copy(alpha = 0.8f)
                )
            } else {
                Text(
                    text = ownerName,
                    style = MaterialTheme.typography.labelLarge,
                    fontFamily = InterBold,
                    fontSize = 14.sp,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun CustomOutLinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    keyBoardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Gray.copy(alpha = 0.1f))
    ) {
        TextField(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.Gray.copy(alpha = 0.1f),
                unfocusedContainerColor = Color.Gray.copy(alpha = 0.1f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray,
                cursorColor = Color.Black,
            ),
            placeholder = {
                Text(
                    text = placeholder, style = TextStyle.Default.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = InterBold,
                        letterSpacing = 1.sp
                    )
                )
            },
            value = value,
            textStyle = TextStyle.Default.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = InterBold,
                letterSpacing = 1.sp
            ),
            singleLine = true,
            onValueChange = {
                onValueChange(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyBoardType),
            visualTransformation = visualTransformation
        )
    }
}

fun formatCardNumber(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
    var out = ""

    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 4 == 3 && i != 15) out += " "
    }
    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            if (offset <= 11) return offset + 2
            if (offset <= 16) return offset + 3
            return 19
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 9) return offset - 1
            if (offset <= 14) return offset - 2
            if (offset <= 19) return offset - 3
            return 16
        }
    }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}