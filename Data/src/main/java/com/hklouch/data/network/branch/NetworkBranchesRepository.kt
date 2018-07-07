package com.hklouch.data.network.branch

import com.hklouch.data.network.GithubReposService
import com.hklouch.data.network.util.toPagingWrapper
import com.hklouch.domain.branch.model.Branch
import com.hklouch.domain.branch.repository.BranchesRepository
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable
import javax.inject.Inject


class NetworkBranchesRepository @Inject constructor(private val service: GithubReposService) : BranchesRepository {

    companion object {
        private const val PAGING_PARAM = "page"
        private const val DEFAULT_RESULTS_PER_PAGE = 50
    }

    override fun getBranches(ownerName: String, projectName: String, page: Int?, resultsPerPage: Int?): Observable<PagingWrapper<Branch>> {
        return service.getBranches(ownerName,
                                   projectName, page,
                                   resultsPerPage ?: DEFAULT_RESULTS_PER_PAGE).map { result ->
            result.toPagingWrapper(pagingParameter = PAGING_PARAM,
                                   mapper = { result.response()?.body()?.toBranches() })

        }
    }
}