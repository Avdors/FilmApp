package com.example.filmapp.tabs

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.filmapp.R
import com.example.filmapp.data.DataBase
import com.example.filmapp.databinding.PanelEditCategoryBinding
import com.example.filmapp.repositories.CategoryRepository
import com.example.filmapp.viewModels.CategoryFactory
import com.example.filmapp.viewModels.CategoryViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PanelEditCategory : BottomSheetDialogFragment(), View.OnKeyListener {
    private var binding: PanelEditCategoryBinding? = null
    private var categoryRepository: CategoryRepository? = null
    private var categoryViewModel: CategoryViewModel? = null
    private var factory: CategoryFactory? = null
    private var idCategory: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PanelEditCategoryBinding.inflate(inflater, container, false)

        idCategory = arguments?.getString("idCategory")?.toInt()
        binding?.editCategory?.setText(arguments?.getString("nameCategory").toString())

        val categoriesDao = DataBase.getInstance((context as FragmentActivity).application).categoryDao
        categoryRepository = CategoryRepository(categoriesDao)
        factory = CategoryFactory(categoryRepository!!)
        categoryViewModel = ViewModelProvider(this, factory!!).get(CategoryViewModel::class.java)

        binding?.editCategory?.setOnKeyListener(this)
        return binding?.root
    }

    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when(view.id){
            R.id.editCategory -> {
                if(keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER){
                    categoryViewModel?.startUpdateCategory(idCategory.toString().toInt(), binding?.editCategory?.text.toString()!!)
                    binding?.editCategory?.setText("")
                    dismiss()
                    (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.content, TabCategories()).commit()
                    return true
                }
            }
        }

        return false
    }


}