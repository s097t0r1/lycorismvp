package com.s097t0r1.lycorismvp.ui


import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.s097t0r1.lycorismvp.ui.favorites.FavoritesFragment
import com.s097t0r1.lycorismvp.ui.feed.FeedFragment


object Screens {

    fun Feed() = FragmentScreen() { FeedFragment() }

    fun Favorites() = FragmentScreen() { FavoritesFragment() }
}