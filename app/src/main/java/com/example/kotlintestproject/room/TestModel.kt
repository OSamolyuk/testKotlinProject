package com.example.testkotlinproject.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DATABASE_NAME)
class TestModel(@PrimaryKey val id: Int, val data: String)