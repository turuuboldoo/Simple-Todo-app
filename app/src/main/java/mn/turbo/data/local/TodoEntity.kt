package mn.turbo.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoEntity(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "completed")
    var completed: Boolean,
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
)
