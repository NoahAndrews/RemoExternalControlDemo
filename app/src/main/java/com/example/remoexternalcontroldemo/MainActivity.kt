package com.example.remoexternalcontroldemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            val intent = Intent("org.btelman.controlsdk.request.stop")
            intent.`package` = "tv.remo.android.controller"
            sendBroadcast(intent)
        }
    }
}
