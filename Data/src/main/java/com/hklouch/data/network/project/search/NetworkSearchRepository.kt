package com.hklouch.data.network.project.search

import com.hklouch.data.network.GithubReposService
import com.hklouch.data.network.project.toProjectList
import com.hklouch.data.network.util.toPagingWrapper
import com.hklouch.domain.project.model.Project
import com.hklouch.domain.project.search.repository.SearchRepository
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable
import javax.inject.Inject

class NetworkSearchRepository @Inject constructor(private val service: GithubReposService) : SearchRepository {

    companion object {
        private const val PAGING_PARAM = "page"
        private const val DEFAULT_RESULTS_PER_PAGE = 50
    }

    override fun searchProjects(query: String, page: Int?, resultsPerPage: Int?): Observable<PagingWrapper<Project>> {
        return service.search(query = query, page = page, resultsPerPage = resultsPerPage
                ?: DEFAULT_RESULTS_PER_PAGE).map { result ->

            result.toPagingWrapper(pagingParameter = PAGING_PARAM,
                                   mapper = { result.response()?.body()?.toProjectList() })
        }
    }

}