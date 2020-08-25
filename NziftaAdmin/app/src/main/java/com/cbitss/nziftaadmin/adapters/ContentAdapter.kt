package com.cbitss.nziftaadmin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cbitss.nziftaadmin.R
import com.cbitss.nziftaadmin.pojoclasses.Contentdatalcass
import com.google.firebase.database.FirebaseDatabase
import de.hdodenhof.circleimageview.CircleImageView

class ContentAdapter(var list: MutableList<Contentdatalcass>, var parentcontext: Context) : RecyclerView.Adapter<ContentAdapter.MyViewHolder>() {



    inner class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)
    {
             lateinit var circleImageView: ImageView
        lateinit var title_textView: TextView
        lateinit var brief_description: TextView
        lateinit var cross_imageview: ImageView

        init {
           circleImageView  = itemview.findViewById(R.id.profile_circle_image)
            title_textView  = itemview.findViewById(R.id.format_item_title)
             brief_description= itemview.findViewById(R.id.biefintroduction)
          cross_imageview = itemview.findViewById(R.id.delete_item)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.userformate,parent,false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var listdata = list.get(position)
        holder.title_textView.text = listdata.item_title
        holder.brief_description.text = listdata.description
        if(listdata.imageurl.equals("default"))
        {
             holder.circleImageView.setImageResource(R.drawable.sample)
        }else
        {
            Glide.with(parentcontext).load(listdata.imageurl).into(holder.circleImageView)
        }
        holder.cross_imageview.setOnClickListener {
           var databaseReference = FirebaseDatabase.getInstance().getReference("Content").child(listdata.id.toString())
            databaseReference.removeValue().addOnCompleteListener {
                if(it.isSuccessful)
                {
                    Toast.makeText(parentcontext,"The item is deleted Successfully",Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(parentcontext,"error: ${it.exception?.message}",Toast.LENGTH_LONG).show()
                }
            }
        }
        holder.itemView.setOnClickListener {
            Toast.makeText(parentcontext,"This will move to another activity",Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
       return list.size
    }
}