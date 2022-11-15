package com.example.localprofiles.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.localprofiles.R
import com.example.localprofiles.databinding.FragmentAuthBinding
import com.example.localprofiles.presentation.activities.MainActivity
import com.example.localprofiles.presentation.factory.ViewModelFactory
import com.example.localprofiles.presentation.viewModels.AuthViewModel
import com.example.localprofiles.presentation.viewModels.HomeViewModel

class AuthFragment : Fragment() {

    private val viewModelFactory by lazy {
        ViewModelFactory(requireActivity().application)
    }

    private val vm by lazy {
        ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
    }

    private var _binding: FragmentAuthBinding? = null
    private val binding: FragmentAuthBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchRightFragment()
        addTextChangeListeners()
    }

    private fun addTextChangeListeners() {
        binding.inputEditTextUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                vm.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        binding.inputEditTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                vm.resetErrorInputPassword()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun launchRightFragment() {

        binding.buttonLogin.setOnClickListener {
            vm.getProfile(
                binding.inputEditTextUsername.text.toString(),
                binding.inputEditTextPassword.text.toString()
            ).observe(viewLifecycleOwner) {
                    if (it.name != null) {
                        requireActivity().startActivity(
                            Intent(
                                requireActivity(),
                                MainActivity::class.java
                            )
                        )
                        requireActivity().finish()
                    }
                }
        }

        binding.buttonRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registrationFragment)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}