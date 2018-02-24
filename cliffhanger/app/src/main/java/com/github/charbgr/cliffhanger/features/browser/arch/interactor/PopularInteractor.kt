package com.github.charbgr.cliffhanger.features.browser.arch.interactor

import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange
import com.github.charbgr.cliffhanger.network.tmdb.TmdbAPI
import com.github.charbgr.cliffhanger.network.tmdb.entity.MiniMovieEntityMapper.transform
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class PopularInteractor : MovieBrowserInteractor {
  private val tmdbAPI: TmdbAPI = TmdbAPI.create(Schedulers.io())

  override fun fetch(page: Int): Observable<PartialChange> {
    return tmdbAPI.movieDAO.popularMovies(page)
        .map { PartialChange.Loaded(transform(it.results), it.page) as PartialChange }
        .startWith(PartialChange.Loading(page != 1))
        .onErrorReturn { PartialChange.Failed(it) }
        .share()
  }
}
