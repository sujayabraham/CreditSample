package com.creditcard.library


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CreditCardInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color.Black
) {
    // Detect card type for icon and length validation
    val cardType = remember(key1 = value) {
        val patterns = listOf(
            Triple("^4[0-9]*$", R.drawable.visa, "Visa"),
            Triple("^5[1-5][0-9]*$", R.drawable.mastercard, "MasterCard"),
            Triple("^3[47][0-9]*$", R.drawable.amex, "American Express")
        )
        patterns.find { (regex, _, _) -> value.matches(regex.toRegex()) }
    }

        // Remove the }, that was here
        OutlinedTextField(
            value = value,
            onValueChange = { input: String ->
                val digits = input.filter { it.isDigit() }
                val maxLength = if (cardType?.third == "American Express") 15 else 16
                if (digits.length <= maxLength) {
                    onValueChange(digits)
                }
            },
            modifier = modifier,
            label = {
                Text(text = "Enter Card Number")
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = cardType?.second ?: R.drawable.credit_card),
                    contentDescription = cardType?.third ?: "Credit Card",
                    modifier = Modifier.size(size = 28.dp),
                    tint =color
                )
            },
            visualTransformation = CreditCardVisualTransformation(separator = '-'),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
        )
} // This brace closes the CreditCardInputField function

