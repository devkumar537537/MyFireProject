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
import com.cbitss.nzifta.adapters.CategoryContentAdapter
import com.cbitss.nzifta.databinding.FragmentListScreenBinding
import com.cbitss.nzifta.modelfactries.CategoryViewModelFactory
import com.cbitss.nzifta.pojoclass.Category
import com.cbitss.nzifta.pojoclass.ContentClass
import com.cbitss.nzifta.viewmodels.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_list_screen.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class ListScreen : Fragment(),KodeinAware {

 lateinit var categortext :String

    override val kodein by kodein()
    private val factory : CategoryViewModelFactory by instance()

    lateinit var viewmodel: CategoryViewModel
    lateinit var bindiing: FragmentListScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindiing = DataBindingUtil.inflate(inflater,R.layout.fragment_list_screen, container, false)
        viewmodel = ViewModelProviders.of(this,factory).get(CategoryViewModel::class.java)
        bindiing.listscreenmodel = viewmodel
        bindiing.lifecycleOwner = this

        return bindiing.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categortext = arguments?.getString("categoryname").toString()
       viewmodel.categorytag = categortext

        makerecyclerview(view)

    }

    private fun makerecyclerview(view : View) {

        var list: MutableList<ContentClass> = ArrayList()
        viewmodel.fetchCategoryContent().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            list.clear()
            if (it != null) {

                for (i in it) {
                    list.add(i)
                    bindiing.apply {
                        listscreenrecyclerview.layoutManager = LinearLayoutManager(view?.context)
                        var apapter = CategoryContentAdapter(list,view)
                        listscreenrecyclerview.adapter = apapter

                    }
                }
            }
        })
    }
}