package com.moataz.madarsoftdatabase.ui.fragments.input

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.moataz.madarsoftdatabase.R
import com.moataz.madarsoftdatabase.data.User


class InputFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_input, container, false)
        val saveButton = view.findViewById<Button>(R.id.save_button)
        var nameField = view.findViewById<TextInputLayout>(R.id.name_text_field)
        var ageField = view.findViewById<TextInputLayout>(R.id.age_text_field)
        var jobField = view.findViewById<TextInputLayout>(R.id.job_text_field)
        val inputViewModel = ViewModelProvider(this)[InputViewModel::class]

        ageField.setOnClickListener { ageField.error = null }
        saveButton.setOnClickListener {
            val genderRadioGroup = view.findViewById<RadioGroup>(R.id.gender_radio_group)
            val checkedId = genderRadioGroup.checkedRadioButtonId

            val userName = nameField.editText?.text.toString().trim()
            val age = ageField.editText?.text.toString().trim()
            val jobTitle = jobField.editText?.text.toString().trim()

            if (userName.isBlank())
                nameField.error = "Name is required"
            else if (age.isBlank()) ageField.error = "Age is required"
            else if (jobTitle.isBlank()) jobField.error = "Job title is required"
            else if (checkedId == -1) Snackbar.make(
                genderRadioGroup,
                "Please select your gender",
                LENGTH_SHORT
            ).show()
            else {
                val selectedGender = view.findViewById<RadioButton>(checkedId).text?.toString()
                val gender = selectedGender

                inputViewModel.saveUser(
                    User(
                        userName = userName,
                        age = age,
                        jobTitle = jobTitle,
                        gender = gender
                    )
                )
                findNavController().navigate(R.id.action_input_to_user_fragment)
            }
        }
        return view
    }
}