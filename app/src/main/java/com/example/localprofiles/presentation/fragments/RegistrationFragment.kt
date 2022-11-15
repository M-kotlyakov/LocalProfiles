package com.example.localprofiles.presentation.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.localprofiles.R
import com.example.localprofiles.databinding.FragmentRegistrationBinding
import com.example.localprofiles.presentation.activities.MainActivity
import com.example.localprofiles.presentation.factory.ViewModelFactory
import com.example.localprofiles.presentation.viewModels.RegistrationViewModel


class RegistrationFragment : Fragment() {

    private val viewModelFactory by lazy {
        ViewModelFactory(requireActivity().application)
    }

    private val vm by lazy {
        ViewModelProvider(this, viewModelFactory)[RegistrationViewModel::class.java]
    }

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchHomeScreen()
//        binding.buttonLogin.setOnClickListener {
//            requireActivity().startActivity(Intent(requireActivity(), MainActivity::class.java))
//            requireActivity().finish()
//        }
    }

    private fun launchHomeScreen() {
        binding.buttonLogin.setOnClickListener {
            vm.registrationProfile(
                name = binding.inputEditTextUsername.text.toString(),
                email = binding.inputEditTextEmail.text.toString(),
                password = binding.inputEditTextPassword.text.toString(),
                rePassword = binding.inputEditTextRePassword.text.toString()
            )
//            childFragmentManager.beginTransaction()
//                .addToBackStack(null)
//                .replace(R.id.main_nav_host_fragment, HomeFragment())
//                .commit()
            findNavController().navigate(R.id.action_registrationFragment_to_bottom_bar_navigation)
        }
    }

//    private fun launchPersonalFragment() {
//        binding.authButton.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_registrationFragment_to_personalAccountFragment,
//                bundleOf(
//                    PersonalAccountFragment.LOGIN_KEY to binding.loginEditText.text.toString(),
//                    PersonalAccountFragment.PASSWORD_KEY to binding.passwordEditText.text.toString()
//                )
//            )
//        }
//    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}