package com.example.sleeptracker

import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sleeptime.database.SleepDetails
import com.example.sleeptracker.databinding.ActivityAddSleepBinding
import com.example.sleeptracker.databinding.ActivityMainBinding
import com.example.sleeptracker.databinding.SleeprecordBinding

class MainActivity : AppCompatActivity(){
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SleepRecyclerAdapter


    val viewmodel2: SleepMainView2Model by lazy {
        ViewModelProvider(this).get(SleepMainView2Model::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // binding.handlerMain = this

        adapter= SleepRecyclerAdapter(this)
        binding.sleeprecyclerview?.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.sleeprecyclerview?.layoutManager = layoutManager
        binding.sleeprecyclerview?.adapter = adapter

        viewmodel2.sleepdetaillist.observe(this, androidx.lifecycle.Observer {
            Log.d("listsize", "onCreate: ${it.size}")
            if(it.isNotEmpty()){
                adapter.sleepdetaillist=it
                adapter.notifyDataSetChanged()
            }
        })

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


        private val inflater: LayoutInflater = LayoutInflater.from(context)
        var sleepdetaillist = emptyList<SleepDetails>()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepViewHolder {
            val binding: SleeprecordBinding = DataBindingUtil.inflate(inflater, R.layout.sleeprecord, parent, false)
            return SleepViewHolder((binding))
        }

        override fun onBindViewHolder(holder: SleepViewHolder, position: Int) {
            holder.binding.tvstartdate.text= sleepdetaillist[position].StartDate.toString()
            holder.binding.tvenddate.text= sleepdetaillist[position].EndDate.toString()
            holder.binding.tvtotalduration.text= sleepdetaillist[position].TotalDuration.toString()
            holder.binding.executePendingBindings()
        }

        override fun getItemCount(): Int {
            return sleepdetaillist.size
        }
        inner class SleepViewHolder(val binding: SleeprecordBinding) : RecyclerView.ViewHolder(binding.root)


    }



}
