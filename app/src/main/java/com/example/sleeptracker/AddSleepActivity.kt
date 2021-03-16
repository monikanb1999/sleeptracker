package com.example.sleeptracker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sleeptime.database.SleepDetails
import com.example.sleeptracker.databinding.ActivityAddSleepBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddSleepActivity : AppCompatActivity(),Handler {
    lateinit var binding: ActivityAddSleepBinding

    val viewModel: SleepMainViewModel by lazy {
        ViewModelProvider(this).get(SleepMainViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSleepBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.handler = this


    }

    override fun onStartDateClicked(view: View) {
        val today = Calendar.getInstance()
        DatePickerDialog(
            this,
            { view, year, month, dayOfMonth ->
                Log.d("datetimeSet", "year $year month $month , dayofmonth $dayOfMonth")
                val cal = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }
                Log.d(
                    "datetimeSet",
                    "year ${cal.get(Calendar.YEAR)} month ${cal.get(Calendar.MONTH)} , dayofmonth ${
                        cal.get(
                            Calendar.DAY_OF_MONTH
                        )
                    }"
                )
                //viewmodel.changeDate(cal.time)
                val simpleDateFormat = SimpleDateFormat("MM-dd-yyyy", Locale.US)
                binding.etstartdate.setText(simpleDateFormat.format(cal.time))
            },
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ).show()

    }

    override fun onStartTimeClicked(view: View) {
        val time = Calendar.getInstance()
        TimePickerDialog(this, { view, hourOfDay, minute ->
            val cal = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hourOfDay)
                set(Calendar.MINUTE, minute)
            }
            val simpletimeFormat = SimpleDateFormat("hh:mm:ss", Locale.US)
            binding.etstarttime.setText(simpletimeFormat.format(cal.time))
            //viewmodel.changeTime(cal.time)
        }, time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), false).show()
    }


    override fun onEndDateClicked(view: View) {
        val today1 = Calendar.getInstance()
        DatePickerDialog(
            this,
            { view, year, month, dayOfMonth ->
                Log.d("datetimeSet", "year $year month $month , dayofmonth $dayOfMonth")
                val cal = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }
                Log.d(
                    "datetimeSet",
                    "year ${cal.get(Calendar.YEAR)} month ${cal.get(Calendar.MONTH)} , dayofmonth ${
                        cal.get(
                            Calendar.DAY_OF_MONTH
                        )
                    }"
                )
                //viewmodel.changeDate(cal.time)
                val simpleDateFormat1 = SimpleDateFormat("MM-dd-yyyy", Locale.US)
                binding.etenddate.setText(simpleDateFormat1.format(cal.time))
            },
            today1.get(Calendar.YEAR),
            today1.get(Calendar.MONTH),
            today1.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    override fun onEndTimeClicked(view: View) {
        val time1 = Calendar.getInstance()
        TimePickerDialog(this, { view, hourOfDay, minute ->
            val cal = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hourOfDay)
                set(Calendar.MINUTE, minute)
            }
            val simpletimeFormat = SimpleDateFormat("hh:mm:ss", Locale.US)
            binding.etendtime.setText(simpletimeFormat.format(cal.time))
            //viewmodel.changeTime(cal.time)
        }, time1.get(Calendar.HOUR_OF_DAY), time1.get(Calendar.MINUTE), false).show()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.saveoption, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.about -> {
                val intent = Intent(this, mpchartActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.save -> {
                onSaveClicked();
                return true
            }
        }
        return super.onOptionsItemSelected(item);
    }
//    var date = Calendar.getInstance().time
//    var sd: DateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z")
//    var StringDate = sd.format(date)
//    TextViewDate.setText(StringDate)

    private fun onSaveClicked() {
      if(!TextUtils.isEmpty(binding.etstartdate.text.toString())){
//          val startdate=convertLongToString(
//              binding.etstartdate.text.toString()
//             )
        val starttime = convertStringToLong(
            binding.etstartdate.text.toString(),
            binding.etstarttime.text.toString()
        )
        val endtime = convertStringToLong(
            binding.etenddate.text.toString(),
            binding.etendtime.text.toString()
        )
        val milliseconds = endtime - starttime
        Log.d("startdate", "convertStringToLong 1111: $starttime")
        Log.d("startdate", "convertStringToLong 1111: $endtime")
        Log.d("startdate", "onSaveClicked: ${endtime - starttime}")
        val minutes = (milliseconds / (1000 * 60) % 60)
        val hours = (milliseconds / (1000 * 60 * 60) % 24)
        //val total=hours.toString()+"hrs"
        Log.d("startdate", "onSaveClicked:$minutes ")
        Log.d("startdate", "onSaveClicked: $hours")
          val table = SleepDetails(
              0,
              starttime,
              endtime,
              hours.toString() + "hrs" + minutes + "mins"
          )
          viewModel.inserttable(table)
    }
    else
    {
        Toast.makeText(this, "exited without inserting", Toast.LENGTH_LONG).show()

    }

}
    fun convertStringToLong(toString: String, toString1: String):Long {
        val startString = toString+" "+toString1
        Log.d("startdate", "convertStringToLong:$startString ")
       // val endString = "march 15, 2021 12:34:45"
        val simpleFormatter=SimpleDateFormat("MM-dd-yyyy hh:mm:ss")
        //val formatterSd = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH)
        val Stdate = simpleFormatter.parse(startString)
//        val Etdate = simpleFormatter.parse(endString)
//        val Eddate = LocalDate.parse(endString, formatterEd)
        Log.d("startdate", "convertStringToLong: ${Stdate.time}")
        Log.d("startdate", "convertStringToLong: ${Stdate}")
        return Stdate.time
    }
    fun convertLongToString(toLong: Long):String{
        val startLong=toLong
        Log.d("startdate", "convertStringToLong:$startLong ")
        val date = Calendar.getInstance().time
        val DateFormat = SimpleDateFormat("yyyy.MM.dd ")
        val StringDate = DateFormat.format(date)
        Log.d("startdate1", "convertStringToLong: ${StringDate}")
        return StringDate
//    var StringDate = sd.format(date)
//    TextViewDate.setText(StringDate)

    }

}



//        class RecyclerAdapter(context: Context) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
//
//            private val inflater : LayoutInflater = LayoutInflater.from(context)
//
//            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
//                val binding:RegisterBinding = DataBindingUtil.inflate(inflater,R.layout.register,parent,false)
//                return RecyclerViewHolder((binding))
//            }



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

