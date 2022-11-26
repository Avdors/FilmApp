package com.example.filmapp.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmapp.databinding.CategoryItemBinding
import com.example.filmapp.model.CategoryModel

class CategoryAdapter(private val deleteCategory:(CategoryModel)->Unit,
                      private val editCategory:(CategoryModel)->Unit): RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    private val categoriesList = ArrayList<CategoryModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding : CategoryItemBinding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoriesList[position], deleteCategory, editCategory)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun setList(categories: List<CategoryModel>) {
        categoriesList.clear()
        categoriesList.addAll(categories)

    }
    class CategoryHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(
            categories: CategoryModel,
            deleteCategory: (CategoryModel) -> Unit,
            editCategory: (CategoryModel) -> Unit
        ){
            binding.idCategory.text = categories.id.toString()
            binding.nameCategory.text = categories.name

            binding.editCategory.setOnClickListener(View.OnClickListener {
                editCategory(categories)
            })
            binding.deleteCategory.setOnClickListener(View.OnClickListener {
                deleteCategory(categories)
            })
        }

    }
}