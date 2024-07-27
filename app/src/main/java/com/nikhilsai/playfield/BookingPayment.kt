
package com.nikhilsai.playfield

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nikhilsai.playfield.Playfieldcomponents.HeadingTextComponent
import com.nikhilsai.playfield.ui.theme.Primary

@Composable
fun StyledTextField(
    label: String,
    icon: ImageVector,
    onTextChanged: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = label, style = androidx.compose.material.MaterialTheme.typography.subtitle1, color = Primary)
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                onTextChanged(it)
            },
            leadingIcon = {
                androidx.compose.material.Icon(imageVector = icon, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = Color.Gray
            )
        )
    }
}

class PaymentActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val context = LocalContext.current
            val gradient45 = Brush.linearGradient(
                colors = listOf(Color.White, Color.White),
                start = Offset(0f, Float.POSITIVE_INFINITY),
                end = Offset(Float.POSITIVE_INFINITY, 0f)
            )

            Surface(
                modifier = Modifier
                    .background(gradient45)
                    .fillMaxSize()
                    .padding(28.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradient45),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(150.dp),
                        painter = painterResource(id = R.drawable.card),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Spacer(modifier = Modifier.height(15.dp))
                    HeadingTextComponent(value = "Payment Details")
                    Spacer(modifier = Modifier.height(5.dp))

                    StyledTextField(
                        label = "cardholder's name",
                        icon = Icons.Default.AccountBalance,
                        onTextChanged = {
                            // Handle text change
                        }
                    )
                    StyledTextField(
                        label = "Card Type",
                        icon = Icons.Default.Sort,
                        onTextChanged = {
                            // Handle text change
                        }
                    )
                    StyledTextField(
                        label = "Card Number",
                        icon = Icons.Default.CreditCard,
                        onTextChanged = {
                            // Handle text change
                        }
                    )



                    StyledTextField(
                        label = "3 digits Cvv Number",
                        icon = Icons.Default.Lock,
                        onTextChanged = {
                            // Handle text change
                        }
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        modifier = Modifier
                            .wrapContentWidth()
                            .heightIn(48.dp),
                        onClick = {
                            context.startActivity(Intent(context, OrderSuccessActivity::class.java))
                        },
                        contentPadding = PaddingValues(),
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        shape = RoundedCornerShape(50.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(48.dp)
                                .background(
                                    brush = Brush.horizontalGradient(listOf(Primary, Primary)),
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Proceed",
                                fontSize = 18.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(0.dp))
                }
            }
        }
    }

}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    // Preview function can be implemented if needed
}
