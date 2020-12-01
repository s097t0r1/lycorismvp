package com.s097t0r1.lycorismvp.ui.feed

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
import kotlinx.android.synthetic.main.fragment_feed.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class FeedFragment : MvpAppCompatFragment(), FeedView {

    @Inject
    @InjectPresenter
    lateinit var presenter: FeedPresenter

    @ProvidePresenter
    fun providePresenter(): FeedPresenter = presenter

    lateinit var recyclerViewListPhotos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_feed, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewListPhotos = view.findViewById(R.id.recyclerView_listPhotos)

        setupRecyclerView(recyclerViewListPhotos)
        setupListeners()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val _adapter = PhotoAdapter(object : ItemClickListener {
            override fun onClick(id: String) {
                presenter.navigate(id)
            }
        })
        val _layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        with(recyclerView) {
            adapter = _adapter
            layoutManager = _layoutManager
        }
    }

    private fun setupListeners() {
        swipeRefreshLayout.setOnRefreshListener {
            presenter.getPhotos(true)
        }
    }

    override fun startRefreshing() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun stopRefreshing() {
        swipeRefreshLayout.isRefreshing = false
    }


    override fun refreshListPhotos(photos: List<Photo>) {
        (recyclerViewListPhotos.adapter as PhotoAdapter).submitList(photos)
        textView_errorMessage.visibility = View.GONE
    }

    override fun displayError() {
        textView_errorMessage.visibility = View.VISIBLE
    }


}