package com.cbitss.nzifta.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cbitss.nzifta.R
import com.cbitss.nzifta.adapters.CategoryAdapters
import com.cbitss.nzifta.databinding.FragmentCategoryButtonBinding
import com.cbitss.nzifta.modelfactries.CategoryViewModelFactory
import com.cbitss.nzifta.pojoclass.Category
import com.cbitss.nzifta.viewmodels.Authlistener
import com.cbitss.nzifta.viewmodels.CategoryViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class CategoryButtonFragment : Fragment(),KodeinAware {

       lateinit var viewmodel: CategoryViewModel
    lateinit var binding :  FragmentCategoryButtonBinding
    override val kodein by kodein()
    private val factory : CategoryViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_category_button, container, false)
        viewmodel = ViewModelProviders.of(this,factory).get(CategoryViewModel::class.java)
        binding.categorymodel = viewmodel
        binding.lifecycleOwner = this

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setrecyclerview(view)
    }
    private fun setrecyclerview(view: View) {


        var list: MutableList<Category> = ArrayList()
       viewmodel.fectchCategory().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            list.clear()
            if (it != null) {

                for (i in it) {
                    list.add(i)
                    binding.apply {
                        categoryrecyclerview.layoutManager = GridLayoutManager(view?.context,2)
                        var apapter = CategoryAdapters(list,view)
                        categoryrecyclerview.adapter = apapter

                    }
                }
            }
        })

    }



}