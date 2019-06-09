package com.example.pokefinder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokefinder.R
import kotlinx.android.synthetic.main.list_item.view.*

open class PokemonDataAdapter(private val items: ArrayList<ArrayList<String>>,
                         private val context: Context): RecyclerView.Adapter<PokemonDataAdapter.ViewHolder>() {

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView? = view.titleTextView
        val contentTextView: TextView? = view.contentTextView
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView?.text = items[position][0]
        holder.contentTextView?.text = items[position][1]
    }

    open fun updateData(items: ArrayList<ArrayList<String>>?) {
        if(!items.isNullOrEmpty()) {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        }
    }

}
