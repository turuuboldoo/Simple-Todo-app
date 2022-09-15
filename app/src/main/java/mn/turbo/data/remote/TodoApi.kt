package mn.turbo.data.remote

import mn.turbo.data.remote.dto.Todo
import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {
    @GET("/todos")
    suspend fun fetchSomething(): Response<List<Todo>>
}
