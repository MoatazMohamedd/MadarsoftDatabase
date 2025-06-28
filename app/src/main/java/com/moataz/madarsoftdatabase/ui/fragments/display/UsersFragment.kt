package com.moataz.madarsoftdatabase.ui.fragments.display

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.moataz.madarsoftdatabase.R
import com.moataz.madarsoftdatabase.data.User


class UsersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_users, container, false)
        val userViewModel = ViewModelProvider(this)[UserViewModel::class]
        val usersRecyclerView = view.findViewById<RecyclerView>(R.id.users_recycler_view)
        val emptyListMessage = view.findViewById<TextView>(R.id.empty_list_message)
        val addUserButton = view.findViewById<FloatingActionButton>(R.id.add_user_button)
        usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        userViewModel.allUsers.observe(viewLifecycleOwner) { users ->
            val userAdapter = UserAdapter(users) { user ->
                promptDeleteMessage(userViewModel, user)
            }
            usersRecyclerView.adapter = userAdapter

            if (users.isEmpty()) {
                emptyListMessage.visibility = VISIBLE
            } else {
                emptyListMessage.visibility = GONE
            }
        }

        addUserButton.setOnClickListener {
            findNavController().navigate(R.id.action_users_to_input)
        }
        return view
    }

    fun promptDeleteMessage(userViewModel: UserViewModel, user: User) {
        MaterialAlertDialogBuilder(
            requireContext(), R.style.CustomAlertDialog
        )
            .setTitle("Are You Sure You Want To Delete This User?")
            .setMessage("You can always add more users later.")
            .setNegativeButton("Cancel") { dialog, which ->
            }
            .setPositiveButton("Delete") { dialog, which ->
                userViewModel.deleteUser(user)
            }
            .show()
    }
}