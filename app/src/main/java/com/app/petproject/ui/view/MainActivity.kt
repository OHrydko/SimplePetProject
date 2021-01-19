package com.app.petproject.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.petproject.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.frame, MainFragment()).commit()
    }
}