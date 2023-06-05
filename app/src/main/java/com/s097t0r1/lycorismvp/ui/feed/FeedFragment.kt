package com.s097t0r1.lycorismvp.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.s097t0r1.lycorismvp.App
import com.s097t0r1.lycorismvp.databinding.FragmentFeedBinding
import com.s097t0r1.lycorismvp.model.Photo
import com.s097t0r1.lycorismvp.ui.ItemClickListener
import com.s097t0r1.lycorismvp.ui.PhotoAdapter
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

    lateinit var binding: FragmentFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(binding.recyclerViewListPhotos)
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
        binding.swipeRefreshLayout.setOnRefreshListener {
            presenter.getPhotos(true)
        }
    }

    override fun startRefreshing() {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun stopRefreshing() {
        binding.swipeRefreshLayout.isRefreshing = false
    }


    override fun refreshListPhotos(photos: List<Photo>) {
        (binding.recyclerViewListPhotos.adapter as PhotoAdapter).submitList(photos)
        binding.textViewErrorMessage.visibility = View.GONE
    }

    override fun displayError() {
        binding.textViewErrorMessage.visibility = View.VISIBLE
    }


}