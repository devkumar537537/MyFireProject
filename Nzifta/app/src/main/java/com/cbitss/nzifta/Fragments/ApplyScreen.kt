package com.cbitss.nzifta.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.cbitss.nzifta.R
import com.cbitss.nzifta.databinding.FragmentApplyScreenBinding
import com.cbitss.nzifta.databinding.FragmentDetailScreenBinding
import com.cbitss.nzifta.modelfactries.ApplyScreenModelFactory
import com.cbitss.nzifta.modelfactries.DetailScreenModelFactory
import com.cbitss.nzifta.viewmodels.ApplyScreenModel
import com.cbitss.nzifta.viewmodels.Authlistener
import com.cbitss.nzifta.viewmodels.DeatailScreeModel

import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

@Suppress("DEPRECATION")
class ApplyScreen : Fragment(),KodeinAware,Authlistener {

    override val kodein by kodein()
    private val factory : ApplyScreenModelFactory by instance()
var userid: String? = null
    lateinit var viewmodel: ApplyScreenModel
    lateinit var bindiing: FragmentApplyScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      bindiing = DataBindingUtil.inflate(inflater,R.layout.fragment_apply_screen, container, false)
        viewmodel = ViewModelProviders.of(this,factory).get(ApplyScreenModel::class.java)
        bindiing.applymodel = viewmodel
        bindiing.lifecycleOwner = this
        viewmodel.authlistener = this
        return bindiing.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userid = arguments?.getString("userid")
        viewmodel.id = userid
    }
    override fun OnStart() {
        bindiing.applyProgressbar.visibility = View.VISIBLE
        Toast.makeText(requireContext(),"Registration Started",Toast.LENGTH_SHORT).show()
    }

    override fun OnSuccess() {
        bindiing.applyProgressbar.visibility = View.GONE
        Toast.makeText(requireContext(),"You Applied Success fully",Toast.LENGTH_SHORT).show()
    }

    override fun OnFailed(message: String) {
        bindiing.applyProgressbar.visibility = View.GONE
        Toast.makeText(requireContext(),"error: $message",Toast.LENGTH_SHORT).show()
    }


}