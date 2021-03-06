package com.github.charbgr.cliffhanger.api_tmdb.entity

import com.github.charbgr.cliffhanger.domain.SearchResults

object SearchResultsEntityMapper {

  fun transform(entity: SearchResultsEntity): SearchResults {
    return SearchResults(entity.page, MiniMovieEntityMapper.transform(entity.results))
  }
}
