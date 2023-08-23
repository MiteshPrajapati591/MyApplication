package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class HomeListItemAdapter:RecyclerView.Adapter<HomeListItemAdapter.ViewHolder>() {

    var homeList = ArrayList<Home>()

    fun addHomeData(list: ArrayList<Home>){
        homeList.clear()
        homeList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeListItemAdapter.ViewHolder {
         val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_item_list, parent, false)

         return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeListItemAdapter.ViewHolder, position: Int) {
        holder.bind(homeList[position])
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var textViewId: AppCompatTextView = view.findViewById(R.id.textViewId)
        var textViewTitle: AppCompatTextView = view.findViewById(R.id.textViewTitle)
        var textViewTrip: AppCompatTextView = view.findViewById(R.id.textViewTrip)
        var textViewCountry: AppCompatTextView = view.findViewById(R.id.textViewCountry)
        var textViewHeadQuaters: AppCompatTextView = view.findViewById(R.id.textViewHeadQuaters)
        var textViewWebSite: AppCompatTextView = view.findViewById(R.id.textViewWebSite)
        var textViewSlogan: AppCompatTextView = view.findViewById(R.id.textViewSlogan)

        fun bind(home: Home) {
            textViewId.text = home.id
            textViewTitle.text = home.name
            textViewTrip.text = home.trip
            textViewCountry.text = home.country
            textViewHeadQuaters.text = home.headQuaters
            textViewWebSite.text =  home.webSite
            textViewSlogan.text =  home.slogen
        }

    }
}