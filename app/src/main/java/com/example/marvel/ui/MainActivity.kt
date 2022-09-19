package com.example.marvel.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.core.base.BaseActivity
import com.example.core.manager.SearchViewManager
import com.example.marvel.R
import com.example.marvel.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        val configuration = AppBarConfiguration(
            setOf(
                R.id.charactersFragment,
                R.id.comicsFragment,
                R.id.creatorsFragment,
                R.id.seriesFragment
            )
        )

        setupActionBarWithNavController(navController, configuration)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): MainViewModel {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        return viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(com.example.core.R.menu.options_menu, menu)

        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(com.example.core.R.id.search).actionView as SearchView
        val search = menu.findItem(com.example.core.R.id.search)

        searchView.setSearchableInfo(
            searchManager.getSearchableInfo(componentName)
        )
        searchView.isIconifiedByDefault = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    SearchViewManager.searchQuery.value = query
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        search.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                searchView.requestFocus()
                Handler(Looper.getMainLooper()).post {
                    searchView.requestFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                    imm?.showSoftInput(searchView.findFocus(), 0)
                }
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                searchView.clearFocus()
                return true
            }
        })

        searchView.setOnQueryTextFocusChangeListener { _, newViewFocus ->
            if (!newViewFocus) {
                lifecycleScope.launch {
                    SearchViewManager.searchQuery.value = null
                }
            }
        }

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
