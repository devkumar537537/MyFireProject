package com.cbitss.nzifta.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import com.cbitss.nzifta.data.repositries.Repositry
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ApplyScreenModel(val repositry: Repositry) : ViewModel() {


    var id: String? = null
    var desgination: String? = null
    var expect_salary: String? = null
    var brief_description: String? = null
    var authlistener : Authlistener? = null


    private val disposables = CompositeDisposable()
    fun inserapplied_user(view: View)
    {
        if(desgination.isNullOrBlank() || desgination.isNullOrEmpty())
        {
            authlistener?.OnFailed("Please fill all field")
            return
        }else if(expect_salary.isNullOrEmpty() || expect_salary.isNullOrBlank())
        {
            authlistener?.OnFailed("Please fill all field")
            return
        }else if(brief_description.isNullOrBlank() || brief_description.isNullOrEmpty())
        {
            authlistener?.OnFailed("Please fill all field")
            return
        }

        authlistener?.OnStart()

        val disposable = repositry.insertappliesuser(id!!,desgination!!,expect_salary!!,brief_description!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authlistener?.OnSuccess()

            },{
                authlistener?.OnFailed(it.message!!)
            })

        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}