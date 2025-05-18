package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.todoapp.data.local.db.AppDatabase
import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.ui.nav.NavGraph
import com.example.todoapp.ui.theme.TodoAppTheme
import com.example.todoapp.ui.todo_detail.TodoDetailViewModel
import com.example.todoapp.ui.todo_list.TodoListViewModel
import com.example.todoapp.ui.viewmodel.TodoViewModelFactory

class MainActivity : ComponentActivity() {

    @OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "todo-db"
        ).build()
        val repository = TodoRepository(db.todoDao())

        setContent {
            val factory = TodoViewModelFactory(repository)

            val listViewModel: TodoListViewModel = viewModel(factory = factory)
            val detailViewModel: TodoDetailViewModel = viewModel(factory = factory)

            TodoAppTheme {
                NavGraph(listViewModel, detailViewModel)
            }
        }

    }
}
