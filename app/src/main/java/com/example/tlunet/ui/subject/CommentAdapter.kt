package com.example.tlunet.ui.subject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tlunet.R
import com.example.tlunet.model.comments.Comment
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter  (val context: Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val listData = mutableListOf<Comment>()
    private val VIEWTYPEDATA = 1
    private val VIEWTYPENULL = 0
    private var view: View? = null

    private var onItemClickListener: ((item: Comment) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: ((item: Comment) -> Unit)) {
        this.onItemClickListener = onItemClickListener
    }

    fun appendData(data: List<Comment>) {
        this.listData.clear()
        this.listData.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEWTYPEDATA) {
            view = inflater.inflate(R.layout.item_comment, parent, false)
            BlockadeViewHolder(view!!)
        } else {
            view = inflater.inflate(R.layout.layout_null, parent, false)
            ViewNull(view!!)
        }

    }

    override fun getItemCount(): Int {
        return if (listData.size != 0) {
            listData.size
        } else {
            1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (listData.size != 0) {
            VIEWTYPEDATA
        } else {
            VIEWTYPENULL
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BlockadeViewHolder) {
            holder.tvUserName.text = listData[position].author
            holder.tvComment.text = listData[position].content
            holder.itemView.setOnClickListener { onItemClickListener?.invoke(listData[position]) }
        }

    }


    companion object {
        class BlockadeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvUserName: TextView = itemView.tvUserName
            val tvComment: TextView = itemView.tvComment

        }

        class ViewNull(itemView: View) : RecyclerView.ViewHolder(itemView) {
        }
    }
}