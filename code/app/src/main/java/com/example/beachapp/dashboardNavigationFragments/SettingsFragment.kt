package com.example.beachapp.dashboardNavigationFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.beachapp.R
import com.example.beachapp.aboutApp.AboutAppActivity
import com.example.beachapp.databinding.FragmentSettingsBinding
import com.example.beachapp.loginAndSignup.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.concurrent.timerTask

class SettingsFragment : Fragment() {

    private lateinit var firestore: FirebaseFirestore

    private lateinit var binding : FragmentSettingsBinding

    private lateinit var auth : FirebaseAuth

    private lateinit var userEmail : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        auth = FirebaseAuth.getInstance()
        userEmail = auth.currentUser?.email.toString()
        firestore = FirebaseFirestore.getInstance()

        showNameIfExists()

        binding.txtEmail.text = userEmail

        binding.logoutContainer.setOnClickListener{
            auth.signOut()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
        }

        binding.accountsContainer.setOnClickListener {
            showUpdateUsernameDialog()
        }

        binding.aboutAppContainer.setOnClickListener {
            showAboutAppActivity()
        }

        return binding.root
    }


    private fun updateName(name : String) {
        val docRef = firestore.collection("gmail").document(userEmail)
        val updatedName = mapOf("name" to name)
        docRef.update(updatedName)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    binding.txtName.text = name
                    Toast.makeText(requireContext(), "Name updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun showNameIfExists() {
        val docRef = firestore.collection("gmail").document(userEmail)
        docRef.get().addOnCompleteListener {
            if (it.isSuccessful) {
                val document = it.result
                if (document.contains("name")) {
                    binding.txtName.text = document.get("name").toString()
                }
            }
        }
    }

    private fun showUpdateUsernameDialog() {
        // Inflate the dialog layout
        val inflater = LayoutInflater.from(requireContext())
        val dialogView = inflater.inflate(R.layout.dialog_update_username, null)

        // Create and show the dialog
        AlertDialog.Builder(requireContext())
            .setTitle("Update Username")
            .setView(dialogView)
            .setPositiveButton("Update") { _, _ ->
                val etUsername: EditText = dialogView.findViewById(R.id.editTextUsername)
                val newUsername = etUsername.text.toString()
                updateName(newUsername)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showAboutAppActivity() {
        val intent = Intent(requireActivity(), AboutAppActivity::class.java)
        startActivity(intent)
    }
}