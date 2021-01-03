package com.example.remoexternalcontroldemo

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.remoexternalcontroldemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val apiKey = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener() {
            val intent = Intent("tv.remo.android.controller.action.REQUEST_REMO_STREAM_START")
            intent.putExtra("ApiKey", apiKey)
            intent.putExtra("EnableCamera", true)
            intent.putExtra("CameraDeviceId", 0)

            startActivityForResult(intent, 0)
        }

        binding.stopButton.setOnClickListener() {
            val stopIntent = Intent("org.btelman.controlsdk.request.stop")
            stopIntent.`package` = "tv.remo.android.controller"
            sendBroadcast(stopIntent)
        }

        binding.sendButton.setOnClickListener() {
            val chatIntent = Intent("tv.remo.android.controller.sdk.chat.send")
            chatIntent.putExtra("message", binding.editTextMessage.text.toString())
            sendBroadcast(chatIntent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val toastMessage = if (resultCode == Activity.RESULT_CANCELED) {
            "The stream was not started"
        } else {
            "The stream was started"
        }
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }
}
