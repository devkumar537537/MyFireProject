package com.cbitss.nziftaadmin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.cbitss.nziftaadmin.R
import com.cbitss.nziftaadmin.databinding.FragmentUserTypeFregamentBinding
import com.cbitss.nziftaadmin.modelfactories.InsertUsrtypeModelFactory
import com.cbitss.nziftaadmin.viewmodels.Authlistener
import com.cbitss.nziftaadmin.viewmodels.UserTypeModel
import kotlinx.android.synthetic.main.fragment_user_type_fregament.*





import org.kodein.di.KodeinAware


import org.kodein.di.generic.instance
import org.kodein.di.android.x.kodein

@Suppress("DEPRECATION")
class UserTypeFregament : Fragment(),Authlistener,KodeinAware {
    override val kodein by kodein()
lateinit var binding: FragmentUserTypeFregamentBinding
    lateinit var viewmodel: UserTypeModel

    private val factory : InsertUsrtypeModelFactory by instance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_type_fregament, container, false)
        viewmodel = ViewModelProviders.of(this,factory).get(UserTypeModel::class.java)
        binding.usermodel = viewmodel
        binding.lifecycleOwner = this
        viewmodel.authListener = this
        return binding.root
    }

    override fun OnSuccess() {
        progregss_bar.visibility = View.GONE
        Toast.makeText(view?.context,"The data submmited here",Toast.LENGTH_LONG).show()
    }

    override fun OnFailed(message: String) {
        progregss_bar.visibility = View.GONE
        Toast.makeText(view?.context,"error: ${message}",Toast.LENGTH_LONG).show()
    }

    override fun OnStart() {
       progregss_bar.visibility = View.VISIBLE

    }




}


