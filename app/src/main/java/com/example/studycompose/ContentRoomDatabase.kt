package com.example.studycompose

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Content::class], version = 1)
abstract class ContentRoomDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao

//    companion object {
//        // Singleton prevents multiple instances of database opening at the
//        // same time.
//        @Volatile
//        private var INSTANCE: ContentRoomDatabase? = null
//
//        fun getDatabase(context: Context): ContentRoomDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    ContentRoomDatabase::class.java,
//                    "content_database"
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
//    }
}
