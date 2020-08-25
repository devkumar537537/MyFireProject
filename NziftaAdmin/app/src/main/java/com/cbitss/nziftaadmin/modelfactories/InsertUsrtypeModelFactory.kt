package com.cbitss.nziftaadmin.modelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cbitss.nziftaadmin.data.repositries.Repositry
import com.cbitss.nziftaadmin.viewmodels.UserTypeModel
@Suppress("UNCHECKED_CAST")
class InsertUsrtypeModelFactory(private val repostiry: Repositry) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return UserTypeModel(repostiry) as T
  }
}

