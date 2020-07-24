package com.charlezz.finalarchitecture.feature.base

import androidx.paging.PagedListAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseAdapter<T, VDB : ViewDataBinding, VH : BaseViewHolder<VDB>>
(val bindingResId:Int, diffCallback: DiffUtil.ItemCallback<T>)
    : PagedListAdapter<T, VH>(diffCallback) {
    abstract fun getViewHolderLayoutId():Int

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = DataBindingUtil.inflate<VDB>(
                LayoutInflater.from(parent.context),
                getViewHolderLayoutId(),
                parent,
                false)

        return object:BaseViewHolder<VDB>(binding) {} as VH
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.setVariable(bindingResId, getItem(position))
        holder.binding.executePendingBindings()
    }
}
