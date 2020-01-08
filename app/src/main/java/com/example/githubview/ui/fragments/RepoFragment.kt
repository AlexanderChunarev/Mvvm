package com.example.githubview.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.githubview.R
import com.example.githubview.ui.adapters.ReposAdapter
import kotlinx.android.synthetic.main.fragment_list_repo.*

class RepoFragment : BaseFragment() {
    private val repoAdapter by lazy {
        ReposAdapter(
            arrayListOf()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val response = arguments!!.getSerializable(RESPONSE_KEY).toString()
            viewModel.getUserReposData(response).observe(this, Observer {
                repoAdapter.updateRepos(it)
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(repo_recycler_view) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = repoAdapter
        }
    }

    companion object {
        fun newInstance(login: String) = RepoFragment().apply {
            arguments = Bundle().apply {
                putSerializable(RESPONSE_KEY, login)
            }
        }

        const val RESPONSE_KEY = "response_key"
    }
}
