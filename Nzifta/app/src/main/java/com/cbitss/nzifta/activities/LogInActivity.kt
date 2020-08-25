package com.cbitss.nzifta.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cbitss.nzifta.R
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        i_am_admin.setOnClickListener {
            i_am_user.visibility = View.VISIBLE
            adminlogin.visibility = View.VISIBLE
            userlogin.visibility = View.GONE
            i_am_admin.visibility = View.GONE
        }
        i_am_user.setOnClickListener {
            i_am_user.visibility = View.GONE
            adminlogin.visibility = View.GONE
            userlogin.visibility = View.VISIBLE
            i_am_admin.visibility = View.VISIBLE
        }
    }
}