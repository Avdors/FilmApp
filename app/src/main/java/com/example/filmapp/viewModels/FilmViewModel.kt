package com.example.filmapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.filmapp.model.FilmModel
import com.example.filmapp.repositories.FilmRepository

import kotlinx.coroutines.launch

class FilmViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    val films = filmRepository.film

    fun getFilter(): LiveData<List<FilmModel>> {
        return filmRepository.getFilter()
    }

    fun insertFilm(filmModel: FilmModel) = viewModelScope.launch {
        filmRepository.insertFilm(filmModel)
    }
    fun updateFilm(filmModel: FilmModel) = viewModelScope.launch {
        filmRepository.updateFilm(filmModel)
    }
    fun deleteFilm(filmModel: FilmModel) = viewModelScope.launch {
        filmRepository.deleteFilm(filmModel)
    }
    fun deleteAllFilms() = viewModelScope.launch {
        filmRepository.deleteAllFilms()
    }

    fun startInsert(nameFilm : String, categoryFilm:String, timeFilm:String, infoFilm:String){
        insertFilm(FilmModel(0, nameFilm, categoryFilm, timeFilm, infoFilm))
    }
    fun startUpdateFilm(idFilm:Int,nameFilm : String, categoryFilm:String, timeFilm:String, infoFilm:String){
        updateFilm(FilmModel(idFilm, nameFilm, categoryFilm, timeFilm, infoFilm))
    }
}