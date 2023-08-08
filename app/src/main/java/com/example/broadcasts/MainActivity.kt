package com.example.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.btnTest

class MainActivity : AppCompatActivity() {
    private lateinit var customBroadcastReceiver: CustomBroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customBroadcastReceiver = CustomBroadcastReceiver()

        btnTest.setOnClickListener {
            val intent = Intent(Constants.ACTION_CUSTOM_BROADCAST)
            intent.putExtra("message", "Hello from the sender!")
            sendBroadcast(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(Constants.ACTION_CUSTOM_BROADCAST)
        registerReceiver(customBroadcastReceiver, filter)
    }
}
object Constants {
    const val ACTION_CUSTOM_BROADCAST = "com.example.ACTION_CUSTOM_BROADCAST"
}
class CustomBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if(p1?.action == Constants.ACTION_CUSTOM_BROADCAST){
            val message = p1.getStringExtra("message")
            Toast.makeText(p0, "Received message: $message", Toast.LENGTH_SHORT).show()
        }
    }

}
