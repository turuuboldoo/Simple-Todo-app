package mn.turbo.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import mn.turbo.data.remote.dto.Todo

@Entity("todo")
data class TodoEntity(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "completed")
    var completed: Boolean,
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
) {
    fun toTodo() = Todo(id, title, completed)
}
