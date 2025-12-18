package com.creditcard.edittext

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.creditcard.edittext.ui.theme.CreditCardEditTextTheme
import com.creditcard.library.CardTypeConfig
import com.creditcard.library.CreditCardInputField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CreditCardEditTextTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Card User",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // State holds the raw digits
    var cardNumber by remember { mutableStateOf("") }

    // Client-side Configuration: Add or update card types here without changing the library
    val myConfigs = remember {
        listOf(
            CardTypeConfig(
                name = "Visa",
                regex = "^4[0-9]*$",
                logoRes = com.creditcard.library.R.drawable.visa
            ),
            CardTypeConfig(
                name = "MasterCard",
                regex = "^(5[1-5]|222[1-9]|22[3-9]|2[3-6]|27[0-1]|2720)[0-9]*$",
                logoRes = com.creditcard.library.R.drawable.mastercard
            ),
            CardTypeConfig(
                name = "Amex",
                regex = "^3[47][0-9]*$",
                logoRes = com.creditcard.library.R.drawable.amex,
                maxLength = 15
            ),
            CardTypeConfig(
                name = "Discover",
                regex = "^(6011|65|64[4-9]|622(12[6-9]|1[3-9][0-9]|[2-8][0-9]{2}|9[0-1][0-9]|92[0-5]))[0-9]*$",
                logoRes = R.drawable.ic_discover // Ensure this is in app/res/drawable
            ),
            CardTypeConfig(
                name = "Diners Club",
                regex = "^(30[0-5]|36|38|39)[0-9]*$",
                logoRes = R.drawable.ic_diners // Ensure this is in app/res/drawable
            ),
            /*CardTypeConfig(
                name = "JCB",
                regex = "^(352[8-9]|35[3-7][0-9]|358[0-9])[0-9]*$",
                logoRes = R.drawable.ic_jcb
            ),
            CardTypeConfig(
                name = "RuPay",
                regex = "^(60|65|81|82|508)[0-9]*$",
                logoRes = R.drawable.ic_rupay
            ),*/
            CardTypeConfig(
                name = "UnionPay",
                regex = "^62[0-9]*$",
                logoRes = R.drawable.ic_unionpay
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Enter your details, $name")
        Spacer(modifier = Modifier.height(height = 16.dp))

        CreditCardInputField(
            value = cardNumber,
            onValueChange = { input -> cardNumber = input },
            cardConfigs = myConfigs,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Unspecified, // Set to Unspecified to keep original icon colors
            label = "Enter Card Number"
        )

        Spacer(modifier = Modifier.height(height = 24.dp))

        Button(
            onClick = {
                Log.d("CreditCardInput", "Final Card Number: $cardNumber")
            },
            modifier = Modifier.fillMaxWidth(fraction = 0.7f),
            enabled = cardNumber.isNotEmpty()
        ) {
            Text(text = "NEXT")
        }
    }
}
