package com.s097t0r1.lycorismvp.ui.feed

import android.util.Log
import com.s097t0r1.lycorismvp.model.source.PhotoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class FeedPresenter @Inject constructor(
    private val photoRepository: PhotoRepository
) : MvpPresenter<FeedView>() {

    init {
        getPhotos(true)
    }

    fun getPhotos(forceUpdate: Boolean) {
        photoRepository.getPhotos(forceUpdate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ Log.d("Getting data", it.toString()) },
                {Log.d("Getting data", it.toString())}
            )
    }

}