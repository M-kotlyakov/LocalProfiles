package com.example.localprofiles.presentation.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.localprofiles.R
import com.example.localprofiles.databinding.FragmentRegistrationBinding
import com.example.localprofiles.presentation.Common.log
import com.example.localprofiles.presentation.ProfilesApp
import com.example.localprofiles.presentation.activities.MainActivity
import com.example.localprofiles.presentation.factory.ViewModelFactory
import com.example.localprofiles.presentation.viewModels.RegistrationViewModel
import com.google.android.material.textfield.TextInputEditText
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val vm by lazy {
        ViewModelProvider(this, viewModelFactory)[RegistrationViewModel::class.java]
    }

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    private val component by lazy {
        (requireActivity().application as ProfilesApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner
        launchHomeScreen()
        addTextChangeListener()
    }

    private fun addTextChangeListener() {
        binding.inputEditTextPassword.textChangeListener { vm.resetErrorInputPassword() }
        binding.inputEditTextRePassword.textChangeListener { vm.resetErrorInputRePassword() }
        binding.inputEditTextUsername.textChangeListener { vm.resetErrorInputName() }
        binding.inputEditTextEmail.textChangeListener { vm.resetErrorInputEmail() }
    }

    private fun TextInputEditText.textChangeListener(block: () -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                block()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun launchHomeScreen() {
        binding.buttonLogin.setOnClickListener {
            val isValid = vm.checkEmail(binding.inputEditTextEmail.text.toString())
            if (isValid)  {
                vm.registrationProfile(
                    inputName = binding.inputEditTextUsername.text.toString(),
                    inputEmail = binding.inputEditTextEmail.text.toString(),
                    inputPassword = binding.inputEditTextPassword.text.toString(),
                    inputRePassword = binding.inputEditTextRePassword.text.toString()
                )
                vm.isSuccess.observe(viewLifecycleOwner) {
                    log("launchHomeScreen", "$it")
                    if (it) {
                        requireActivity().startActivity(
                            Intent(
                                requireActivity(),
                                MainActivity::class.java
                            )
                        )
                        requireActivity().finish()
                    }
                }
            } else {
                binding.inputEditTextEmail.error = getString(R.string.error_email_incorrect)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}