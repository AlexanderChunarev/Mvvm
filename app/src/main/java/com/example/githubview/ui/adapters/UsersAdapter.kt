package com.example.githubview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubview.R
import com.example.githubview.responces.UserResponse
import com.example.githubview.ui.listener.OnItemListener
import com.example.githubview.ui.listener.OnLongItemListener
import com.squareup.picasso.Picasso

class UsersAdapter(
    private val items: ArrayList<UserResponse>,
    private val onItemListener: OnItemListener,
    private val onLongItemListener: OnLongItemListener
) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    fun updateUsers(newUsers: List<UserResponse>) {
        items.clear()
        items.addAll(newUsers)
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class UserViewHolder(view: View) :
        RecyclerView.ViewHolder(view), View.OnLongClickListener, View.OnClickListener {

        private val avatarView: ImageView = view.findViewById(R.id.avatar_view)
        private val userLogin: TextView = view.findViewById(R.id.login_text)
        private val followersCount: TextView = view.findViewById(R.id.followers_count_text)
        private val followingCount: TextView = view.findViewById(R.id.following_count_text)

        init {
            view.setOnClickListener(this)
            view.setOnLongClickListener(this)
        }

        fun onBind(position: Int) {
            val selectedItem = items[position]
            selectedItem.apply {
                Picasso.get().load(avatar_url).into(avatarView)
                userLogin.text = login
                followersCount.text = followers.toString()
                followingCount.text = following.toString()
            }
        }

        override fun onLongClick(v: View?): Boolean {
            onLongItemListener.onLongClickItem(adapterPosition)
            return true
        }

        override fun onClick(v: View?) {
            onItemListener.onClickItem(adapterPosition)
        }
    }
}