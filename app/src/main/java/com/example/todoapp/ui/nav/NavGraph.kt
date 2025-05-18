package com.example.todoapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.ui.todo_list.TodoListScreen
import com.example.todoapp.ui.todo_detail.TodoDetailScreen
import com.example.todoapp.ui.todo_list.TodoListViewModel
import com.example.todoapp.ui.todo_detail.TodoDetailViewModel

@Composable
fun NavGraph(
    listViewModel: TodoListViewModel,
    detailViewModel: TodoDetailViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "todo_list"
    ) {
        composable("todo_list") {
            TodoListScreen(
                viewModel = listViewModel,
                onTodoClick = { todoId ->
                    navController.navigate("todo_detail/$todoId")
                }
            )
        }

        composable(
            route = "todo_detail/{todoId}",
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId") ?: -1
            TodoDetailScreen(
                viewModel = detailViewModel,
                todoId = todoId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
