package com.hklouch.data.network.pull

import com.hklouch.data.network.GithubReposService
import com.hklouch.data.network.util.toPagingWrapper
import com.hklouch.domain.pull.interactor.PullsUseCase.Params.State
import com.hklouch.domain.pull.model.Pull
import com.hklouch.domain.pull.repository.PullsRepository
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable
import javax.inject.Inject

class NetworkPullsRepository @Inject constructor(private val service: GithubReposService) : PullsRepository {

    companion object {
        private const val PAGING_PARAM = "page"
        private const val DEFAULT_RESULTS_PER_PAGE = 50
    }

    override fun getPulls(ownerName: String, projectName: String, state: State, page: Int?, resultsPerPage: Int?): Observable<PagingWrapper<Pull>> {

        return service.getPulls(ownerName,
                                projectName,
                                state.toParam(),
                                page,
                                resultsPerPage ?: DEFAULT_RESULTS_PER_PAGE).map { result ->
            result.toPagingWrapper(pagingParameter = PAGING_PARAM,
                                   mapper = { result.response()?.body()?.toPulls() })

        }
    }
}