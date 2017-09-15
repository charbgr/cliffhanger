package com.github.charbgr.cliffhanger.features.home.arch.state

import com.github.charbgr.cliffhanger.features.home.arch.HomeViewModel
import com.github.charbgr.cliffhanger.features.home.arch.state.MovieCategory.NowPlaying
import com.github.charbgr.cliffhanger.features.home.arch.state.MovieCategory.Popular
import com.github.charbgr.cliffhanger.features.home.arch.state.MovieCategory.TopRated
import com.github.charbgr.cliffhanger.features.home.arch.state.MovieCategory.Upcoming

class HomeStateReducer {

  private val categoryReducer: CategoryStateReducer = CategoryStateReducer()

  val reduce: (previousViewModel: HomeViewModel, partialChange: PartialChange) -> HomeViewModel =
      { previousViewModel, partialChange ->

        when (partialChange.movieCategory) {
          is TopRated -> {
            val prevCategoryVM = previousViewModel.topRated
            previousViewModel.copy(topRated = categoryReducer.reduce(prevCategoryVM, partialChange),
                currentPartialChange = partialChange)
          }
          is NowPlaying -> {
            val prevCategoryVM = previousViewModel.nowPlaying
            previousViewModel.copy(
                nowPlaying = categoryReducer.reduce(prevCategoryVM, partialChange),
                currentPartialChange = partialChange)
          }
          is Upcoming -> {
            val prevCategoryVM = previousViewModel.upcoming
            previousViewModel.copy(upcoming = categoryReducer.reduce(prevCategoryVM, partialChange),
                currentPartialChange = partialChange)
          }
          is Popular -> {
            val prevCategoryVM = previousViewModel.popular
            previousViewModel.copy(popular = categoryReducer.reduce(prevCategoryVM, partialChange),
                currentPartialChange = partialChange)
          }
          else -> {
            previousViewModel
          }
        }
      }
}