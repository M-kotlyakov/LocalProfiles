package com.example.localprofiles.presentation.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.localprofiles.databinding.FragmentRegistrationBinding
import com.example.localprofiles.presentation.activities.MainActivity


class RegistrationFragment : Fragment() {

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

        binding.buttonLogin.setOnClickListener {
            requireActivity().startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finish()
        }
//        launchPersonalFragment()
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