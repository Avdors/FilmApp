package com.example.filmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.filmapp.databinding.ActivityMainBinding
import com.example.filmapp.tabs.TabCategories
import com.example.filmapp.tabs.TabFilms
import com.example.filmapp.tabs.TabFilters
import com.example.filmapp.tabs.TabPanel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    //  BottomNavigationView.OnNavigationItemSelectedListener
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportFragmentManager.beginTransaction().replace(R.id.content, TabPanel()).commit()

        binding?.bottomNav?.setOnNavigationItemSelectedListener(this)
        binding?.bottomNav?.selectedItemId = R.id.panelItemBottomNav
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.panelItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, TabPanel()).commit()
            R.id.catalogFilmsItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, TabFilms()).commit()
            R.id.catalogActionItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, TabFilters()).commit()
            R.id.catalogCategoriesItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, TabCategories()).commit()
        }
        return true
    }
}