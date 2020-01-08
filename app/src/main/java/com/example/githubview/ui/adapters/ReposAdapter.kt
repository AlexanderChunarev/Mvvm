package com.example.githubview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubview.R
import com.example.githubview.responces.RepoResponse

class ReposAdapter(private val items: ArrayList<RepoResponse>) :
    RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    fun updateRepos(repos: List<RepoResponse>) {
        items.clear()
        items.addAll(repos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = items.size

    inner class RepoViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val repoName: TextView = view.findViewById(R.id.repo_name_text)
        private val repoLanguage: TextView = view.findViewById(R.id.language_text)
        private val updatedDate: TextView = view.findViewById(R.id.updated_date_text)

        fun onBind(position: Int) {
            val selectedItem = items[position]
            selectedItem.apply {
                repoName.text = name
                repoLanguage.text = language
                updatedDate.text = updated_at
            }
        }
    }
}