package com.cbitss.nziftaadmin.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.cbitss.nziftaadmin.R

class HomeViewModel: ViewModel() {
    fun movetoaddregister_type(view: View)
    {
        view.findNavController().navigate(R.id.action_homeFragment_to_userTypeFregament)
    }
    fun move_to_category(view: View)
    {
        view.findNavController().navigate(R.id.action_homeFragment_to_categoryAdd)
    }
    fun move_tocontent(view: View)
    {
        view.findNavController().navigate(R.id.action_homeFragment_to_addContent)
    }
}