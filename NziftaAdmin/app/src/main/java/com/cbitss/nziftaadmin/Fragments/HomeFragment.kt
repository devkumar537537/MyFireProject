package com.cbitss.nziftaadmin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.cbitss.nziftaadmin.R
import com.cbitss.nziftaadmin.databinding.FragmentHomeBinding
import com.cbitss.nziftaadmin.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.homeviewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


}