package mn.turbo.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mn.turbo.data.local.dao.TodoDao
import mn.turbo.data.remote.TodoApi
import mn.turbo.data.remote.dto.Todo

class DefaultTodoRepository(
    private val api: TodoApi,
    private val dao: TodoDao
) : TodoRepository {
    override suspend fun getTodoList(): List<Todo> {
        val todos: List<Todo> = dao.selectAllFromTodo().map {
            it.toTodo()
        }

        return todos.ifEmpty {
            val request = api.fetchSomething()

            if (request.isSuccessful) {
                val remoteTodos = request.body() ?: mutableListOf()

                withContext(Dispatchers.Default) {
                    dao.insertIntoTodos(
                        remoteTodos.map {
                            it.toTodoEntity()
                        }
                    )
                }

                remoteTodos
            } else {
                mutableListOf()
            }
        }
    }
}
