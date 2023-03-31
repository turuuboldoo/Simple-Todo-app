package mn.turbo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import mn.turbo.data.local.TodoEntity
import mn.turbo.data.local.dao.TodoDao

@Database(
    entities = [TodoEntity::class],
    version = 1
)
abstract class TodoDatabase : RoomDatabase() {
    abstract val todoDao:TodoDao
}
