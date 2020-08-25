package com.cbitss.nziftaadmin.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cbitss.nziftaadmin.data.repositries.Repositry
import com.cbitss.nziftaadmin.pojoclasses.Category
import com.cbitss.nziftaadmin.pojoclasses.Contentdatalcass
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AddContentViewModel(private val repostiry: Repositry) : ViewModel()
{
    var authListener: Authlistener? = null
var banner_name: String? = null
    var item_title: String? = null
    var required_degination: String? = null
    var spinnercategory_text: String? = null
    var item_description: String? = null





    //disposable to dispose the Completable
    private val disposables = CompositeDisposable()
    fun intercontent(view: View)
    {
        if(banner_name.isNullOrEmpty() || banner_name.isNullOrBlank())
        {
            authListener?.OnFailed("Invailid banner name")
            return
        }else if(item_title.isNullOrEmpty() || item_title.isNullOrBlank())
        {
            authListener?.OnFailed("Invailid item_title name")
            return
        }
        else if(required_degination.isNullOrEmpty() || required_degination.isNullOrBlank())
        {
            authListener?.OnFailed("Invailid item_title name")
            return
        }
        else if( spinnercategory_text.isNullOrEmpty() ||  spinnercategory_text.isNullOrBlank())
        {
            authListener?.OnFailed("Invailid item_title name")
            return
        }
        else if(item_description.isNullOrEmpty() || item_description.isNullOrBlank())
        {
            authListener?.OnFailed("Invailid item_title name")
            return
        }
        authListener?.OnStart()

        val disposable = repostiry.insertcontent(banner_name!!,item_title!!,required_degination!!, spinnercategory_text
        !!,item_description!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.OnSuccess()

            },{
                authListener?.OnFailed(it.message!!)
            })

        disposables.add(disposable)
    }


    fun getallusertype(): LiveData<List<Category>>{
        return repostiry.fetchusertype()
    }
    fun getcontentData() : LiveData<List<Contentdatalcass>>
    {
        return repostiry.fetchContent()
    }
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}