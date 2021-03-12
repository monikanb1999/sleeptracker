package com.example.sleeptracker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sleeptime.database.SleepDetails
import com.example.sleeptracker.databinding.ActivityAddSleepBinding
import java.text.SimpleDateFormat
import java.util.*

class AddSleepActivity : AppCompatActivity(),Handler {
    lateinit var binding: ActivityAddSleepBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddSleepBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.handler=this


        val viewmodel: SleepMainViewModel by lazy {
            ViewModelProvider(this).get(SleepMainViewModel::class.java)
        }



    }
    override fun onStartDateClicked(view: View) {
            val today = Calendar.getInstance()
            DatePickerDialog(this , { view, year, month, dayOfMonth ->
                Log.d("datetimeSet" , "year $year month $month , dayofmonth $dayOfMonth")
                val cal = Calendar.getInstance().apply {
                    set(Calendar.YEAR , year)
                    set(Calendar.MONTH , month)
                    set(Calendar.DAY_OF_MONTH , dayOfMonth)
                }
                Log.d("datetimeSet" , "year ${cal.get(Calendar.YEAR)} month ${cal.get(Calendar.MONTH)} , dayofmonth ${cal.get(
                        Calendar.DAY_OF_MONTH)}")
                //viewmodel.changeDate(cal.time)
                val simpleDateFormat = SimpleDateFormat("MM-dd-yyyy", Locale.US)
                binding.etstartdate.setText(simpleDateFormat.format(cal.time))
            }, today.get(Calendar.YEAR) , today.get(Calendar.MONTH) , today.get(Calendar.DAY_OF_MONTH)).show()
        }

    override fun onStartTimeClicked(view: View) {
            val time = Calendar.getInstance()
            TimePickerDialog(this , { view, hourOfDay, minute ->
                val cal = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY , hourOfDay)
                    set(Calendar.MINUTE , minute)
                }
                val simpletimeFormat = SimpleDateFormat("hh:mm:sss", Locale.US)
                binding.etstarttime.setText(simpletimeFormat.format(cal.time))
                //viewmodel.changeTime(cal.time)
            }, time.get(Calendar.HOUR_OF_DAY) , time.get(Calendar.MINUTE)  , false ).show()
        }



    override fun onEndDateClicked(view: View) {
        val today1 = Calendar.getInstance()
        DatePickerDialog(this , { view, year, month, dayOfMonth ->
            Log.d("datetimeSet", "year $year month $month , dayofmonth $dayOfMonth")
            val cal = Calendar.getInstance().apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }
            Log.d("datetimeSet", "year ${cal.get(Calendar.YEAR)} month ${cal.get(Calendar.MONTH)} , dayofmonth ${
                cal.get(
                        Calendar.DAY_OF_MONTH)
            }")
            //viewmodel.changeDate(cal.time)
            val simpleDateFormat1 = SimpleDateFormat("MM-dd-yyyy", Locale.US)
            binding.etenddate.setText(simpleDateFormat1.format(cal.time))
        },today1.get(Calendar.YEAR),today1.get(Calendar.MONTH),today1.get(Calendar.DAY_OF_MONTH)).show()
    }
    override fun onEndTimeClicked(view: View) {
        val time1 = Calendar.getInstance()
        TimePickerDialog(this , { view, hourOfDay, minute ->
            val cal = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY , hourOfDay)
                set(Calendar.MINUTE , minute)
            }
            val simpletimeFormat = SimpleDateFormat("hh:mm:sss", Locale.US)
            binding.etendtime.setText(simpletimeFormat.format(cal.time))
            //viewmodel.changeTime(cal.time)
        }, time1.get(Calendar.HOUR_OF_DAY) , time1.get(Calendar.MINUTE)  , false ).show()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.saveoption, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search-> {
                Toast.makeText(this, " search clicked", Toast.LENGTH_SHORT).show()
                return true}
            R.id.about->{
                Toast.makeText(this, " search clicked", Toast.LENGTH_SHORT).show()
                return true}
            R.id.save->{
                onSaveClicked();
                return true
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private fun onSaveClicked(){
      if(!TextUtils.isEmpty(binding.etstartdate.text.toString())){
          val table =SleepDetails(
                   0,
                  Calendar.getInstance().timeInMillis,
                  Calendar.getInstance().timeInMillis,Calendar.getInstance().timeZone.toString().length
                          )
         //viewmodel.inserttable(table)
       }



//        class RecyclerAdapter(context: Context) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
//
//            private val inflater : LayoutInflater = LayoutInflater.from(context)
//
//            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
//                val binding:RegisterBinding = DataBindingUtil.inflate(inflater,R.layout.register,parent,false)
//                return RecyclerViewHolder((binding))
//            }


    }
}
//    override fun onAddClicked(view: View) {
//        if (!TextUtils.isEmpty(binding.username.text.toString())) {
//            val table = ENTITY(
//                0, binding.username.text.toString(),
//                binding.gender.text.toString(),
//                binding.email.text.toString(), Calendar.getInstance().timeInMillis
//            )
//            viewMODEL.inserttable(table)
//        } else {
//            Toast.makeText(this, "done the process", Toast.LENGTH_LONG).show()
//            Toast.makeText(this, "done the process", Toast.LENGTH_LONG).show()
//        }
//    }


//        override fun onTimeClicked(view: View) {
//            val today = Calendar.getInstance()
//            TimePickerDialog(this , { view, hourOfDay, minute ->
//                val cal = Calendar.getInstance().apply {
//                    set(Calendar.HOUR_OF_DAY , hourOfDay)
//                    set(Calendar.MINUTE , minute)
//                }
//                viewmodel.changeTime(cal.time)
//            }, today.get(Calendar.HOUR_OF_DAY) , today.get(Calendar.MINUTE)  , false ).show()
//        }
//
//        Toast.makeText(this,"startdate clicked",Toast.LENGTH_LONG).show()
//    }

