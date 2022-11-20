package com.example.localprofiles.presentation.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.localprofiles.R
import com.example.localprofiles.databinding.FragmentAddProfileBinding
import com.example.localprofiles.presentation.factory.ViewModelFactory
import com.example.localprofiles.presentation.viewModels.AddProfileViewModel
import com.squareup.picasso.Picasso
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import java.io.IOException


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
        pickImage()
        phoneFormatter()
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

    private fun phoneFormatter() {
        val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
        mask.isHideHardcodedHead = false // default value

        val formatWatcher: FormatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(binding.inputEditTextPhone)
    }

    private fun addItem() {
        binding.addBtn.setOnClickListener {
            val image: Bitmap? = if(vm.statusOfChoose()) {
                binding.avatarId.drawToBitmap(Bitmap.Config.ARGB_8888)
            } else {
                null
            }

            val isValid = vm.checkEmail(binding.inputEditTextEmail.text.toString())
            if (isValid)  {
                vm.addItemToDb(
                    image = image,
                    name = binding.inputEditTextUsername.text.toString(),
                    surname =  binding.inputEditTextSurname.text.toString(),
                    email = binding.inputEditTextEmail.text.toString(),
                    dateOfBirth = binding.inputEditTextDateOfBirth.text.toString(),
                    description = binding.inputEditTextDescription.text.toString()
                )
                findNavController().navigate(R.id.action_addProfileFragment_to_homeFragment)
            } else {
                binding.inputEditTextEmail.error = getString(R.string.error_email_incorrect)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val PICK_IMAGE = 100

        val profilePhoto = listOf(
            "https://cdn2.vectorstock.com/i/1000x1000/20/76/man-avatar-profile-vector-21372076.jpg",
            "https://cdn2.vectorstock.com/i/1000x1000/17/61/male-avatar-profile-picture-vector-10211761.jpg",
            "https://png.pngtree.com/element_our/png_detail/20181102/avatar-profile-logo-vector-emblem-illustration-modern-illustration-png_227486.jpg",
            "https://cdn2.vectorstock.com/i/1000x1000/38/21/male-face-avatar-logo-template-pictograph-vector-11333821.jpg"
        )
    }
}