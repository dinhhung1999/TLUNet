package com.example.tlunet.ui.category.categorydetail

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.tlunet.R
import com.example.tlunet.model.subjects.Subjects
import kotlinx.android.synthetic.main.item_subject.view.*

class SubjectAdapter2(val context: Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val listData = mutableListOf<Subjects>()
    private val VIEWTYPEDATA = 1
    private val VIEWTYPENULL = 0
    private var view: View? = null

    private var onItemClickListener: ((item: Subjects)->Unit)? = null

    fun setOnItemClickListener(onItemClickListener: ((item: Subjects)->Unit)){
        this.onItemClickListener = onItemClickListener
    }

    fun appendData(data: List<Subjects>) {
        this.listData.clear()
        this.listData.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEWTYPEDATA){
            view = inflater.inflate(R.layout.item_subject2, parent, false)
            BlockadeViewHolder(view!!)
        }else{
            view = inflater.inflate(R.layout.layout_null, parent, false)
            ViewNull(view!!)
        }

    }

    override fun getItemCount(): Int {
        return if (listData.size != 0){
            listData.size
        }else{
            1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (listData.size != 0){
            VIEWTYPEDATA
        }else{
            VIEWTYPENULL
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BlockadeViewHolder) {
            holder.tvName.text = listData[position].name
            holder.tvCode.text = "Mã số môn học: ${listData[position].code}"
            holder.tvCredit.text =  "Số tín chỉ: ${listData[position].credit.toString()}"
            holder.tvTeacher.text = listData[position].lecturers
            Glide.with(context).load(listData[position].imgUrl).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    holder.progress.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    holder.progress.visibility = View.GONE
                    return false
                }
            })
                    .into(holder.image)
            holder.itemView.setOnClickListener { onItemClickListener?.invoke(listData[position]) }
        }

    }


    companion object{
        class BlockadeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvName: TextView = itemView.tvName
            val progress: ProgressBar = itemView.progress
            val tvCode: TextView = itemView.tvCode
            val tvCredit: TextView = itemView.tvCredit
            val tvTeacher: TextView = itemView.tvTeacher
            val image: ImageView = itemView.image

        }

        class ViewNull(itemView: View): RecyclerView.ViewHolder(itemView){
        }
    }
}