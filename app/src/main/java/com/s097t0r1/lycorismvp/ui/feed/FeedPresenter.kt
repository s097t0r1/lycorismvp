package com.s097t0r1.lycorismvp.ui.feed

import android.util.Log
import com.s097t0r1.lycorismvp.model.source.PhotoRepository
import com.s097t0r1.lycorismvp.utils.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class FeedPresenter @Inject constructor(
    private val photoRepository: PhotoRepository
) : MvpPresenter<FeedView>() {

    private val disposables = CompositeDisposable()

    init {
        getPhotos(true)
    }

    fun getPhotos(forceUpdate: Boolean) {
        photoRepository.getPhotos(forceUpdate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.startRefreshing() }
            .doOnError { Log.e("Getting data", it.toString()) }
            .doOnComplete { viewState.stopRefreshing() }
            .subscribe({ onSuccess ->
                if(onSuccess.isEmpty())
                    viewState.displayError()
                else
                    viewState.refreshListPhotos(onSuccess)
            }, { onError ->
                viewState.displayError()
            }).addTo(disposables)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

}