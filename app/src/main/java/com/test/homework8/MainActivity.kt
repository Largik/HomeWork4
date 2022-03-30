package com.test.homework8

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.test.homework8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val CHANNEL_ID = "channel_id"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChanel()

        binding.sendNotification.setOnClickListener {
            checkGooglePlayServices()
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                val token = task.result

                Log.d("token", token)
                Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
            })
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChanel() {
        val name = "channel_name"
        val descriptionText = "channel_desc"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun checkGooglePlayServices(): Boolean {
        val status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)

        return if (status != ConnectionResult.SUCCESS) {
            Log.w("Message", "ERROR! No googlePlay services")
            false
        } else {
            true
        }
    }
}