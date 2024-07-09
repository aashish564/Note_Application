package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.noteapp.Model.UserModel
import com.example.noteapp.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SigninActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var email : String
    private lateinit var password : String
    private lateinit var name : String
    private lateinit var dataBase: DatabaseReference

    private val binding : ActivitySigninBinding by lazy {
        ActivitySigninBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // Initialization Firebase Auth
        auth = Firebase.auth
        // Initialization Firebase Database
        dataBase = Firebase.database.reference

        binding.CreateButton.setOnClickListener {

            name = binding.Nameuser.text.toString().trim()
            email = binding.EmailUser.text.toString().trim()
            password = binding.PasswordUser.text.toString().trim()

            if(name.isBlank() || email.isBlank() || password.isBlank())
            {
                Toast.makeText(this,"Please fill all the requried details",Toast.LENGTH_SHORT).show()
            }
            else{
                createAccount(email,password)
            }

//            val intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)
        }

        binding.alreadyhavebutton.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {task ->

            if(task.isSuccessful){
                Toast.makeText(this,"Account created successfully",Toast.LENGTH_SHORT).show()

                saveUserData()

                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Account creation is failed", Toast.LENGTH_SHORT).show()
                Log.d("Account","createAccount: Failure",task.exception)
            }

        }
    }

    // Save data into database

    private fun saveUserData() {
        name = binding.Nameuser.text.toString().trim()
        email = binding.EmailUser.text.toString().trim()
        password = binding.PasswordUser.text.toString().trim()

        val user = UserModel(name,email,password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        // save user data in firebase database
        dataBase.child("user").child(userId).setValue(user)
    }
}