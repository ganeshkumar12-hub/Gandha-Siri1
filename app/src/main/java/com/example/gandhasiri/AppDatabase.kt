package com.example.gandhasiri

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tree::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun treeDao(): TreeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gandha_siri_db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration() // wipes old db, rebuilds fresh
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}