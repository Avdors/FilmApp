package com.example.filmapp.repositories

import androidx.lifecycle.LiveData
import com.example.filmapp.data.FilmDao
import com.example.filmapp.model.FilmModel

class FilmRepository(private val filmDao: FilmDao) {
    val film = filmDao.getAllFilms()

    fun getFilter(): LiveData<List<FilmModel>> {
        return filmDao.getFilter()
    }
    suspend fun insertFilm(filmModel: FilmModel){
        filmDao.insertFilm(filmModel)
    }
    suspend fun updateFilm(filmModel: FilmModel){
        filmDao.updateFilm(filmModel)
    }
    suspend fun deleteFilm(filmModel: FilmModel){
        filmDao.deleteFilm(filmModel)
    }
    suspend fun deleteAllFilms(){
        filmDao.deleteAllFilms()
    }

}