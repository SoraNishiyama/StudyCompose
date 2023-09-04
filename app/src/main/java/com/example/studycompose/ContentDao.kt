package com.example.studycompose

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentDao {
    @Query("SELECT * FROM content_table ORDER BY content ASC")
    fun getAlphabetizedContents(): Flow<List<Content>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(content: Content)

    @Query("DELETE FROM content_table")
    suspend fun deleteAll()
}
