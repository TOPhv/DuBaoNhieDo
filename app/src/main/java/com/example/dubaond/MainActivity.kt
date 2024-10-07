package com.example.dubaond
import androidx.compose.ui.tooling.preview.Preview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dubaond.ui.theme.DubaondTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DubaondTheme {
                TemperatureScreen()
            }
        }
    }
}
@Composable
fun TemperatureScreen() {
    var temperatureInput by remember { mutableStateOf("") }
    var temperature by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ô nhập liệu cho người dùng nhập nhiệt độ
        OutlinedTextField(
            value = temperatureInput,
            onValueChange = { input ->
                temperatureInput = input
                temperature = input.toFloatOrNull() ?: 0f
            },
            label = { Text("Nhập nhiệt độ (°C)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Hiển thị hình tròn với màu sắc và nhãn tương ứng
        TemperatureCircle(temperature = temperature)
    }
}

@Composable
fun TemperatureCircle(temperature: Float) {
    // Logic thay đổi màu sắc và nhãn của hình tròn dựa trên nhiệt độ
    val (circleColor, weatherText) = when {
        temperature < 0 -> Color(0xFF0D47A1) to "Rất lạnh" // Màu xanh đậm
        temperature < 25 -> Color.Blue to "Lạnh"
        temperature in 25f..28f -> Color.Green to "Ôn hòa"
        temperature in 29f..35f -> Color(0xFFFFA500) to "Nóng" // Màu cam
        else -> Color.Red to "Rất nóng"
    }

    Column(
        modifier = Modifier
            .size(200.dp)
            .background(circleColor, CircleShape),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Nhãn bên trong hình tròn
        Text(
            text = weatherText,
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DubaondTheme {
        // Gọi hàm TemperatureScreen để hiển thị giao diện trong Preview
        TemperatureScreen()
    }
}