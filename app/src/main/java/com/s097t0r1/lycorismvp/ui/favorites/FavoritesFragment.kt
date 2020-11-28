package com.s097t0r1.lycorismvp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.s097t0r1.lycorismvp.R
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class FavoritesFragment : MvpAppCompatFragment(), FavoritesView {

    @InjectPresenter
    lateinit var presenter: FavoritesPresenter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)
        return root
    }
}