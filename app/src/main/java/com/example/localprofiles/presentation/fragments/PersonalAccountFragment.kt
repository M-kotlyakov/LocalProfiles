package com.example.localprofiles.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.localprofiles.databinding.FragmentPersonalAccountBinding

class PersonalAccountFragment : Fragment() {

    private var _binding: FragmentPersonalAccountBinding? = null
    private val binding: FragmentPersonalAccountBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

//    private var login: String = UNKNOWN
//    private var password: String = UNKNOWN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalAccountBinding.inflate(inflater, container, false)
        return binding.root
    }
/*
    private fun parseParams() {
        val args = requireArguments()
        if(!args.containsKey(LOGIN_KEY)) {
            throw RuntimeException("Param login mode is absent")
        }
        if(!args.containsKey(PASSWORD_KEY)) {
            throw RuntimeException("Param password mode is absent")
        }
        login = args.getString(LOGIN_KEY).toString()
        password = args.getString(PASSWORD_KEY).toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

         const val LOGIN_KEY = "login_mode"
         const val PASSWORD_KEY = "password_mode"
         const val UNKNOWN = ""

        fun newInstanceItem(login: String, password: String): PersonalAccountFragment {
            return PersonalAccountFragment().apply {
                arguments = Bundle().apply {
                    putString(LOGIN_KEY, login)
                    putString(PASSWORD_KEY, password)
                }
            }
        }
    }*/
}