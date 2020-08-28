package com.cbitss.nzifta.viewmodels

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.cbitss.nzifta.data.repositries.Repositry
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RegisterViewModel( private val repositry: Repositry): ViewModel(){

    var email_register: String? = null
    var password_register: String? = null
    var name_register : String? = null
    var number_register: String? = null
    var city_register: String? = null
    var about: String? = null
    var usertypetext: String? = null
    var authlistener : Authlistener ? = null
    private val disposables = CompositeDisposable()
    fun insertuser(view : View)
    {
        if(email_register.isNullOrEmpty())
        {
            authlistener?.OnFailed("please enter email")
            return
        }else if(password_register.isNullOrEmpty())
        {
            authlistener?.OnFailed("please enter password")
            return
        }else if(name_register.isNullOrEmpty())
        {
            authlistener?.OnFailed("please enter name")
            return
        }else if(number_register.isNullOrEmpty())
        {
            authlistener?.OnFailed("please enter number")
            return
        }else if(city_register.isNullOrEmpty())
        {
            authlistener?.OnFailed("please enter city")
            return
        }else if(about.isNullOrEmpty())
        {
            authlistener?.OnFailed("please enter about")
            return
        }else if(usertypetext.isNullOrEmpty())
        {
            authlistener?.OnFailed("please enter usertype")
            return
        }

        authlistener?.OnStart()

       val disposal   =  repositry.insertrpostryapplieduser(
        email_register!!,
        password_register!!,
        name_register!!,
        number_register!!,
        city_register!!,
        about!!,
        usertypetext!!

    )
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe({
               authlistener?.OnSuccess()

           },{
               try {
                   authlistener?.OnFailed(it.message!!)
               }catch (e:KotlinNullPointerException)
               {
                   Toast.makeText(view.context,"This is $e",Toast.LENGTH_LONG).show()
               }

           })

        disposables.add(disposal)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}