package com.example.localprofiles.presentation.activities

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.localprofiles.R
import com.example.localprofiles.data.AppDataBase
import com.example.localprofiles.data.ProfileDao
import com.example.localprofiles.data.ProfileItemDbModel
import com.example.localprofiles.databinding.ProfileItemBinding
import com.example.localprofiles.domain.ProfileItem
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ProfileViewHolder>() {

    private var profileList = emptyList<ProfileItemDbModel>()

    var onProfileItemClickListener: ((ProfileItemDbModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_item, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ProfileViewHolder, position: Int) {
        val item = profileList[position]
        viewHolder.itemView.setOnClickListener {
            onProfileItemClickListener?.invoke(item)
        }
        viewHolder.bind(item)
    }

    override fun getItemCount(): Int = profileList.size

    fun getCurrentItem(position: Int): ProfileItemDbModel = profileList[position]

    fun setData(newList: List<ProfileItemDbModel>) {
        profileList = newList
        notifyDataSetChanged()
    }

    class ProfileViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var imageView: ImageView = view.findViewById(R.id.imageProfile)
        var usernameTextView: TextView = view.findViewById(R.id.usernameTextView)
        var userSurnameTextView: TextView = view.findViewById(R.id.userSurnameTextView)
        var emailInputTextView: TextView = view.findViewById(R.id.emailTextView)
        var birthdayInputTextView: TextView = view.findViewById(R.id.birthdayTextView)

        fun bind(profileItem: ProfileItemDbModel) {

            imageView.setImageResource(R.drawable.splash_logo)
            usernameTextView.text = profileItem.name
            userSurnameTextView.text = profileItem.surname
            emailInputTextView.text = profileItem.email
            birthdayInputTextView.text = profileItem.dateOfBirth
        }
    }
}
