package com.example.sleeptracker

import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sleeptracker.databinding.ActivityAddSleepBinding
import com.example.sleeptracker.databinding.ActivityMainBinding
import com.example.sleeptracker.databinding.SleeprecordBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SleepRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.handler = this

        adapter = SleepRecyclerAdapter(this)
        binding.sleeprecyclerview?.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.sleeprecyclerview?.layoutManager = layoutManager
        binding.sleeprecyclerview?.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                Toast.makeText(this, " search clicked", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.about -> {
                Toast.makeText(this, " about clicked", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.add -> {
                val intent = Intent(this, AddSleepActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item);
    }

    class SleepRecyclerAdapter(context: Context) : RecyclerView.Adapter<SleepRecyclerAdapter.SleepViewHolder>() {

        inner class SleepViewHolder(binding: SleeprecordBinding) : RecyclerView.ViewHolder(binding.root)

        private val inflater: LayoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepViewHolder {
            val binding: SleeprecordBinding = DataBindingUtil.inflate(inflater, R.layout.sleeprecord, parent, false)
            return SleepViewHolder((binding))
        }

        override fun onBindViewHolder(holder: SleepViewHolder, position: Int) {
           //holder.binding.
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }
    }
}
