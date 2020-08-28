package com.cbitss.nzifta.activities

import android.os.Bundle
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cbitss.nzifta.Fragments.Annoucement
import com.cbitss.nzifta.Fragments.Event
import com.cbitss.nzifta.Fragments.Home
import com.cbitss.nzifta.Fragments.Profile
import com.cbitss.nzifta.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var bottomanavigationbar: BottomNavigationView = findViewById(R.id.bottomNavigationView)




        bottomanavigationbar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home_layout -> {

                val HomeFragment = Home.newInstance()
                openFragment(HomeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.annoucemnt_layout -> {

                val AnounceFragment = Annoucement.newInstance()
                openFragment(AnounceFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.events_layout -> {

              val EventFragment = Event.newInstance()
                openFragment(EventFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile_layout ->{
                val profileFragment: Profile = Profile.newInstance()
                openFragment(profileFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout, fragment)
        transaction.commit()
    }

    override fun onStart() {
        super.onStart()
        val homeFragment = Home.newInstance()
        openFragment(homeFragment)
    }


}