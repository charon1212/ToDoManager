package com.example.todomanager.presentation.mainactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todomanager.R
import com.example.todomanager.domain.model.Task
import com.example.todomanager.domain.service.TaskService
import com.example.todomanager.infrastructure.repository.TaskDAO
import com.example.todomanager.presentation.mainactivity.recycleview.RecyclerViewAdapter
import com.example.todomanager.presentation.taskaddactivity.TaskAddActivity
import com.example.todomanager.presentation.taskaddactivity.extraKeyTaskDetail
import com.example.todomanager.presentation.taskaddactivity.extraKeyTaskTitle

class MainActivity : AppCompatActivity() {

    private var taskList: List<Task> = listOf()
    private val taskService = TaskService(TaskDAO())
    private var adapter = RecyclerViewAdapter(taskList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskList = taskService.getTasks()
        this.adapter = setRecyclerViewAdapter(taskList)
    }

    private fun setRecyclerViewAdapter(data: List<Task>): RecyclerViewAdapter {
        val rvAdapter = RecyclerViewAdapter(data)
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = rvAdapter
        }
        return rvAdapter
    }

    /**
     * 追加ボタンクリック時の処理
     *
     * @param view クリックしたビュー
     */
    fun btnAddOnClick(view: View) {

        val taskAddActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
                if (result?.resultCode == Activity.RESULT_OK) {
                    // receive param
                    val intent = result.data ?: return@registerForActivityResult
                    val title: String =
                        intent.getStringExtra(extraKeyTaskTitle) ?: return@registerForActivityResult
                    val detail: String = intent.getStringExtra(extraKeyTaskDetail)
                        ?: return@registerForActivityResult
                    // create task
                    val task: Task = taskService.createTask(title, detail)
                    taskList += task
                    setRecyclerViewAdapter(taskList)
                }
            }

        val intent = Intent(this, TaskAddActivity::class.java)
        taskAddActivityResultLauncher.launch(intent)

    }

    /**
     * 更新ボタンクリック時の処理
     *
     * @param view クリックしたビュー
     */
    fun btnUpdateOnClick(view: View) {
        // サンプルとして、一番上のタスクの中身を変更する。
        val task = taskList[0]
        if (task !== null) {
            task.detail += "_update"
            taskService.updateTask(task)
            // TODO:適切なnotify関数に切り替える。
            adapter.notifyDataSetChanged()
        }
        Toast.makeText(this, "update button clicked", Toast.LENGTH_SHORT).show()
    }

    /**
     * 削除ボタンクリック時の処理
     *
     * @param view クリックしたビュー
     */
    fun btnDeleteOnClick(view: View) {

        // サンプルとして、一番上のタスクを削除する。
        if (taskList[0] !== null) {
            taskService.deleteTask(taskList[0])
            taskList = taskList.toMutableList().apply {
                this.removeAt(0)
            }
            // TODO:適切なnotify関数に切り替える。
            setRecyclerViewAdapter(taskList)
        }

        Toast.makeText(this, "delete button clicked", Toast.LENGTH_SHORT).show()

    }

}