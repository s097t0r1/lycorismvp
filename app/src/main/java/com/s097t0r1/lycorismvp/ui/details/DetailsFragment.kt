package com.s097t0r1.lycorismvp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.s097t0r1.lycorismvp.App
import com.s097t0r1.lycorismvp.R
import com.s097t0r1.lycorismvp.model.Photo
import com.s097t0r1.lycorismvp.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_details.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_ID = "id"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : MvpAppCompatFragment(), DetailsView, BackButtonListener {

    @Inject
    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter


    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getPhoto(requireArguments().getString(ARG_ID, ""))

        toggleButton_favoriteButton.setOnCheckedChangeListener { _, checked ->
            presenter.setFavoriteState(checked)
        }
    }



    override fun showError() {
        textView_errorMessage.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        progressBar_loadingDetails.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar_loadingDetails.visibility = View.GONE
    }

    override fun showResult(photo: Photo) {
        Glide.with(this)
            .load(photo.imageUrl)
            .centerCrop()
            .error(R.drawable.ic_error_outline_24)
            .placeholder(R.drawable.ic_person_24)
            .into(imageView_detailsPhoto)

        textView_descriptionPhoto.text = photo.description

        toggleButton_favoriteButton.isChecked = photo.isFavorite

        linearLayout_details.visibility = View.VISIBLE
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param id ID of photo
         * @return A new instance of fragment DetailsFragment.
         */
        @JvmStatic
        fun newInstance(id: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ID, id)
                }
            }
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }


}