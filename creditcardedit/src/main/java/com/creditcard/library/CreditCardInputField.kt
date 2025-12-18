package com.creditcard.library


import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    cardConfigs: List<CardTypeConfig> = emptyList(),
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    label : String = "Enter Card Number"
) {
    // Recalculate activeConfig whenever value or the config list changes
    val activeConfig = remember(value, cardConfigs) {
        cardConfigs.find { config ->
            value.matches(config.regex.toRegex())
        }
    }

    OutlinedTextField(
        value = value,
        onValueChange = { input: String ->
            // Filter digits and enforce the limit from the dynamic config
            val digits = input.filter { it.isDigit() }
            val limit = activeConfig?.maxLength ?: 16

            if (digits.length <= limit) {
                onValueChange(digits)
            }
        },
        modifier = modifier,
        label = {
            Text(text = label)
        },
        trailingIcon = {
            Icon(
                // This updates dynamically because activeConfig is reactive
                painter = painterResource(
                    id = activeConfig?.logoRes ?: R.drawable.credit_card
                ),
                contentDescription = activeConfig?.name ?: "Card Type",
                modifier = Modifier.size(size = 28.dp),
                // tint is passed from Sample App.
                // Color.Unspecified is usually better for brand logos.
                tint = color
            )
        },
        visualTransformation = CreditCardVisualTransformation(separator = '-'),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
    )
}
