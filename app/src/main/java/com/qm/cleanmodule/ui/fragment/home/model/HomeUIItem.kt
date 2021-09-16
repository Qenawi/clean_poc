package com.qm.cleanmodule.ui.fragment.home.model

import android.os.Parcelable
import com.qm.cleanmodule.base.view.BaseParcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class HomeUIItem(
    val id:Int,
    val status: String?,
    val gender: String?,
    val name: String?,
    val image: String?
) : BaseParcelable, Parcelable {
    override fun unique(): Any {
       return id
    }
}



