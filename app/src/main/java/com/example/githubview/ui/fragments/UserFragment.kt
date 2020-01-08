package com.example.githubview.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.example.githubview.R
import com.example.githubview.ui.adapters.UsersAdapter
import com.example.githubview.ui.listener.OnItemListener
import com.example.githubview.ui.listener.OnLongItemListener
import com.example.githubview.utilities.MessageUtils
import kotlinx.android.synthetic.main.fragment_users_list.*

class UserFragment : BaseFragment(), OnItemListener, OnLongItemListener {
    private val usersAdapter by lazy {
        UsersAdapter(
            arrayListOf(),
            this,
            this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(user_recycler_view) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = usersAdapter
        }

        viewModel.message.observe(this, Observer {
            when (it) {
                is MessageUtils.ErrorMessage -> Toast.makeText(
                    context,
                    it.errorString,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })



        search_button.setOnClickListener {
            viewModel.addUserData(editText.text.toString())
        }

        viewModel.getUsersData().observe(this, Observer {
            usersAdapter.updateUsers(it)
        })
    }

    override fun onClickItem(position: Int) {
        switchFragment(RepoFragment.newInstance(usersAdapter.getItem(position).login))
    }

    private fun clearBackStack() {
        activity!!.supportFragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    private fun switchFragment(fragment: Fragment) {
        clearBackStack()
        activity!!.supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                0,
                0,
                android.R.anim.slide_out_right
            )
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onLongClickItem(position: Int) {
        viewModel.deleteUserData(usersAdapter.getItem(position).login)
    }

    companion object {
        fun newInstance() = UserFragment()
    }
}
