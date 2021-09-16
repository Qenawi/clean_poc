package com.qm.cleanmodule.ui.fragment.home

import com.qm.cleanmodule.R
import com.qm.cleanmodule.base.view.BaseAdapter
import com.qm.cleanmodule.base.view.BaseViewHolder
import com.qm.cleanmodule.ui.fragment.home.model.HomeUIItem

// HomeAdapter @Docs
class HomeAdapter(itemClick: (HomeUIItem) -> Unit) : BaseAdapter<HomeUIItem>(itemClick) {

    override fun layoutRes(): Int = R.layout.item_home_view
    override fun onViewHolderCreated(viewHolder: BaseViewHolder<HomeUIItem>) {

    }
}