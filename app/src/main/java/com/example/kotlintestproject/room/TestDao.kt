package com.example.testkotlinproject.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TestDao {
    @Query("SELECT * FROM $DATABASE_NAME")
    fun getTestData(): List<TestModel>

    @Query("SELECT * FROM $DATABASE_NAME WHERE id =:id")
    fun getSingleTestData(id: Int): TestModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTestData(vararg testData: TestModel)

    @Query("DELETE FROM $DATABASE_NAME")
    suspend fun deleteAllTestData()
}