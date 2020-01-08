package com.example.githubview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubview.ui.fragments.UserFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        val listFragment = UserFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, listFragment)
            .commit()
    }
}
