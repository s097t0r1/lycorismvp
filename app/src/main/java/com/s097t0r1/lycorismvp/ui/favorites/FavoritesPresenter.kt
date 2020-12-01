package com.s097t0r1.lycorismvp.ui.favorites

import com.github.terrakok.cicerone.Router
import com.s097t0r1.lycorismvp.model.source.PhotoRepository
import com.s097t0r1.lycorismvp.ui.Screens
import com.s097t0r1.lycorismvp.utils.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class FavoritesPresenter @Inject constructor(
    private val photoRepository: PhotoRepository,
    private val router: Router
) : MvpPresenter<FavoritesView>() {

    val disposables = CompositeDisposable()

    init {
        getPhotos()
    }

    fun getPhotos() {
        photoRepository.getPhotos(false)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.startRefreshing() }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { viewState.stopRefreshing() }
            .subscribe({ onSuccess ->
                if(onSuccess.isEmpty())
                    viewState.displayError()
                else
                    viewState.refreshListPhotos(onSuccess)
            }, { onError ->
                viewState.displayError()
            }).addTo(disposables)
    }

    fun navigate(id: String) {
        router.navigateTo(Screens.Details(id))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}