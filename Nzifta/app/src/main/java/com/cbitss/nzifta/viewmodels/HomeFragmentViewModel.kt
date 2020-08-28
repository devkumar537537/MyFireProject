package com.cbitss.nzifta.viewmodels

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cbitss.nzifta.activities.LogInActivity
import com.cbitss.nzifta.activities.RegisterActivity
import com.cbitss.nzifta.activities.UpComingMoviesHome
import com.cbitss.nzifta.data.repositries.Repositry
import com.cbitss.nzifta.pojoclass.RegisterAs

class HomeFragmentViewModel(private val reposity: Repositry) : ViewModel(){

 var registe_as : String? = null





 fun loginefun(view: View)
 {
  val intent = Intent(view.context,LogInActivity::class.java)

  view.context.startActivity(intent)
 }

 fun fetchUserType(): LiveData<List<RegisterAs>>
 {
   return reposity.fetchUsertype()
 }

 fun moveto_Regsiter(view: View)
 {
      val intent = Intent(view.context,RegisterActivity::class.java)
     intent.putExtra("RegisterAs",registe_as)

  view.context.startActivity(intent)
 }
 fun move_to_UpCominge(view: View)
 {
  val intent = Intent(view.context,UpComingMoviesHome::class.java)
  view.context.startActivity(intent)
 }
 }