package com.example.studycompose

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "content_table")
data class Content(
    @PrimaryKey
    val content: String
)
