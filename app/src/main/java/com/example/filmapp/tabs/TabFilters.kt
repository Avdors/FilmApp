package com.example.filmapp.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmapp.R
import com.example.filmapp.data.DataBase
import com.example.filmapp.databinding.TabFiltersBinding
import com.example.filmapp.model.FilmModel
import com.example.filmapp.repositories.FilmRepository
import com.example.filmapp.viewModels.FilmFactory
import com.example.filmapp.viewModels.FilmViewModel


class TabFilters : Fragment() {

    private var binding: TabFiltersBinding? = null
    private var filmRepository: FilmRepository? = null
    private var filmViewModel: FilmViewModel? = null
    private var filmFactory: FilmFactory? = null
    private var filmAdapter: FilmAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TabFiltersBinding.inflate(inflater, container, false)

        val filmDao = DataBase.getInstance((context as FragmentActivity).application).filmDao
        filmRepository = FilmRepository(filmDao)
        filmFactory = FilmFactory(filmRepository!!)
        filmViewModel = ViewModelProvider(this, filmFactory!!).get(FilmViewModel::class.java)
        initRecyclerFilterFilms()
        return binding?.root
    }

    private fun initRecyclerFilterFilms() {
        binding?.recyclerFilter?.layoutManager = LinearLayoutManager(context)
        filmAdapter = FilmAdapter({filmModel: FilmModel -> deleteFilm(filmModel) },
            {filmModel: FilmModel -> editFilm(filmModel)})
        binding?.recyclerFilter?.adapter = filmAdapter

        displayFilterFilms()
    }

    private fun editFilm(filmModel: FilmModel) {
        val panelEditFilm = PanelEditFilm()
        val parameters = Bundle()
        parameters.putString("idFilm", filmModel.id.toString())
        parameters.putString("nameFilm", filmModel.name)
        parameters.putString("categoryFilm", filmModel.category)
        parameters.putString("timeFilm", filmModel.filmTime)
        parameters.putString("infoFilm", filmModel.info)
        panelEditFilm.arguments = parameters

        panelEditFilm.show((context as FragmentActivity).supportFragmentManager, "editFilm")
    }

    private fun deleteFilm(filmModel: FilmModel) {
        filmViewModel?.deleteFilm(filmModel)
    }

    private fun displayFilterFilms() {
        filmViewModel?.getFilter()?.observe(viewLifecycleOwner, Observer {
            filmAdapter?.setList(it)
            filmAdapter?.notifyDataSetChanged()
        })
    }


}