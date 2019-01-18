package com.tallymarks.kotlinbasics.ui.activities.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.tallymarks.kotlinbasics.R
import com.tallymarks.kotlinbasics.data.local.Users


import java.util.ArrayList

class RecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.UsersHolder>() {

    private var users: List<Users> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_single_item, parent, false)
        return UsersHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        val currentUser = users[position]
        holder.tvDesignation.text = currentUser.designation
        holder.tvUsername.text=currentUser.userName
        holder.cardItem.setOnClickListener {
            Toast.makeText(context,"clicked : "+holder.tvUsername.text,Toast.LENGTH_LONG).show()
        }

    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setData(users: List<Users>) {
        this.users = users
        notifyDataSetChanged()
    }

    inner class UsersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUsername: TextView = itemView.findViewById(R.id.tv_name)
        val tvDesignation: TextView = itemView.findViewById(R.id.tv_designation)
        val cardItem:CardView=itemView.findViewById(R.id.card_item)
    }
}
