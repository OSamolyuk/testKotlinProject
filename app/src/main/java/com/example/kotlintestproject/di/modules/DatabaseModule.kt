package com.example.testkotlinproject.modules

import android.content.Context
import com.example.testkotlinproject.room.TestDao
import com.example.testkotlinproject.room.TestDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : TestDatabase {
        return TestDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideDao(db : TestDatabase) : TestDao {
        return db.testDao()
    }
}