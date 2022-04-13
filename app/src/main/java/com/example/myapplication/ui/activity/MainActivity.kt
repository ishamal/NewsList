package com.example.myapplication.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.searchButton.setOnClickListener {
            var searchText = binding.search.text.toString()
            if (searchText.trim().isNotEmpty()) {
                findNavController(R.id.nav_host_fragment2).navigate(R.id.searchFragment
                    , bundleOf("Search" to searchText))
            }
        }
    }
}