package mn.turbo.data.repository

import mn.turbo.data.remote.TodoApi

class DefaultTodoRepository(private val api: TodoApi) : TodoRepository {
    override suspend fun getTodoList() = api.fetchSomething()
}
