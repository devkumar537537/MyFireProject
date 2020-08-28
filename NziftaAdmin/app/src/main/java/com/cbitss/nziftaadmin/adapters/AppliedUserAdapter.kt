package com.cbitss.nziftaadmin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cbitss.nziftaadmin.R
import com.cbitss.nziftaadmin.pojoclasses.AppliedUserClass
import com.cbitss.nziftaadmin.pojoclasses.RegisteredUser
import de.hdodenhof.circleimageview.CircleImageView


class AppliedUserAdapter(var registeruser: MutableList<RegisteredUser>,
                         var applieduser : MutableList<AppliedUserClass>,
                         var view: View): RecyclerView.Adapter<AppliedUserAdapter.MyViewHolder>() {


inner class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)
{
    lateinit var profilecireimaged: CircleImageView
    lateinit var username: TextView
    lateinit var contactnumber: TextView

    init {
        username = itemview.findViewById(R.id.appliedusername)
        contactnumber = itemview.findViewById(R.id.applied_user_number)
        profilecireimaged = itemview.findViewById(R.id.applied_userimage)
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.appliesuser_formate,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var registdata = registeruser.get(position)
        var appliddata = applieduser.get(position)

        holder.username.text = registdata.userName
        holder.contactnumber.text = registdata.userNumber
        if(registdata.imagerurl.equals("default"))
        {
            holder.profilecireimaged.setImageResource(R.drawable.sample)
        }else
        {
            Glide.with(view.context).load(registdata.imagerurl).into(holder.profilecireimaged)
        }

        holder.itemView.setOnClickListener {
            view.findNavController().navigate(R.id.action_appliesUser_to_appliedUserDetail)
        }
    }

    override fun getItemCount(): Int {
        return applieduser.size
    }

}
