package com.s097t0r1.lycorismvp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.s097t0r1.lycorismvp.App
import com.s097t0r1.lycorismvp.databinding.FragmentFavoritesBinding
import com.s097t0r1.lycorismvp.model.Photo
import com.s097t0r1.lycorismvp.ui.ItemClickListener
import com.s097t0r1.lycorismvp.ui.PhotoAdapter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.ktp.KTP
import javax.inject.Inject

class FavoritesFragment : MvpAppCompatFragment(), FavoritesView {

    @Inject
    @InjectPresenter
    lateinit var presenter: FavoritesPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    lateinit var binding: FragmentFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        KTP.openScope(App::class).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        binding.swipeRefreshLayout.setOnRefreshListener {
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

        with(binding.recyclerViewListFavoritePhotos) {
            adapter = _adapter
            layoutManager = _layoutManager
        }
    }

    override fun startRefreshing() {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun stopRefreshing() {
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun refreshListPhotos(photos: List<Photo>) {
        binding.recyclerViewListFavoritePhotos.visibility = View.GONE
        (binding.recyclerViewListFavoritePhotos.adapter as PhotoAdapter).submitList(photos)
    }

    override fun displayError() {
        binding.recyclerViewListFavoritePhotos.visibility = View.VISIBLE
    }
}