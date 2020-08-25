package com.cbitss.nzifta.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.cbitss.nzifta.R
import com.cbitss.nzifta.databinding.FragmentDetailScreenBinding
import com.cbitss.nzifta.databinding.FragmentListScreenBinding
import com.cbitss.nzifta.modelfactries.CategoryViewModelFactory
import com.cbitss.nzifta.modelfactries.DetailScreenModelFactory
import com.cbitss.nzifta.viewmodels.CategoryViewModel
import com.cbitss.nzifta.viewmodels.DeatailScreeModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

@Suppress("DEPRECATION")
class DetailScreen : Fragment(),KodeinAware {

    override val kodein by kodein()
    private val factory : DetailScreenModelFactory by instance()
      lateinit var imageurl : String
    lateinit var viewmodel: DeatailScreeModel
    lateinit var bindiing: FragmentDetailScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      bindiing = DataBindingUtil.inflate(inflater,R.layout.fragment_detail_screen, container, false)
        viewmodel = ViewModelProviders.of(this,factory).get(DeatailScreeModel::class.java)
        bindiing.detailmodel = viewmodel
        bindiing.lifecycleOwner = this

        return bindiing.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val  project_title = arguments?.getString("project_title").toString()
        viewmodel.project_title = project_title

        val  project_banner = arguments?.getString("banner_name").toString()
        viewmodel.project_banner = project_banner
        imageurl = arguments?.getString("imageUrl").toString()


        val  project_description = arguments?.getString("project_description").toString()
        viewmodel.project_description = project_description

        val  userid = arguments?.getString("userid").toString()
        viewmodel.id= userid

        val  designation = arguments?.getString("designation").toString()
        viewmodel.desigantion = designation

        if(imageurl == "default")
        {
            bindiing.sampleImageLayout.setImageResource(R.drawable.sample)
        }else
        {
            Glide.with(view.context).load(imageurl).into(bindiing.sampleImageLayout)
        }
    }

}