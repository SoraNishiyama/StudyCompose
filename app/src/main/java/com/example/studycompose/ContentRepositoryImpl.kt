package com.example.studycompose

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDao: ContentDao
) : ContentRepository {

    override fun getAll(): Flow<List<Content>> {
        return contentDao.getAlphabetizedContents()
    }

    override suspend fun insert(content: Content) {
        contentDao.insert(content)
    }
}
