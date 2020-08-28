package com.cbitss.nziftaadmin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cbitss.nziftaadmin.R
import com.cbitss.nziftaadmin.adapters.AppliedUserAdapter
import com.cbitss.nziftaadmin.databinding.FragmentAppliesUserBinding
import com.cbitss.nziftaadmin.databinding.FragmentCategoryAddBinding
import com.cbitss.nziftaadmin.modelfactories.AppliedUserViewModelFactory
import com.cbitss.nziftaadmin.modelfactories.InsertUsrtypeModelFactory
import com.cbitss.nziftaadmin.pojoclasses.AppliedUserClass
import com.cbitss.nziftaadmin.pojoclasses.RegisteredUser
import com.cbitss.nziftaadmin.viewmodels.AppliedUserViewModel
import com.cbitss.nziftaadmin.viewmodels.UserTypeModel

import org.kodein.di.KodeinAware


import org.kodein.di.generic.instance
import org.kodein.di.android.x.kodein
@Suppress("DEPRECATION")
class AppliesUser : Fragment(),KodeinAware {
    override val kodein by kodein()
    lateinit var binding: FragmentAppliesUserBinding
    lateinit var viewmodel : AppliedUserViewModel

    private val factory : AppliedUserViewModelFactory by instance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_applies_user, container, false)
        viewmodel = ViewModelProviders.of(this,factory).get(AppliedUserViewModel::class.java)
        binding.registeitemmodel = viewmodel
        binding.lifecycleOwner = this
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            applieduserRcyclerview.layoutManager = LinearLayoutManager(view.context)
            var adapter = AppliedUserAdapter(RegisteredUser.registedata.registlist,AppliedUserClass.appliedata.list,view)
            applieduserRcyclerview.adapter = adapter
        }
    }


}