package com.example.todoapp.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.local.entities.Todo
import com.example.todoapp.data.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    init {
        viewModelScope.launch {
            try {

                val remoteTodos = repository.fetchTodosFromApi()


                remoteTodos.forEach {
                    repository.insertTodo(it)
                }

            } catch (e: Exception) {

                e.printStackTrace()
            }


            repository.getTodos().collect {
                _todos.value = it
            }
        }
    }


    fun deleteTodo(todo: Todo) = viewModelScope.launch {
        repository.deleteTodo(todo)
    }
}
