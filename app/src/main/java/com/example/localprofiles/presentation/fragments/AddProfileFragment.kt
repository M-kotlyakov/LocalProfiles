package com.example.localprofiles.presentation.fragments

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.localprofiles.R
import com.example.localprofiles.data.ProfileListRepositoryImpl
import com.example.localprofiles.databinding.FragmentAddProfileBinding
import com.example.localprofiles.domain.AddProfileItemUseCase
import com.example.localprofiles.domain.ProfileItem
import com.example.localprofiles.domain.ProfileListRepository
import com.example.localprofiles.presentation.factory.AddProfileViewModelFactory
import com.example.localprofiles.presentation.viewModels.AddProfileViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddProfileFragment : Fragment() {

    private val viewModelFactory by lazy {
        AddProfileViewModelFactory(requireActivity().application)
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

    @OptIn(DelicateCoroutinesApi::class)
    private fun addItem() {

        val item = ProfileItem(
            name = binding.inputEditTextUsername.text.toString(),
            surname = binding.inputEditTextSurname.text.toString(),
            email = binding.inputEditTextEmail.text.toString(),
            dateOfBirth = binding.inputEditTextDateOfBirth.text.toString(),
            numberPhone = "89258872159",
            description = binding.inputEditTextDescription.text.toString(),
            password = "111"
        )
        binding.addBtn.setOnClickListener {
            vm.addItemToDb(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}