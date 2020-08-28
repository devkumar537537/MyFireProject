package com.cbitss.nzifta.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.cbitss.nzifta.R
import com.cbitss.nzifta.databinding.FragmentHomeBinding
import com.cbitss.nzifta.modelfactries.HomeViewModelFactory
import com.cbitss.nzifta.pojoclass.RegisterAs
import com.cbitss.nzifta.viewmodels.Authlistener
import com.cbitss.nzifta.viewmodels.HomeFragmentViewModel
import io.reactivex.Observable

import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
@Suppress("DEPRECATION")
class Home : Fragment(),KodeinAware,Authlistener {

    override val kodein by kodein()
    var usertypearray : MutableList<String> = ArrayList()
    val default_text = "Select Category"
    private val factory: HomeViewModelFactory by instance()

   lateinit var binding: FragmentHomeBinding
   lateinit var viewmodel: HomeFragmentViewModel

    override fun onStart() {
        super.onStart()
        getusertype()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        viewmodel = ViewModelProviders.of(this,factory).get(HomeFragmentViewModel::class.java)
        binding.homeviewmodela = viewmodel
        binding.lifecycleOwner = this
        initisevalueinspinner()
        return binding.root

    }



    companion object {
        fun newInstance(): Home = Home()

    }
    private fun getusertype() {
        var list: MutableList<RegisterAs> = ArrayList()
        viewmodel.fetchUserType().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            usertypearray.clear()
            if (it != null) {

                for (i in it) {
                    usertypearray.add(i.usertype.toString())
                }
            }
        })


    }
    private fun initisevalueinspinner() {

        usertypearray.add(0,"Register As")
        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, usertypearray)
        binding.itemspinner.adapter = dataAdapter
        binding.itemspinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewmodel.registe_as = parent.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
                Toast.makeText(requireContext(), "Nothing is selected", Toast.LENGTH_LONG).show();
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        usertypearray.clear()
    }

    override fun OnStart() {

    }

    override fun OnSuccess() {

    }

    override fun OnFailed(message: String) {

    }

}