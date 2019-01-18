package com.tallymarks.kotlinbasics.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import com.tallymarks.kotlinbasics.R
import com.tallymarks.kotlinbasics.ui.activities.adapters.RecyclerViewAdapter
import com.tallymarks.kotlinbasics.data.general_models.RecyclerViewModel
import com.tallymarks.kotlinbasics.data.local.DatabaseHandler
import com.tallymarks.kotlinbasics.data.local.UserDao
import com.tallymarks.kotlinbasics.data.local.Users
import kotlinx.android.synthetic.main.activity_recyclerview.*

class RecyclerViewActivity : AppCompatActivity()
{
    private var userDao: UserDao? = null
    private var databaseHandler: DatabaseHandler? = null
    private var adapter : RecyclerViewAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra("activityName")
        initRoomDB()
        loadData()
        deleteAll()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun initRoomDB()
    {
        databaseHandler = DatabaseHandler.getAppDataBase(context = this)
        userDao = databaseHandler?.userDao()
    }

    private fun loadData() {
//        val recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        var users: List<Users> = userDao!!.allUsers
        adapter= RecyclerViewAdapter(this@RecyclerViewActivity)
        adapter!!.setData(users)
        recycler_view.adapter = adapter
    }

    private fun deleteAll() {
        btn_delete_all.setOnClickListener {
            userDao?.truncateTable()
            Toast.makeText(this@RecyclerViewActivity,"Deleted!!",Toast.LENGTH_LONG).show()
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }

}
