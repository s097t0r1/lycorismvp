package com.s097t0r1.lycorismvp.ui.feed

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface FeedView : MvpView {
    @AddToEndSingle
    fun displayMessage()
}