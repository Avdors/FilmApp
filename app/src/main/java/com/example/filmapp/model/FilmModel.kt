package com.example.filmapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_data_table")
data class FilmModel (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "film_id")
    var id : Int,

    @ColumnInfo(name = "film_name")
    var name : String,

    @ColumnInfo(name = "film_category")
    var category : String,

    @ColumnInfo(name = "film_time")
    var filmTime : String,

    @ColumnInfo(name = "info")
    var info : String

)