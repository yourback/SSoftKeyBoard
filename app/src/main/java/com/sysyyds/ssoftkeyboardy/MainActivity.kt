package com.sysyyds.ssoftkeyboardy

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sysyyds.sysoftkeyboard.SoftKeyBoardUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        SoftKeyBoardUtils.setTargetViewAlwaysAboveSoftKeyBoard(window.decorView, button)
    }
}