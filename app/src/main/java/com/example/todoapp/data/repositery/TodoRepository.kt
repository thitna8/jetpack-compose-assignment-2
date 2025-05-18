package com.example.todoapp.data.repository

import com.example.todoapp.data.local.dao.TodoDao
import com.example.todoapp.data.local.entities.Todo
import com.example.todoapp.data.remote.api.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val todoDao: TodoDao
) {
    fun getTodos(): Flow<List<Todo>> = todoDao.getAllTodos()

    suspend fun insertTodo(todo: Todo) = todoDao.insertTodo(todo)

    suspend fun deleteTodo(todo: Todo) = todoDao.deleteTodo(todo)


    suspend fun fetchTodosFromApi(): List<Todo> {
        val remote = RetrofitInstance.api.getTodos()


        return remote.map {
            Todo(
                id = it.id,
                title = it.title,
                description = "",
                isDone = it.completed
            )
        }
    }
}

