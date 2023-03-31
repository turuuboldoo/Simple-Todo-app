package mn.turbo.data.remote.dto

import mn.turbo.data.local.entity.TodoEntity

data class Todo(
    var id: Int,
    var title: String,
    var completed: Boolean
) {
    fun toTodoEntity() = TodoEntity(title, completed, id)
}