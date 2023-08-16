package com.toliveira.todolist.fragments.list

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.toliveira.todolist.MainActivity
import com.toliveira.todolist.R
import com.toliveira.todolist.databinding.FragmentListBinding
import com.toliveira.todolist.model.Task
import kotlinx.coroutines.NonDisposableHandle.parent

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    // This list will store the tasks to fill the adapter
    private var taskList = emptyList<Task>()


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return taskList.size
    }


    @SuppressLint("CutPasteId")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = taskList[position]
        holder.itemView.findViewById<TextView>(R.id.textViewTaskID).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.textViewTaskTitle).text = currentItem.title.toString()

        if(currentItem.resolved){
            holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setBackgroundResource(R.color.Green)
        }

        holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(task: List<Task>) {
        this.taskList = task
        notifyDataSetChanged()
    }
}