package com.cbitss.nzifta.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.cbitss.nzifta.R
import com.cbitss.nzifta.databinding.ActivityRegisterBinding
import com.cbitss.nzifta.modelfactries.RegisterViewModelFactory
import com.cbitss.nzifta.viewmodels.Authlistener
import com.cbitss.nzifta.viewmodels.RegisterViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein


import org.kodein.di.generic.instance
@Suppress("DEPRECATION")
class RegisterActivity : AppCompatActivity(),KodeinAware,Authlistener {

    override val kodein by kodein()
    lateinit var viewmodel : RegisterViewModel
    lateinit var binding: ActivityRegisterBinding

    private val factory: RegisterViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewmodel = ViewModelProviders.of(this, factory).get(RegisterViewModel::class.java)
        binding.regitsmodel = viewmodel
        viewmodel.authlistener = this

        var getvalue = intent.getStringExtra("RegisterAs")
        viewmodel.usertypetext = getvalue
    }
        override fun OnStart() {
        binding.registerProgressbar.visibility = View.VISIBLE
        Toast.makeText(applicationContext,"Started the registaration",Toast.LENGTH_SHORT).show()
    }

    override fun OnSuccess() {
        binding.registerProgressbar.visibility = View.GONE
        Toast.makeText(applicationContext,"Completed Success fully",Toast.LENGTH_SHORT).show()
    }

    override fun OnFailed(message: String) {
       binding.registerProgressbar.visibility = View.GONE
        Toast.makeText(applicationContext,"error $message",Toast.LENGTH_SHORT).show()
    }

}


