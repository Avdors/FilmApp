package com.example.filmapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
//, indices = [Index(value = ["category_name"], unique = true)]
@Entity(tableName = "category_data_table")
data class CategoryModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    var id : Int,

    @ColumnInfo(name = "category_name")
    var name: String

)