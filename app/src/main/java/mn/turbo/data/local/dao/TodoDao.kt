package mn.turbo.data.local.dao

import androidx.room.*
import mn.turbo.data.local.entity.TodoEntity

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoTodos(todos: List<TodoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoTodo(todo: TodoEntity)

    @Update(entity = TodoEntity::class)
    suspend fun updateTodo(todo: TodoEntity)

    @Query("select * from todo")
    suspend fun selectAllFromTodo(): MutableList<TodoEntity>
}
