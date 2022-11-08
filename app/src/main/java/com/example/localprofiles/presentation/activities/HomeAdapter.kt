package com.example.localprofiles.presentation.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.localprofiles.R
import com.example.localprofiles.data.ProfileItemDbModel

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ProfileViewHolder>() {

    private var profileList = ArrayList<ProfileItemDbModel>()

    init {
        profileList = arrayListOf(
            ProfileItemDbModel(
                1,
                "Max",
                "Kotlyakov",
                "max@mail.ru",
                "05.04.2002",
                "8999999999",
                "descr",
                "111"
            ),
            ProfileItemDbModel(
                2,
                "Max",
                "Kotlyakov",
                "max@mail.ru",
                "05.04.2002",
                "8999999999",
                "descr",
                "111"
            ),
            ProfileItemDbModel(
                3,
                "Max",
                "Kotlyakov",
                "max@mail.ru",
                "05.04.2002",
                "8999999999",
                "descr",
                "111"
            ),
            ProfileItemDbModel(
                4,
                "Max",
                "Kotlyakov",
                "max@mail.ru",
                "05.04.2002",
                "8999999999",
                "descr",
                "111"
            ),
            ProfileItemDbModel(
                5,
                "Max",
                "Kotlyakov",
                "max@mail.ru",
                "05.04.2002",
                "8999999999",
                "descr",
                "111"
            ),
            ProfileItemDbModel(
                6,
                "Max",
                "Kotlyakov",
                "max@mail.ru",
                "05.04.2002",
                "8999999999",
                "descr",
                "111"
            ),
            ProfileItemDbModel(
                7,
                "Max",
                "Kotlyakov",
                "max@mail.ru",
                "05.04.2002",
                "8999999999",
                "descr",
                "111"
            )
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_item, parent, false)
        return ProfileViewHolder(view)

        /*val binding = ProfileItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProfileViewHolder(binding)*/
    }

    override fun onBindViewHolder(viewHolder: ProfileViewHolder, position: Int) {
        val item = profileList[position]
        viewHolder.bind(item)
    }

    override fun getItemCount(): Int = profileList.size

    class ProfileViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var imageView: ImageView = view.findViewById(R.id.imageProfile)
        var usernameTextView: TextView = view.findViewById(R.id.usernameTextView)
        var emailInputTextView: TextView = view.findViewById(R.id.emailTextView)
        var birthdayInputTextView: TextView = view.findViewById(R.id.birthdayTextView)

        fun bind(profileItem: ProfileItemDbModel) {

            imageView.setImageResource(R.drawable.splash_logo)
            usernameTextView.text = profileItem.name
            emailInputTextView.text = profileItem.email
            birthdayInputTextView.text = profileItem.dateOfBirth
        }

       /* val imageView: ImageView
        val usernameInputEDitText: TextInputEditText
        val emailInputEDitText: TextInputEditText
        val birthdayInputEDitText: TextInputEditText

        init {
            imageView = view.findViewById(R.id.imageProfile)
            usernameInputEDitText = view.findViewById(R.id.inputEditText_username)
            emailInputEDitText = view.findViewById(R.id.inputEditText_email)
            birthdayInputEDitText = view.findViewById(R.id.inputEditText_date_of_birth)
        }*/
    }
}