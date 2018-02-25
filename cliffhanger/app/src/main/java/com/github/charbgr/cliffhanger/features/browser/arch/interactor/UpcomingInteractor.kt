package com.github.charbgr.cliffhanger.features.browser.arch.interactor

import com.github.charbgr.cliffhanger.BuildConfig
import com.github.charbgr.cliffhanger.api_tmdb.TmdbAPI
import com.github.charbgr.cliffhanger.api_tmdb.entity.MiniMovieEntityMapper.transform
import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class UpcomingInteractor() : MovieBrowserInteractor {
  private val tmdbAPI: TmdbAPI = TmdbAPI(BuildConfig.TMDB_API_KEY, Schedulers.io())

  override fun fetch(page: Int): Observable<PartialChange> {
    return tmdbAPI.movieDAO.upcomingMovies(page)
        .map { PartialChange.Loaded(transform(it.results), it.page) as PartialChange }
        .startWith(PartialChange.Loading(page != 1))
        .onErrorReturn { PartialChange.Failed(it) }
        .share()
  }

}
