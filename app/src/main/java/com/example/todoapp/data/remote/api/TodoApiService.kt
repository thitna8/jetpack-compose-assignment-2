package com.example.todoapp.data.remote.api

import com.example.todoapp.data.remote.model.Todo
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>
}
