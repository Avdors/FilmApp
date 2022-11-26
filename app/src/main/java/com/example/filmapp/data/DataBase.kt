package com.example.filmapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.filmapp.model.CategoryModel
import com.example.filmapp.model.FilmModel

@Database(entities = [CategoryModel::class, FilmModel::class], version = 1)
abstract class DataBase: RoomDatabase() {
    abstract val filmDao : FilmDao
    abstract val categoryDao : CategoryDao

    companion object{
        @Volatile
        private var INSTANSE : com.example.filmapp.data.DataBase? = null
        fun getInstance(context: Context):com.example.filmapp.data.DataBase{
            synchronized(this){
                var instance = INSTANSE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        com.example.filmapp.data.DataBase::class.java,
                        "database"
                    ).build()
                }
                return instance
            }
        }
    }
}