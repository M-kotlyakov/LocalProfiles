package com.example.localprofiles.presentation.fragments

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.localprofiles.R
import com.example.localprofiles.databinding.FragmentAuthBinding
import com.example.localprofiles.presentation.activities.MainActivity
import com.example.localprofiles.presentation.factory.ViewModelFactory
import com.example.localprofiles.presentation.viewModels.AuthViewModel
import com.example.localprofiles.presentation.viewModels.HomeViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

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
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner
        launchRightFragment(view.context)
        addTextChangeListener()
    }

    private fun addTextChangeListener() {
        binding.inputEditTextUsername.textChangeListener { vm.resetErrorInputName() }
        binding.inputEditTextPassword.textChangeListener { vm.resetErrorInputPassword() }
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

    private fun launchRightFragment(context: Context) {
        binding.buttonLogin.setOnClickListener {
            vm.getProfile(
                binding.inputEditTextUsername.text.toString(),
                binding.inputEditTextPassword.text.toString()
            ).observe(viewLifecycleOwner) {
                try {
                    val name = it.name
                    val password = it.password

                    if (
                        name == binding.inputEditTextUsername.text.toString() &&
                        password == binding.inputEditTextPassword.text.toString()
                    ) {
                        requireActivity().startActivity(
                            Intent(
                                requireActivity(),
                                MainActivity::class.java
                            )
                        )
                        requireActivity().finish()
                    }
                } catch (e: NullPointerException) {
                    setupAlertDialog(context)
                }

            }
        }
        binding.buttonRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registrationFragment)
        }
    }

    private fun setupAlertDialog(context: Context) {
        val positiveButtonClick = { _: DialogInterface, _: Int->
            binding.inputEditTextUsername.setText("")
            binding.inputEditTextPassword.setText("")
        }
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Incorrect data")
        builder.setMessage("type name or password again")
        builder.setPositiveButton(R.string.okay, DialogInterface.OnClickListener(positiveButtonClick))
        builder.show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}