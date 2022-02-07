package com.stephen.mokitotutorial.presenter.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stephen.mokitotutorial.R
import com.stephen.mokitotutorial.data.model.ToDoModel
import com.stephen.mokitotutorial.databinding.ItemTodoBinding

/**
 * Written by StephenLeeDev on 2022/02/07.
 */

class ToDoListAdapter(
    private val toDoItemClickListener: (ToDoModel) -> Unit,
    private val toDoCheckListener: (ToDoModel) -> Unit
) : ListAdapter<ToDoModel, ToDoListAdapter.ToDoViewHolder>(diffUtil) {

    inner class ToDoViewHolder(private val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(toDoModel: ToDoModel) {
            binding.toDoModel = toDoModel
        }

        fun bindViews(data: ToDoModel) {
            binding.checkBox.setOnClickListener {
                toDoCheckListener(
                    data.copy(hasCompleted = binding.checkBox.isChecked)
                )
                checkToDoComplete(binding.checkBox.isChecked)
            }
            binding.root.setOnClickListener {
                toDoItemClickListener(data)
            }
        }

        private fun checkToDoComplete(isChecked: Boolean) = with(binding) {
            checkBox.isChecked = isChecked
            constraintContainer.setBackgroundColor(
                ContextCompat.getColor(
                    root.context,
                    if (isChecked) {
                        R.color.gray
                    } else {
                        R.color.white
                    }
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.bindViews(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ToDoModel>() {
            override fun areItemsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}