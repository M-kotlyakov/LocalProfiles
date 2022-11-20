package com.example.localprofiles.presentation.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.localprofiles.data.ProfileItemDbModel
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso


class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ProfileViewHolder>() {

    private var profileList = emptyList<ProfileItemDbModel>()

    var onProfileItemClickListener: ((ProfileItemDbModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.localprofiles.R.layout.profile_item, parent, false)
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

    class ProfileViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        var imageView: ShapeableImageView = view.findViewById(com.example.localprofiles.R.id.imageProfile)
        var usernameTextView: TextView = view.findViewById(com.example.localprofiles.R.id.usernameTextView)
        var userSurnameTextView: TextView = view.findViewById(com.example.localprofiles.R.id.userSurnameTextView)
        var emailInputTextView: TextView = view.findViewById(com.example.localprofiles.R.id.emailTextView)
        var birthdayInputTextView: TextView = view.findViewById(com.example.localprofiles.R.id.birthdayTextView)

        fun bind(profileItem: ProfileItemDbModel) {
//            val bitmap = BitmapFactory.decodeResource(view.resources, com.example.localprofiles.R.drawable.personal_account_avatar)
//            Log.d("AddProfileViewModel", "bitmap:  , profileItem.image: ${profileItem.image} AND ${profileItem.image?.sameAs(bitmap).toString()}")
            when (profileItem.image) {
                null -> {
                    val picture = picturesUrl.random()
                    Picasso.get().load(picture).into(imageView)
                }
                else -> {
                    imageView.setImageBitmap(profileItem.image)
                }
            }
            usernameTextView.text = profileItem.name
            userSurnameTextView.text = profileItem.surname
            emailInputTextView.text = profileItem.email
            birthdayInputTextView.text = profileItem.dateOfBirth
        }
    }

    companion object {

        private var picturesUrl = listOf(
            "https://img.freepik.com/free-vector/sunset-or-sunrise-in-ocean-nature-landscape_33099-2244.jpg?w=826&t=st=1668560568~exp=1668561168~hmac=61d82d557cf5ea6d586d947afcd49f5644fa90fecf52a7d41e354aceafab8037",
            "https://img.freepik.com/premium-vector/sunset-at-beach-with-tree-silhouette_116220-170.jpg?w=826",
            "https://img.freepik.com/premium-vector/beach-landscape-with-a-beautiful-lighthouse-at-sunset-09_693194-2.jpg?w=826",
            "https://img.freepik.com/premium-vector/dramatic-sunset-landscape-over-sea-with-flowers_104785-339.jpg?w=826"
        )
    }
}
