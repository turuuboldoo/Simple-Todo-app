package mn.turbo.data.repository

import mn.turbo.data.remote.dto.Todo
import retrofit2.Response

interface TodoRepository {
    suspend fun getTodoList(): Response<List<Todo>>
}