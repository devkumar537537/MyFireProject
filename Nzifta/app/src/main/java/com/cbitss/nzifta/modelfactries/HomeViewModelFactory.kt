package com.cbitss.nzifta.modelfactries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cbitss.nzifta.data.repositries.Repositry
import com.cbitss.nzifta.viewmodels.HomeFragmentViewModel

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val repostiry: Repositry) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(repostiry) as T
    }
}