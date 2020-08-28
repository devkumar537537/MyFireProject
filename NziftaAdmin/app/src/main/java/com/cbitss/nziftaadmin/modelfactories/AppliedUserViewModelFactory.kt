package com.cbitss.nziftaadmin.modelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cbitss.nziftaadmin.data.repositries.Repositry
import com.cbitss.nziftaadmin.viewmodels.AppliedUserViewModel

@Suppress("UNCHECKED_CAST")
class AppliedUserViewModelFactory(private val repostiry: Repositry) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppliedUserViewModel(repostiry) as T
    }
}