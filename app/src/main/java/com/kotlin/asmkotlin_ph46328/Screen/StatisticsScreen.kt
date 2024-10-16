

package com.kotlin.asmkotlin_ph46328

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Statistics : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StatisticsScreen()
        }
    }
}

@Composable
fun   StatisticsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tiêu đề
        Text(
            text = "Cơm tấm dim",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Danh sách liên lạc
        ContactItem("Zalo", "0947289999", painterResource(id = R.drawable.zaloimages))
        ContactItem("Gmail", "phuonglttph46328@fpt.edu.vn", painterResource(id = R.drawable.gmailimages))
        ContactItem("Điện thoại", "0936789999", painterResource(id = R.drawable.phonesimages))
    }
}

@Composable
fun ContactItem(label: String, info: String, icon: androidx.compose.ui.graphics.painter.Painter) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = icon,
            contentDescription = label,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = label, fontWeight = FontWeight.Bold)
            Text(text = info, color = Color.Gray)
        }
    }
}
