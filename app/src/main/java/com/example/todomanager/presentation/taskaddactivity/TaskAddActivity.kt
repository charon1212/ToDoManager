package com.example.todomanager.presentation.taskaddactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todomanager.R

const val extraKeyTaskTitle = "com.example.todomanager.tasktitle"
const val extraKeyTaskDetail = "com.example.todomanager.taskdetail"

class TaskAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_add)
    }

    fun btnAddOnClick(view: View) {
        val title: String = findViewById<EditText>(R.id.txtTitle).text.toString()
        val detail: String = findViewById<EditText>(R.id.txtDetail).text.toString()
        // validate
        if (title.isEmpty()) {
            Toast.makeText(this, "タイトルを入力してください。", Toast.LENGTH_SHORT).show()
            return
        }
        // send param
        val intent = Intent().apply {
            putExtra(extraKeyTaskTitle, title)
            putExtra(extraKeyTaskDetail, detail)
        }
        setResult(Activity.RESULT_OK, intent)
        // finish
        finish()
    }
}