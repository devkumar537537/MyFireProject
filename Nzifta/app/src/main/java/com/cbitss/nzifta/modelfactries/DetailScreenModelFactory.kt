package com.cbitss.nzifta.modelfactries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cbitss.nzifta.data.repositries.Repositry
import com.cbitss.nzifta.viewmodels.DeatailScreeModel

@Suppress("UNCHECKED_CAST")
class DetailScreenModelFactory(private val repositry: Repositry) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DeatailScreeModel(repositry) as T
    }
}