package com.example.filmapp.repositories

import com.example.filmapp.data.CategoryDao
import com.example.filmapp.model.CategoryModel

class CategoryRepository(private val categoryDao: CategoryDao) {

    val categories = categoryDao.getAllCategories()

    suspend fun insertCategory(categoryModel: CategoryModel){
        categoryDao.insertCategory(categoryModel)
    }

    suspend fun updateCategory(categoryModel: CategoryModel){
        categoryDao.updateCategory(categoryModel)
    }
    suspend fun deleteCategory(categoryModel: CategoryModel){
        categoryDao.deleteCategory(categoryModel)
    }
    suspend fun deleteAllCategories(){
        categoryDao.deleteAllCategories()
    }
}