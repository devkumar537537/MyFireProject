package com.cbitss.nzifta.viewmodels

import android.net.Uri
import android.view.View
import android.widget.Toast

import androidx.lifecycle.ViewModel
import com.cbitss.nzifta.data.repositries.Repositry
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RegisterViewModel(var repositry: Repositry): ViewModel() {
    var email: String? = null
    var password: String? = null
    var name: String? = null
    var  number: String? = null

   var imageuri : Uri? = null

    var city:String? = null
    var age:String? = "0"
    var number_of_filsm: String? = "0"
    var gender:String? = null
var userageIstype = false
    var userno_of_filmsIstype = false
    var aboutwork:String? = null

    var usertype: String? = null

    var authListener: Authlistener? = null
     private val disposables = CompositeDisposable()

   fun insertUser(view : View)
    {



        if(email.isNullOrBlank() || email.isNullOrEmpty())
        {
            authListener?.OnFailed("Please fill all field")
            return
        }else if(password.isNullOrBlank() || password.isNullOrEmpty())
        {
            authListener?.OnFailed("Please fill all field")
            return
        }else if(name.isNullOrEmpty() || name.isNullOrBlank())
        {
            authListener?.OnFailed("Please fill all field")
            return
        }else if(number.isNullOrEmpty() || number.isNullOrBlank())
        {
            authListener?.OnFailed("Please fill all field")
            return
        }else if(city.isNullOrEmpty() || city.isNullOrBlank())
        {
            authListener?.OnFailed("Please fill all field")
            return
        }else if(gender.isNullOrEmpty() || gender.isNullOrBlank())
        {
            authListener?.OnFailed("Please fill all field")
            return
        }else if(aboutwork.isNullOrEmpty() || aboutwork.isNullOrBlank())
        {
            authListener?.OnFailed("Please fill all field")
            return
        }else if(usertype.isNullOrEmpty() || usertype.isNullOrBlank())
        {
            authListener?.OnFailed("Please fill all field")
            return
        }else if(imageuri == null)
        {
            authListener?.OnFailed("Image Uri can not be done")
            return
        }

            authListener?.OnStart()

            val disposable = repositry.insertUserr(email!!,password!!,name!!,number!!,city!!,age!!,number_of_filsm!!,gender!!,usertype!!,aboutwork!!,imageuri!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    authListener?.OnSuccess()

                },{
                    authListener?.OnFailed(it.message!!)
                })

            disposables.add(disposable)

Toast.makeText(view.context,"This is testing",Toast.LENGTH_LONG).show()

    }



    fun visiblitylogic()
    {
        if(usertype.equals("Director"))
        {
            userno_of_filmsIstype = true
        }

        if(usertype.equals("Actor") || usertype.equals("Actoress"))
        {
            userageIstype = true
        }
    }
}