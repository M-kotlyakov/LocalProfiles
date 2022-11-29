package com.example.localprofiles.presentation.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.localprofiles.R
import com.example.localprofiles.databinding.ActivityLoginBinding
import com.example.localprofiles.presentation.ProfilesApp
import com.example.localprofiles.presentation.factory.ViewModelFactory
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private val component by lazy {
        (application as ProfilesApp).component
    }

    private var _binding: ActivityLoginBinding? = null
    private val binding: ActivityLoginBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.login_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navController?.navigate(R.id.authFragment)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}