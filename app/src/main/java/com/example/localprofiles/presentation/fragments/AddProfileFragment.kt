package com.example.localprofiles.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.localprofiles.R
import com.example.localprofiles.databinding.FragmentAddProfileBinding
import com.example.localprofiles.domain.ProfileItem
import com.example.localprofiles.presentation.factory.ViewModelFactory
import com.example.localprofiles.presentation.viewModels.AddProfileViewModel
import kotlinx.coroutines.DelicateCoroutinesApi

class AddProfileFragment : Fragment() {


    private val viewModelFactory by lazy {
        ViewModelFactory(requireActivity().application)
    }

    private val vm by lazy {
        ViewModelProvider(this, viewModelFactory)[AddProfileViewModel::class.java]
    }

    private var _binding: FragmentAddProfileBinding? = null
    private val binding: FragmentAddProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addItem()
    }

    private fun addItem() {
        binding.addBtn.setOnClickListener {
            vm.addItemToDb(
                name = binding.inputEditTextUsername.text.toString(),
                surname =  binding.inputEditTextSurname.text.toString(),
                email = binding.inputEditTextEmail.text.toString(),
                dateOfBirth = binding.inputEditTextDateOfBirth.text.toString(),
                description = binding.inputEditTextDescription.text.toString()
            )
            findNavController().navigate(R.id.action_addProfileFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}