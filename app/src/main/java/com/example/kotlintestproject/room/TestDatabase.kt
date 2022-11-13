package com.example.testkotlinproject.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "test_database"

@Database(
    entities = [TestModel::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class TestDatabase : RoomDatabase() {

    abstract fun testDao(): TestDao

    companion object {
        fun getDatabase(context: Context): TestDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                TestDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
