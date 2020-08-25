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
import com.cbitss.nziftaadmin.databinding.FragmentCategoryAddBinding
import com.cbitss.nziftaadmin.modelfactories.InsertUsrtypeModelFactory
import com.cbitss.nziftaadmin.viewmodels.Authlistener
import com.cbitss.nziftaadmin.viewmodels.UserTypeModel
import kotlinx.android.synthetic.main.fragment_category_add.*
import org.kodein.di.KodeinAware


import org.kodein.di.generic.instance
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.factory

@Suppress("DEPRECATION")
class CategoryAdd : Fragment(),KodeinAware,Authlistener {
    override val kodein by kodein()
 lateinit var binding: FragmentCategoryAddBinding
    lateinit var viewmodel : UserTypeModel

    private val factory : InsertUsrtypeModelFactory by instance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_category_add, container, false)
        viewmodel = ViewModelProviders.of(this,factory).get(UserTypeModel::class.java)
        binding.categorymodel = viewmodel
        binding.lifecycleOwner = this
        viewmodel.authListener = this
        return binding.root
    }

    override fun OnSuccess() {
       progregss_bar_category.visibility = View.GONE
        Toast.makeText(view?.context,"Category Added Successfully",Toast.LENGTH_LONG).show()
    }

    override fun OnFailed(message: String) {
      progregss_bar_category.visibility = View.GONE
        Toast.makeText(view?.context,"error : ${message}",Toast.LENGTH_LONG).show()
    }

    override fun OnStart() {
       progregss_bar_category.visibility = View.VISIBLE
    }


}