package com.s097t0r1.lycorismvp.ui.details

import com.s097t0r1.lycorismvp.model.Photo
import com.s097t0r1.lycorismvp.model.source.PhotoRepository
import com.s097t0r1.lycorismvp.utils.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class DetailsPresenter @Inject constructor(
    private val photoRepository: PhotoRepository
) : MvpPresenter<DetailsView>() {

    private val disposables = CompositeDisposable()

    private lateinit var _photo: Photo

    fun getPhoto(id: String) {
        photoRepository.getPhoto(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgressBar() }
            .doOnSuccess { viewState.hideProgressBar() }
            .subscribe({ onSuccess ->
                _photo = onSuccess
                viewState.showResult(onSuccess)
            }, { onError ->
                viewState.showError()
            }).addTo(disposables)
    }

    fun setFavoriteState(favorite: Boolean) {
        if(favorite)
            photoRepository.insertPhoto(_photo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        else
            photoRepository.deletePhoto(_photo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()

        _photo.isFavorite = favorite
    }

}