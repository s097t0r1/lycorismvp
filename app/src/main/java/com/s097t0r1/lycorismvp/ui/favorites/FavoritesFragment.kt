package com.s097t0r1.lycorismvp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.s097t0r1.lycorismvp.App
import com.s097t0r1.lycorismvp.R
import com.s097t0r1.lycorismvp.model.Photo
import com.s097t0r1.lycorismvp.ui.ItemClickListener
import com.s097t0r1.lycorismvp.ui.PhotoAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class FavoritesFragment : MvpAppCompatFragment(), FavoritesView {

    @Inject
    @InjectPresenter
    lateinit var presenter: FavoritesPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        swipeRefreshLayout.setOnRefreshListener {
            presenter.getPhotos()
        }
    }

    private fun setupRecyclerView() {

        val _adapter = PhotoAdapter(object : ItemClickListener {
            override fun onClick(id: String) {
                presenter.navigate(id)
            }
        })

        val _layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        with(recyclerView_listFavoritePhotos) {
            adapter = _adapter
            layoutManager = _layoutManager
        }
    }

    override fun startRefreshing() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun stopRefreshing() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun refreshListPhotos(photos: List<Photo>) {
        textView_errorMessage.visibility = View.GONE
        (recyclerView_listFavoritePhotos.adapter as PhotoAdapter).submitList(photos)
    }

    override fun displayError() {
        textView_errorMessage.visibility = View.VISIBLE
    }
}