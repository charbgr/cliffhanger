package com.github.charbgr.cliffhanger.api_tmdb.dao

import com.github.charbgr.cliffhanger.api_tmdb.Routes
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchDAO {
  @GET(Routes.SEARCH_ROUTE + "movie")
  fun searchMovie(@Query("query") searchedMovie: String): Observable<MovieResults>
}
