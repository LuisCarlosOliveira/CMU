package com.example.mysmarthome

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CalendarContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.mysmarthome.ui.components.Navigation
import com.example.mysmarthome.ui.theme.MySmartHomeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySmartHomeTheme {
                val mainActivity = this

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation(mainActivity)
                }

            }
        }
    }

    fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        val MY_REQUEST_PERMISSION = 112
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.SET_ALARM
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.SET_ALARM),
                MY_REQUEST_PERMISSION
            )

        } else {

            try {
                startActivity(intent)
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }
    }

    fun addEvent(title: String, location: String, description: String) {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, title)
            putExtra(CalendarContract.Events.EVENT_LOCATION, location)
            putExtra(CalendarContract.Events.DESCRIPTION, description)
            putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
        }

        try {
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    fun homeLocation(location:String){
        val mapLocation: Uri = Uri.parse("geo:0,0?q=" + Uri.encode(location))
        val intent = Intent(Intent.ACTION_VIEW, mapLocation)
        try {
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    fun composeEmail(addresses: Array<String>, subject: String, text: String, attachment: Uri) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, text)
            putExtra(Intent.EXTRA_STREAM, attachment)
        }
        try {
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    fun callSomeone(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        val MY_REQUEST_PERMISSION = 111
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CALL_PHONE),
                MY_REQUEST_PERMISSION
            )

        } else {

            try {
                startActivity(intent)
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }
    }

    fun composeSMSMessage(message: String, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phoneNumber, null))
        intent.putExtra("sms_body", message)
        try {
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }

    }

    fun notification_logged_in() {
        val CHANNEL_ID = "007"
        // Create an explicit intent for an Activity in your app
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.worten.pt/smart-home-e-redes/smart-home/tudo-sobre-smart-home"))
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Início de Sessão")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Seja Bem-Vindo(a)!\nAcabou de iniciar sessão na aplicação MySmartHome!\n" +
                            "Disfrute ao máximo da gestão da sua casa inteligente!")

            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(R.drawable.ic_launcher_foreground, "Adquirir Dispositivos", pendingIntent)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Canal"
            val descriptionText = "Canal para fazer notificacao"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            //Mostrar
            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                val notificationId = 111
                notify(notificationId, builder.build())
            }

        }
    }

fun notification_member_request() {
        val CHANNEL_ID = "007"
        // Create an explicit intent for an Activity in your app
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Pedido de Adesão")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("O Nelo está a pedir permissão para aceder à casa")

            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Canal"
            val descriptionText = "Canal para fazer notificacao"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            //Mostrar
            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                val notificationId = 111
                notify(notificationId, builder.build())
            }

        }
    }





}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MySmartHomeTheme {

    }
}
