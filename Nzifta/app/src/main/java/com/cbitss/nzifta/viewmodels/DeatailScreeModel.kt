package com.cbitss.nzifta.viewmodels

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.cbitss.nzifta.R
import com.cbitss.nzifta.data.repositries.Repositry
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DeatailScreeModel(val repositry: Repositry): ViewModel() {


    var project_title: String? = null
    var project_banner: String? = null
    var project_description: String? = null
    var desigantion: String? = null
    var id: String? = null




    fun moveto_apply(view: View)
    {
        var bundle = bundleOf("userid" to id.toString())
        view.findNavController().navigate(R.id.action_detailScreen_to_applyScreen,bundle)
    }


}