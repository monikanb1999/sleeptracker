package com.example.sleeptracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sleeptracker.databinding.ActivityAddSleepBinding
import com.example.sleeptracker.databinding.ActivityMainBinding

class AddSleepActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddSleepBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddSleepBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}