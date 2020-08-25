package com.cbitss.nziftaadmin.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import com.cbitss.nziftaadmin.data.repositries.Repositry
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserTypeModel(private val repostiry: Repositry) : ViewModel() {
    var usertype: String? = null
    var category: String? = null
    var authListener: Authlistener? = null
    var email : String? = null
    var password: String? = null
    //disposable to dispose the Completable
    private val disposables = CompositeDisposable()


    fun insertuser(view: View)
    {
            if(usertype.isNullOrEmpty())
            {
                authListener?.OnFailed("Invalid email or password")
                return
            }
        authListener?.OnStart()

        val disposable = repostiry.insertusertype(usertype!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.OnSuccess()

            },{
                authListener?.OnFailed(it.message!!)
            })

        disposables.add(disposable)
    }
    fun insertcategory(view: View)
    {
        if(category.isNullOrEmpty())
        {
            authListener?.OnFailed("Invalid text")
            return
        }
        authListener?.OnStart()

        val disposable = repostiry.insertcategory(category!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.OnSuccess()

            },{
                authListener?.OnFailed(it.message!!)
            })

        disposables.add(disposable)
    }

    fun adminlogin(view: View)
    {
        if( email.isNullOrBlank() || email.isNullOrEmpty())
        {
            authListener?.OnFailed("Invalid email or Password")
            return
        }else if(password.isNullOrEmpty() || password.isNullOrBlank())
        {
            authListener?.OnFailed("Invalid email or Password")
            return
        }
        authListener?.OnStart()

        val disposable = repostiry.adminLogin(email!!,password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.OnSuccess()

            },{
                authListener?.OnFailed(it.message!!)
            })

        disposables.add(disposable)
    }
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}