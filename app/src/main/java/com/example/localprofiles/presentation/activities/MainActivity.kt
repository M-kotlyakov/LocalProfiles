package com.example.localprofiles.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.localprofiles.R
import com.example.localprofiles.databinding.ActivityMainBinding
import com.example.localprofiles.presentation.ProfilesApp

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    private var navController: NavController? = null

    private val component by lazy {
        (application as ProfilesApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomBarNavigation()
    }

    private fun setupBottomBarNavigation() {
        binding.bottomNavigation.apply {
            navController = (supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment).navController
            setupWithNavController(this, navController!!)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}