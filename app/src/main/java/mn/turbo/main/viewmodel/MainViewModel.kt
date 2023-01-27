package mn.turbo.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import mn.turbo.data.repository.TodoRepository
import mn.turbo.common.Resource
import mn.turbo.data.remote.dto.Todo
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
            val response = repository.getTodoList()
            if (response.isSuccessful) {
                _todo.value = Resource.Success(response.body())
            } else {
                _todo.value = Resource.Error("Somethings wrong?")
            }
        }
}
