package com.example.mysmarthome

import LoginPage
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mysmarthome.ui.components.Navigation
import com.example.mysmarthome.ui.theme.MySmartHomeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MySmartHomeTheme {

                //val navController = rememberNavController()
                val mainActivity = this


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Text("MySmartHome")
                    Navigation(mainActivity)
                }


            }
        }
    }

    fun imgDevices(): Int {
        return R.drawable.ic_baseline_devices_24
    }

    fun imgConsume(): Int {
        return R.drawable.ic_baseline_timeline_24
    }

    fun imgQRCode(): Int {
        return R.drawable.ic_baseline_qr_code_2_24
    }

    fun composeEmail(addresses: Array<String>, subject: String, text: String, attachment: Uri) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, text)
            putExtra(Intent.EXTRA_STREAM, attachment)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MySmartHomeTheme {

    }
}