package com.moataz.madarsoftdatabase.ui.fragments.display

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moataz.madarsoftdatabase.R
import com.moataz.madarsoftdatabase.data.User

class UserAdapter(
    private val usersList: List<User>, private val onDeleteClick: (User) -> Unit
) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.name)
        val ageTextView: TextView = view.findViewById(R.id.age)
        val jobTitleTextView: TextView = view.findViewById(R.id.job_title)
        val genderTextView: TextView = view.findViewById(R.id.gender)
        val deleteUserButton: ImageView = view.findViewById(R.id.delete_user_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_card_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return usersList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersList[position]

        holder.nameTextView.text = user.userName
        holder.jobTitleTextView.text = user.jobTitle
        holder.genderTextView.text = "Gender: ${user.gender}"
        holder.ageTextView.text = "Age: ${user.age} years old"

        holder.deleteUserButton.setOnClickListener { onDeleteClick(user) }
    }
}