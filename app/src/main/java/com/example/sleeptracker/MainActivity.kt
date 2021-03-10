package com.example.sleeptracker

import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.sleeptracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
           R.id.search-> {Toast.makeText(this, " search clicked", Toast.LENGTH_SHORT).show()
            return true}
            R.id.about->{Toast.makeText(this, " search clicked", Toast.LENGTH_SHORT).show()
            return true}
            R.id.add->{val intent = Intent(this,AddSleepActivity::class.java)
                startActivity(intent)
                return true
            }
        }
                return super.onOptionsItemSelected(item);
    }
}
