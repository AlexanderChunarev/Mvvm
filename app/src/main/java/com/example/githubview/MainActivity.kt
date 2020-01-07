package com.example.githubview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.githubview.ui.DataAdapter
import com.example.githubview.ui.listener.OnItemListener
import com.example.githubview.ui.listener.OnLongItemListener
import com.example.githubview.utilities.MessageUtils
import com.example.githubview.viewmodels.MainActivityViewModel
import com.resocoder.mvvmbasicstut.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemListener, OnLongItemListener {
    private val factory by lazy { InjectorUtils.provideViewModelFactory(this) }
    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java)
    }
    private val usersAdapter by lazy { DataAdapter(arrayListOf(), this, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(recycler_view) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = usersAdapter
        }

        viewModel.message.observe(this, Observer {
            when (it) {
                is MessageUtils.ErrorMessage -> Toast.makeText(
                    this,
                    it.errorString,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.getUsersData()

        search_button.setOnClickListener {
            viewModel.addUserData(editText.text.toString())
        }

        viewModel.userLiveData.observe(this, Observer {
            usersAdapter.updateUsers(it)
        })
    }

    override fun onClickItem(position: Int) {

    }

    override fun onLongClickItem(position: Int) {
        viewModel.deleteUserData(usersAdapter.getItem(position).login)
    }
}
