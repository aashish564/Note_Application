package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.noteapp.R
import com.example.noteapp.Fragment.HomeFragment
import com.example.noteapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        replaceFragment(HomeFragment.newInstance())

    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentTransition = supportFragmentManager.beginTransaction()

        fragmentTransition.setCustomAnimations(
            android.R.anim.slide_out_right,
            android.R.anim.slide_in_left
        )
        fragmentTransition.replace(R.id.flFragmenet, fragment)
        fragmentTransition.commit()
    }

}
