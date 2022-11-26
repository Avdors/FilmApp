package com.example.filmapp.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmapp.databinding.CategoryChoiceBinding
import com.example.filmapp.model.CategoryModel

class CategoryChoiceAdapter(private val choiceCategory:(CategoryModel)->Unit): RecyclerView.Adapter<CategoryChoiceAdapter.CategoryHolder>() {
    private val categoriesList = ArrayList<CategoryModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding : CategoryChoiceBinding = CategoryChoiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding)
    }



    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun setList(categories: List<CategoryModel>) {
        categoriesList.clear()
        categoriesList.addAll(categories)

    }
    class CategoryHolder(val binding: CategoryChoiceBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(
            categories: CategoryModel,
            choiceCategory: (CategoryModel) -> Unit
        ){
            binding.idCategory.text = categories.id.toString()
            binding.nameCategory.text = categories.name

            binding.choiceCategory.setOnClickListener(View.OnClickListener {
                choiceCategory(categories)
            })

        }

    }



    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoriesList[position], choiceCategory)
    }
}