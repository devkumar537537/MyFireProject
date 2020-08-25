package com.cbitss.nzifta.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cbitss.nzifta.R
import com.cbitss.nzifta.pojoclass.ContentClass
import de.hdodenhof.circleimageview.CircleImageView

class CategoryContentAdapter(var list:MutableList<ContentClass>,var view: View) : RecyclerView.Adapter<CategoryContentAdapter.MyViewHolder>() {

inner class  MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    lateinit var item_basic: CircleImageView
    lateinit var item_title: TextView
    lateinit var item_banner: TextView
    init {
        item_basic = itemView.findViewById(R.id.content_picture)
        item_title = itemView.findViewById(R.id.itemtile)
        item_banner = itemView.findViewById(R.id.Item_banner)
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.listscreenitem,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       var listdata = list.get(position)

        holder.item_banner.text = listdata.banner_name
        holder.item_title.text = listdata.item_title
        if(listdata.imageone.equals("default"))
        {
            holder.item_basic.setImageResource(R.drawable.picture)
        }else
        {
           Glide.with(view).load(listdata.imageone).into(holder.item_basic)
        }
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("imageUrl" to listdata.imageone.toString(),
            "project_title" to listdata.item_title.toString(),
                "project_description" to listdata.description.toString(),
            "banner_name" to listdata.banner_name.toString(),
               "userid" to listdata.id.toString(),
                "designation" to listdata.degination.toString()

                )
            view.findNavController().navigate(R.id.action_listScreen_to_detailScreen,bundle)
        }
    }

    override fun getItemCount(): Int {
      return  list.size
    }
}