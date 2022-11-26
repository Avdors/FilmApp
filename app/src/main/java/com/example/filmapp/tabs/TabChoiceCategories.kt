package com.example.filmapp.tabs

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmapp.R
import com.example.filmapp.data.DataBase
import com.example.filmapp.databinding.TabChoiceCategoriesBinding
import com.example.filmapp.model.CategoryModel
import com.example.filmapp.repositories.CategoryRepository
import com.example.filmapp.viewModels.CategoryFactory
import com.example.filmapp.viewModels.CategoryViewModel

class TabChoiceCategories : Fragment() {
    private var binding: TabChoiceCategoriesBinding? = null
    private var categoryRepository: CategoryRepository? = null
    private var categoryViewModel: CategoryViewModel? = null
    private var categoryFactory: CategoryFactory? = null
    private var categoryAdapter: CategoryChoiceAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = TabChoiceCategoriesBinding.inflate(inflater,container,false)

        val categoriesDao = DataBase.getInstance((context as FragmentActivity).application).categoryDao
        categoryRepository = CategoryRepository(categoriesDao)
        categoryFactory = CategoryFactory(categoryRepository!!)
        categoryViewModel = ViewModelProvider(this, categoryFactory!!).get(CategoryViewModel::class.java)

        initRecyclerCategories()
        displayCategories()


        return binding?.root
    }

    private fun initRecyclerCategories(){
        binding?.recyclerCategories?.layoutManager = LinearLayoutManager(context)
        categoryAdapter = CategoryChoiceAdapter({ categoryModel : CategoryModel -> choiceCategory(categoryModel)})
        binding?.recyclerCategories?.adapter = categoryAdapter
    }

    private fun displayCategories(){
        categoryViewModel?.categories?.observe(viewLifecycleOwner, Observer {
            categoryAdapter?.setList(it)
            categoryAdapter?.notifyDataSetChanged()
        })
    }

    private fun choiceCategory(categoryModel: CategoryModel){
        val tabPanel = TabPanel()
        val parameters = Bundle()
        parameters.putString("idCategory", categoryModel.id.toString())
        parameters.putString("nameCategory", categoryModel.name)
        tabPanel.arguments = parameters


        (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.content, TabPanel()).commit()
    }




}