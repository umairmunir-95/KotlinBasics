package com.tallymarks.kotlinbasics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_recyclerview.*

class RecyclerViewActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra("activityName")

        loadData()

    }

    private fun loadData() {
//        val recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val users = ArrayList<RecyclerViewModel>()
        for (i in 1..1000) {
            users.add(RecyclerViewModel("Umair Munir Ahmad : $i", "Mobility Consultant : " + i))
        }
        val adapter = RecyclerViewAdapter(this)
        adapter.setData(users)
        recycler_view.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}