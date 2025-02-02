package com.example.currencyconverter.persentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currencyconverter.ui.CurrencyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ConvertCard(viewModel : CurrencyViewModel = viewModel()) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    val result = viewModel.remoteCurrencies.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 50.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        ) {
            Text(
                text = "Amount",
                style = TextStyle(
                    color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            )
            Text(
                text = "From",
                style = TextStyle(
                    color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {


            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(10.dp))

            CurrencyDropdown(Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        ) {
            Text(
                text = "To",
                style = TextStyle(
                    color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            )

            Text(
                text = "Amount",
                style = TextStyle(
                    color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CurrencyDropdown(modifier = Modifier.weight(1f) )

            Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "", style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    ), modifier = Modifier
                        .weight(1f)
                        .border(
                            2.dp,
                            MaterialTheme.colorScheme.inverseOnSurface,
                            shape = CircleShape
                        )
                        .padding(16.dp)
                        .weight(1f)
                )
            }


        Button(
            onClick = {  viewModel.onEvent(Event.Convert(amount= text.text.toDouble(), from=viewModel.state.value.selectedCurrencyIndex, to= "")) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Convert", style = TextStyle(
                    fontSize = 16.sp, fontWeight = FontWeight.Bold
                )
            )
        }

    }
}
