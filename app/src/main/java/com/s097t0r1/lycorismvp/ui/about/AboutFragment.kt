package com.s097t0r1.lycorismvp.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.s097t0r1.lycorismvp.R
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class AboutFragment : MvpAppCompatFragment(), AboutView {

    @InjectPresenter
    lateinit var presenter: AboutPresenter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_about, container, false)

        return root
    }
}