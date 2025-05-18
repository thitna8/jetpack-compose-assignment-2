package com.example.todoapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.data.local.entities.Todo
import com.example.todoapp.data.local.dao.TodoDao

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
