package com.cbitss.nzifta.modelfactries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cbitss.nzifta.data.repositries.Repositry
import com.cbitss.nzifta.viewmodels.RegisterViewModel


@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory(private val repostiry: Repositry) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel(repostiry) as T
    }
}