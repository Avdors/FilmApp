package com.example.filmapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmapp.model.CategoryModel
import com.example.filmapp.repositories.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository : CategoryRepository) : ViewModel(){

    val categories = categoryRepository.categories

    fun insert(categoryModel: CategoryModel) = viewModelScope.launch{
        categoryRepository.insertCategory(categoryModel)
    }
    fun updateCategory(categoryModel: CategoryModel) = viewModelScope.launch {
        categoryRepository.updateCategory(categoryModel)
    }
    fun deleteCategory(categoryModel: CategoryModel) = viewModelScope.launch {
        categoryRepository.deleteCategory(categoryModel)
    }
    fun deleteAllCategories()= viewModelScope.launch {
        categoryRepository.deleteAllCategories()
    }

    fun startInsert(nameCategory:String){
        insert(CategoryModel(0,nameCategory))
    }

    fun startUpdateCategory(idCategory:Int,nameCategory:String){
        updateCategory(CategoryModel(idCategory,nameCategory))
    }

}