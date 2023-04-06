package mn.turbo.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mn.turbo.common.Const
import mn.turbo.data.local.dao.TodoDao
import mn.turbo.data.local.database.TodoDatabase
import mn.turbo.data.remote.TodoApi
import mn.turbo.data.repository.DefaultTodoRepository
import mn.turbo.data.repository.TodoRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): TodoApi {
        return Retrofit.Builder()
            .baseUrl(Const.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMyRepository(
        todoApi: TodoApi,
        dao: TodoDao
    ): TodoRepository {
        return DefaultTodoRepository(todoApi, dao)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ): TodoDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            TodoDatabase::class.java,
            Const.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(
        todoDatabase: TodoDatabase
    ): TodoDao {
        return todoDatabase.todoDao
    }
}
