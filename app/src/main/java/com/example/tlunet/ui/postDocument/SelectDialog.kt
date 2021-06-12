package com.example.tlunet.ui.postDocument

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tlunet.R
import com.example.tlunet.extensions.getColorResource

import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.dialog_select.*
import kotlinx.android.synthetic.main.item_select.view.*

class SelectDialog<T>(context: Context, val data: List<T>, val f: (T) -> String,private var selectedIndex: Int = -1) : BottomSheetDialog(context) {

    private var onSelectedListener: ((T) -> Unit)? = null

    init {
        setContentView(R.layout.dialog_select)
        imgClose.setOnClickListener { cancel() }
        rvValues.layoutManager = LinearLayoutManager(context)
        rvValues.adapter = ItemAdapter()
    }

    fun setOnSelectedListener(onSelectedListener: ((T) -> Unit)?) {
        this.onSelectedListener = onSelectedListener
    }

    inner class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
        private val inflater = LayoutInflater.from(context)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val view = inflater.inflate(R.layout.item_select, parent, false)
            return ItemViewHolder(view)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(position)
        }

        inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val tvValue = itemView.tvValue
            private val imgChecked = itemView.imgChecked
            fun bind(i: Int) {
                tvValue.text = f(data[i])
                if (i == selectedIndex) {
                    imgChecked.visibility = View.VISIBLE
                    tvValue.setTextColor(context.getColorResource(R.color.colorPrimary))
                } else {
                    imgChecked.visibility = View.GONE
                    tvValue.setTextColor(context.getColorResource(R.color.colorBody1))
                }
                itemView.setOnClickListener {
                    onSelectedListener?.invoke(data[i])
                    selectedIndex = i
                    rvValues.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    fun setSelectedIndex(selectedIndex: Int) {
        this.selectedIndex = selectedIndex
        rvValues.adapter?.notifyDataSetChanged()
    }
    fun setTitle(title: String) {
        tvTitle.text = title
    }
}
