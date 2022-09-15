package mn.turbo.common

import mn.turbo.data.remote.dto.Todo

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}

sealed class LatestNewsUiState {
    data class Success(var news: List<Todo>) : LatestNewsUiState()
    data class Error(var exception: Throwable) : LatestNewsUiState()
}
