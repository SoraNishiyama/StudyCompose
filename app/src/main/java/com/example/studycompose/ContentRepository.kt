package com.example.studycompose

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun getAll(): Flow<List<Content>>
    suspend fun insert(content: Content)

}
