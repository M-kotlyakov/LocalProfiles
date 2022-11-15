package com.example.localprofiles.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.localprofiles.R
import com.example.localprofiles.data.ProfileItemDbModel
import com.example.localprofiles.databinding.ActivityMainBinding
import com.example.localprofiles.presentation.fragments.AddProfileFragment
import com.example.localprofiles.presentation.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomBarNavigation()
    }

    private fun setupBottomBarNavigation() {
        binding.bottomNavigation.apply {
            navController = (supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment).navController
            /*val inflater = navController?.navInflater
            val graph = inflater?.inflate(R.navigation.bottom_bar_navigation)
            graph?.setStartDestination(R.id.personalAccountFragment)
            if (graph != null) {
                navController?.graph = graph
            }*/
            setupWithNavController(this, navController!!)
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}