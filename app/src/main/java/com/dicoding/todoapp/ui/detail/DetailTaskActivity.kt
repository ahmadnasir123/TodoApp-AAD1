package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {
    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action

        val taskId = intent.getIntExtra(TASK_ID, 0)

        val factory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this, factory).get(DetailTaskViewModel::class.java)

        detailTaskViewModel.setTaskId(taskId)

        detailTaskViewModel.task.observe(this){
            if (it != null){
                setDetail(it)
            }
        }

        val buttonDelete = findViewById<Button>(R.id.btn_delete_task)
        buttonDelete.setOnClickListener{
            detailTaskViewModel.deleteTask()
            finish()
            Toast.makeText(this, "Berhasil menghapus", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setDetail(it: Task) {
        val title = findViewById<TextView>(R.id.detail_ed_title)
        val desc = findViewById<TextView>(R.id.detail_ed_description)
        val date = findViewById<TextView>(R.id.detail_ed_due_date)

        title.setText(it.title)
        desc.setText(it.description)
        date.text = DateConverter.convertMillisToString(it.dueDateMillis)
    }
}