package com.qm.cleanmodule.databinding

import androidx.databinding.DataBindingComponent

/**
 * Created by ninja on 6/18/2020.
 **/
class AppDataBindingComponent : DataBindingComponent{

    override fun getOtherViewsBinding(): OtherViewsBinding {
        return OtherViewsBinding()
    }
}