package com.example.localprofiles.presentation.fragments

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.localprofiles.R
import com.example.localprofiles.databinding.FragmentPersonalAccountBinding
import com.example.localprofiles.presentation.factory.ViewModelFactory
import com.example.localprofiles.presentation.viewModels.PersonalAccountViewModel

class PersonalAccountFragment : Fragment() {

    private val args by navArgs<PersonalAccountFragmentArgs>()

    private val viewModelFactory by lazy {
        ViewModelFactory(requireActivity().application)
    }

    private val vm by lazy {
        ViewModelProvider(this, viewModelFactory)[PersonalAccountViewModel::class.java]
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewsFromArgs()
        clickListener()
    }

    private fun initViewsFromArgs() {
        val item = args.profileItem
        with(binding) {
            inputEditTextUsername.setText(item?.name)
            inputEditTextSurname.setText(item?.surname)
            inputEditTextEmail.setText(item?.email)
            inputEditTextPhone.setText(item?.numberPhone)
            inputEditTextDateOfBirth.setText(item?.dateOfBirth)
            inputEditTextDescription.setText(item?.description)
        }
    }

    private fun clickListener() {
        binding.saveBtn.setOnClickListener {
            val item = args.profileItem?.copy(
                name = binding.inputEditTextUsername.text.toString(),
                surname = binding.inputEditTextSurname.text.toString(),
                email = binding.inputEditTextEmail.text.toString(),
                numberPhone = binding.inputEditTextPhone.text.toString(),
                dateOfBirth = binding.inputEditTextDateOfBirth.text.toString(),
                description = binding.inputEditTextDescription.text.toString(),
            )
            if (item != null) {
                vm.editValues(item)
            }
            findNavController().navigate(R.id.action_personalAccountFragment_to_homeFragment)
        }
    }

    companion object {

        const val PROFILE_ITEM_KEY = "profile_item_key"

        /*const val USERNAME_KEY = "username_mode"
        const val SURNAME_KEY = "surname_mode"
        const val EMAIL_KEY = "email_mode"
        const val NUMBER_PHONE_KEY = "number_phone_mode"
        const val DATE_OF_BIRTH_KEY = "date_of_birth_mode"
        const val DESCRIPTION_KEY = "description_mode"
        const val PASSWORD_KEY = "password_mode"
        const val UNKNOWN = ""*/

        /*fun newInstanceItem(login: String, password: String): PersonalAccountFragment {
            return PersonalAccountFragment().apply {
                arguments = Bundle().apply {
                    putString(LOGIN_KEY, login)
                    putString(PASSWORD_KEY, password)
                }
            }
        }*/
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