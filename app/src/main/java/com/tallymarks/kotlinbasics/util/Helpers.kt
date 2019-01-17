package com.tallymarks.kotlinbasics.util

import android.app.Activity
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.tallymarks.kotlinbasics.R

object Helpers {

    fun showCustomAlertDialog(context: Context, title: String, message: String) {

        (context as Activity).runOnUiThread {
            val li = LayoutInflater.from(context)
            val promptsView = li.inflate(R.layout.custom_alert_dialog, null)
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setView(promptsView)
            val alertDialog = alertDialogBuilder.create()
            val ivClose = promptsView.findViewById<ImageView>(R.id.iv_close)
            val ivInfo = promptsView.findViewById<ImageView>(R.id.iv_info)

            val tvTitle = promptsView.findViewById<TextView>(R.id.tv_option)
            val tvBody = promptsView.findViewById<TextView>(R.id.tv_body)
            val btnNo = promptsView.findViewById<Button>(R.id.btn_no)
            val btnYes = promptsView.findViewById<Button>(R.id.btn_yes)
            ivClose.visibility = View.GONE
            btnNo.visibility = View.GONE
            tvTitle.text = title
            tvBody.text = message
            btnYes.text = context.getResources().getString(R.string.dismiss)
            tvBody.setTextColor(context.getResources().getColor(R.color.colorPrimary))
//            tvTitle.visibility = View.GONE
            ivInfo.visibility = View.VISIBLE

            btnYes.setOnClickListener { alertDialog.cancel() }
            alertDialogBuilder.setCancelable(true)
            alertDialog.show()
        }
    }
}
