package com.example.studycompose

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindContentRepository(impl: ContentRepositoryImpl): ContentRepository
}

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideContentRoomDatabase(@ApplicationContext context: Context): ContentRoomDatabase {
        return Room.databaseBuilder(
            context,
            ContentRoomDatabase::class.java,
            "todo.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideContentDao(db: ContentRoomDatabase): ContentDao {
        return db.contentDao()
    }
}
