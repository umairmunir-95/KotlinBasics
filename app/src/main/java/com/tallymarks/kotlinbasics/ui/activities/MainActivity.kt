package com.tallymarks.kotlinbasics.ui.activities

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import android.view.View
import com.tallymarks.kotlinbasics.App
import com.tallymarks.kotlinbasics.R
import com.tallymarks.kotlinbasics.data.local.UserDao
import com.tallymarks.kotlinbasics.data.remote.RetrofitClient
import com.tallymarks.kotlinbasics.data.remote.UserInterface
import com.tallymarks.kotlinbasics.data.remote.UserRepoModel
import com.tallymarks.kotlinbasics.util.Helpers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.tallymarks.kotlinbasics.data.local.DatabaseHandler
import com.tallymarks.kotlinbasics.data.local.Users


class MainActivity : AppCompatActivity() {

    private var userDao: UserDao? = null
    private var databaseHandler: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRoomDB()
        insertData()
        showAlertDialog()
        showRecyclerView()
        callRestApi()
    }

    private fun callRestApi() {

        btn_call_rest_api.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            var userInterface: UserInterface = RetrofitClient.getClient()!!.create(UserInterface::class.java)
            var user: Call<List<UserRepoModel>>
            user = userInterface.getUserRepos(App.GET_REPOSITORIES)
            user.enqueue(object : Callback<List<UserRepoModel>> {
                override fun onFailure(call: Call<List<UserRepoModel>>?, t: Throwable?) {
                    Toast.makeText(applicationContext, t?.message, Toast.LENGTH_SHORT).show()
                    Log.d("Error:::", t?.message)
                    progress_bar.visibility = View.GONE
                }

                override fun onResponse(
                    call: Call<List<UserRepoModel>>?,
                    response: Response<List<UserRepoModel>>?
                ) {
                    if (response != null && response.isSuccessful && response.body() != null) {
                        Log.d("ResponseCode : ", "" + response.code())
                        progress_bar.visibility = View.GONE
                        var githubRepos: String = "List of github repositories for user umairmunir-95 are;\n\n"
                        for (i in 0 until response.body()!!.size) {
                            githubRepos = githubRepos + i + ") " + response.body()!![i].name + "\n"
                            Log.d("RepoName : ", response.body()!![i].name)
                        }
                        Helpers.showCustomAlertDialog(this@MainActivity, "Alert", githubRepos)
                    }
                }
            })
        }
    }


    private fun showAlertDialog() {
        btn_alert_dialog.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("App background color")
            builder.setMessage("Are you want to set the app background color to GREEN?")
            builder.setPositiveButton("YES") { dialog, which ->
                Toast.makeText(applicationContext, "Ok, we change the app background.", Toast.LENGTH_SHORT).show()
                linear_layout.setBackgroundColor(Color.GREEN)
            }
            builder.setNegativeButton("No") { dialog, which ->
                Toast.makeText(applicationContext, "You are not agree.", Toast.LENGTH_SHORT).show()
            }
            builder.setNeutralButton("Cancel") { _, _ ->
                Toast.makeText(applicationContext, "You cancelled the dialog.", Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    private fun showRecyclerView()
    {
        btn_show_recycler_view.setOnClickListener {
            val intent = Intent(this, RecyclerViewActivity::class.java)
            intent.putExtra("activityName","RecyclerView")
            startActivity(intent)
        }
    }

    private fun initRoomDB()
    {
        databaseHandler = DatabaseHandler.getAppDataBase(context = this)
        userDao = databaseHandler?.userDao()
    }

    private fun insertData()
    {
        btn_inser_db.setOnClickListener {
            if (et_name.text.trim().isEmpty()) {
                Toast.makeText(this, "Name required", Toast.LENGTH_LONG).show()
            }
            else if (et_designation.text.trim().isEmpty()) {
                Toast.makeText(this, "Designation required", Toast.LENGTH_LONG).show()
            }
            else
            {
                userDao?.insertUser(Users(et_name.text.toString(),et_designation.text.toString()))
                et_name.text.clear()
                et_designation.text.clear()
                Toast.makeText(this, "Added successfully.", Toast.LENGTH_LONG).show()
            }
        }
    }
}

