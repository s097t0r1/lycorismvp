package com.s097t0r1.lycorismvp.ui.details

import com.s097t0r1.lycorismvp.model.Photo
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.SingleState

interface DetailsView : MvpView {

    @SingleState
    fun showError()

    @SingleState
    fun showResult(photo: Photo)

    @AddToEndSingle
    fun showProgressBar()

    @AddToEndSingle
    fun hideProgressBar()


}