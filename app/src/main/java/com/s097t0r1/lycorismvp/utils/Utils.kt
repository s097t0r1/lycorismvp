package com.s097t0r1.lycorismvp.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(composition: CompositeDisposable) { composition.add(this) }