package com.example.filmapp.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmapp.databinding.FilmItemBinding
import com.example.filmapp.model.FilmModel

class FilmAdapter (private val deleteFilm:(FilmModel)->Unit,
                   private val editFilm:(FilmModel)->Unit) : RecyclerView.Adapter<FilmAdapter.FilmHolder>(){
    private val filmList = ArrayList<FilmModel>()


    class FilmHolder(val binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            filmModel: FilmModel,
            deleteFilm: (FilmModel) -> Unit,
            editFilm: (FilmModel) -> Unit
        ){
            binding.idFilm.text = filmModel.id.toString()
            binding.nameFilm.text = filmModel.name
            binding.categoryFilm.text = filmModel.category
            binding.timeFilm.text = filmModel.filmTime
            binding.infoFilm.text = filmModel.info

            binding.editFilm.setOnClickListener(View.OnClickListener {
                editFilm(filmModel) })
            binding.deleteFilm.setOnClickListener(View.OnClickListener {
                deleteFilm(filmModel)
            })
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val binding : FilmItemBinding = FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bind(filmList[position], deleteFilm, editFilm)
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    fun setList(films : List<FilmModel>){
        filmList.clear()
        filmList.addAll(films)
    }

}