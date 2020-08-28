package com.cbitss.nziftaadmin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.cbitss.nziftaadmin.R
import com.cbitss.nziftaadmin.databinding.ActivityLoginBinding
import com.cbitss.nziftaadmin.modelfactories.InsertUsrtypeModelFactory
import com.cbitss.nziftaadmin.viewmodels.Authlistener
import com.cbitss.nziftaadmin.viewmodels.UserTypeModel
import com.google.firebase.auth.FirebaseAuth

import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein


import org.kodein.di.generic.instance
import org.kodein.di.android.x.kodein

@Suppress("DEPRECATION")
class Login : AppCompatActivity(),Authlistener,KodeinAware {

    override val kodein by kodein()
    lateinit var  viewmodel: UserTypeModel

    lateinit var binding: ActivityLoginBinding
    private val factory : InsertUsrtypeModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        viewmodel = ViewModelProviders.of(this,factory).get(UserTypeModel::class.java)
        binding.adminlogin = viewmodel
        viewmodel.authListener = this
    }

    override fun OnSuccess() {
       binding.adminloginProgressbasr.visibility = View.GONE
        Toast.makeText(applicationContext,"Loged In SuccessFully",Toast.LENGTH_SHORT).show()
        val intent = Intent(applicationContext,MainActivity::class.java)
        startActivity(intent)
    }

    override fun OnFailed(message: String) {
        binding.adminloginProgressbasr.visibility = View.GONE
        Toast.makeText(applicationContext,"error : ${message}",Toast.LENGTH_SHORT).show()
    }

    override fun OnStart() {
       binding.adminloginProgressbasr.visibility = View.VISIBLE


    }

    override fun onStart() {
        super.onStart()

        val firebaseuser = FirebaseAuth.getInstance().currentUser

        if(firebaseuser != null)
        {
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }
}