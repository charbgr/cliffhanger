package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.arch.View
import io.reactivex.Observable

interface View : View {
  fun fetchMovieIntent(): Observable<Int>
}
