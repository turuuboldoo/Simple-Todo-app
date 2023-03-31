package mn.turbo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import mn.turbo.data.local.entity.TodoEntity

@Dao
interface TodoDao {
    @Insert
    suspend fun insertIntoTodos(todos: List<TodoEntity>)

    @Query("select * from todo")
    suspend fun selectAllFromTodo(): MutableList<TodoEntity>
}
