package com.example.filmapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.filmapp.model.FilmModel

@Dao
interface FilmDao {

    @Insert
    suspend fun insertFilm(filmModel: FilmModel)

    @Update
    suspend fun updateFilm(filmModel: FilmModel)

    @Delete
    suspend fun deleteFilm(filmModel: FilmModel)

    @Query("DELETE FROM film_data_table")
    suspend fun deleteAllFilms()

    @Query("SELECT * FROM film_data_table")
    fun getAllFilms(): LiveData<List<FilmModel>>

    @Query("SELECT * FROM film_data_table WHERE film_time = '120'")
    fun getFilter(): LiveData<List<FilmModel>>
}