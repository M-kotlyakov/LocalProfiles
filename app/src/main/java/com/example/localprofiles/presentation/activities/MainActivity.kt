package com.example.localprofiles.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.localprofiles.R
import com.example.localprofiles.data.ProfileItemDbModel
import com.example.localprofiles.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

//    private lateinit var recyclerView: RecyclerView
//    private lateinit var homeAdapter: HomeAdapter
    private lateinit var profileFromDB: ArrayList<ProfileItemDbModel>
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        recyclerView = findViewById(R.id.recyclerView)

//        setupRecyclerView()
        setupBottomBarNavigation()
    }

    /*private fun setupRecyclerView() {
        homeAdapter = HomeAdapter(this)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = homeAdapter
        }
    }*/

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