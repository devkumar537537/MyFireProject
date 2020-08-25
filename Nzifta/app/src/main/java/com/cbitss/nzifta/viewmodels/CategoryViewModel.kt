package com.cbitss.nzifta.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cbitss.nzifta.data.repositries.Repositry
import com.cbitss.nzifta.pojoclass.Category
import com.cbitss.nzifta.pojoclass.ContentClass

class CategoryViewModel(val repositry: Repositry) : ViewModel() {
    var categorytag:String? = null

    fun fectchCategory() : LiveData<List<Category>>
    {
        return repositry.fetchCategory()
    }
    fun fetchCategoryContent() : LiveData<List<ContentClass>>
    {
        return repositry.fetchContent(categorytag!!)
    }
}