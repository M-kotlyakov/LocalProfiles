package com.example.localprofiles.presentation.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.localprofiles.R
import com.example.localprofiles.databinding.FragmentPersonalAccountBinding
import com.example.localprofiles.presentation.factory.ViewModelFactory
import com.example.localprofiles.presentation.fragments.AddProfileFragment.Companion.profilePhoto
import com.example.localprofiles.presentation.viewModels.PersonalAccountViewModel
import com.squareup.picasso.Picasso
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import java.io.IOException
import java.util.regex.Pattern


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
        pickImage()
        phoneFormatter()
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

    private fun pickImage() {
        binding.chooseAvatarBtn.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, PICK_IMAGE)
            vm.resetClickStatusTrue()
        }
        binding.defineAvatar.setOnClickListener {
            Picasso.get()
                .load(profilePhoto.random())
                .into(binding.avatarId)
            vm.resetClickStatusTrue()
        }
    }

    private fun phoneFormatter() {
        val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
        mask.isHideHardcodedHead = false // default value

        val formatWatcher: FormatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(binding.inputEditTextPhone)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var bitmap: Bitmap? = null
        val image = binding.avatarId

        when(requestCode) {
            PICK_IMAGE -> {
                if (resultCode == RESULT_OK) {
                    val selectedImage: Uri? = data?.data
                    try {
                        bitmap =
                            MediaStore.Images.Media.getBitmap(activity?.contentResolver, selectedImage)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    image.setImageBitmap(bitmap)
                    vm.resetClickStatusTrue()
                }
            }
        }

        val selectedImage: Uri? = data?.data
        image.setImageURI(selectedImage)
    }

    private fun clickListener() {
        binding.saveBtn.setOnClickListener {
            val image: Bitmap? = if(vm.statusOfChoose()) {
                binding.avatarId.drawToBitmap(Bitmap.Config.ARGB_8888)
            } else {
                null
            }

            val isValid = vm.checkEmail(binding.inputEditTextEmail.text.toString())
            if (isValid)  {
                val item = args.profileItem?.copy(
                    image = image,
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
            } else {
                binding.inputEditTextEmail.error = getString(R.string.error_email_incorrect)
            }
        }
    }

    companion object {

        private const val PICK_IMAGE = 100
    }
}