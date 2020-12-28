package com.jay.androidarchitecturesample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jay.androidarchitecturesample.model.Book

@Database(
    entities = [Book::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "Book.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return INSTANCE!!
        }

        fun destroyDataBase() {
            INSTANCE = null
        }

    }

}