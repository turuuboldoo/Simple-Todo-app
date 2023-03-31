package mn.turbo.data.repository

import mn.turbo.data.remote.dto.Todo

interface TodoRepository {
    suspend fun getTodoList(): List<Todo>
    suspend fun update(todo: Todo)
}