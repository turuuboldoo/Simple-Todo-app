package mn.turbo.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mn.turbo.common.Resource
import mn.turbo.data.remote.dto.Todo
import mn.turbo.data.repository.TodoRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    init {
        getTodoStateFlow()
    }

    private val _todo = MutableStateFlow<Resource<List<Todo>>>(Resource.Loading())
    val todo = _todo.asStateFlow()

    private fun getTodoStateFlow() =
        viewModelScope.launch {
            val todos = repository.getTodoList()
            if (todos.isNotEmpty()) {
                _todo.value = Resource.Success(todos)
            } else {
                _todo.value = Resource.Error("Somethings wrong?")
            }
        }

    fun update(todo: Todo) =
        viewModelScope.launch {
            repository.update(todo)
        }
}
