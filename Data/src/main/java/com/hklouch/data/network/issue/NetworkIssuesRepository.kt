package com.hklouch.data.network.issue

import com.hklouch.data.network.GithubReposService
import com.hklouch.data.network.util.toPagingWrapper
import com.hklouch.domain.issue.model.Issue
import com.hklouch.domain.issue.repository.IssuesRepository
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable
import javax.inject.Inject


class NetworkIssuesRepository @Inject constructor(private val service: GithubReposService) : IssuesRepository {

    companion object {
        private const val PAGING_PARAM = "page"
        private const val DEFAULT_RESULTS_PER_PAGE = 50
    }

    override fun getIssues(ownerName: String, projectName: String, page: Int?, resultsPerPage: Int?): Observable<PagingWrapper<Issue>> {
        return service.getIssues(ownerName,
                                 projectName,
                                 page,
                                 resultsPerPage ?: DEFAULT_RESULTS_PER_PAGE).map { result ->
            result.toPagingWrapper(pagingParameter = PAGING_PARAM,
                                   mapper = { result.response()?.body()?.toIssues() })

        }
    }
}