package com.s097t0r1.lycorismvp.ui.favorites

import com.s097t0r1.lycorismvp.model.Photo
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import moxy.viewstate.strategy.alias.Skip

interface FavoritesView : MvpView {

    @Skip
    fun startRefreshing()

    @Skip
    fun stopRefreshing()

    @SingleState
    fun refreshListPhotos(photos: List<Photo>)

    @SingleState
    fun displayError()
}