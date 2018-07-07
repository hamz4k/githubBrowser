package com.hklouch.data.network.contributor

import com.hklouch.data.network.GithubReposService
import com.hklouch.data.network.util.toPagingWrapper
import com.hklouch.domain.contributor.repository.ContributorsRepository
import com.hklouch.domain.shared.model.PagingWrapper
import com.hklouch.domain.shared.model.User
import io.reactivex.Observable
import javax.inject.Inject


class NetworkContributorsRepository @Inject constructor(private val service: GithubReposService) : ContributorsRepository {

    companion object {
        private const val PAGING_PARAM = "page"
        private const val DEFAULT_RESULTS_PER_PAGE = 50
    }

    override fun getContributors(ownerName: String, projectName: String, page: Int?, resultsPerPage: Int?): Observable<PagingWrapper<User>> {
        return service.getContributors(ownerName,
                                       projectName,
                                       page,
                                       resultsPerPage ?: DEFAULT_RESULTS_PER_PAGE).map { result ->
            result.toPagingWrapper(pagingParameter = PAGING_PARAM,
                                   mapper = { result.response()?.body()?.toUsers() })

        }
    }
}