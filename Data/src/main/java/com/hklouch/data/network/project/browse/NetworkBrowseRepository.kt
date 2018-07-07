package com.hklouch.data.network.project.browse

import com.hklouch.data.network.GithubReposService
import com.hklouch.data.network.project.toProjectList
import com.hklouch.data.network.util.toPagingWrapper
import com.hklouch.domain.project.browse.repository.BrowseRepository
import com.hklouch.domain.project.model.Project
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable
import javax.inject.Inject

class NetworkBrowseRepository @Inject constructor(private val service: GithubReposService) : BrowseRepository {

    companion object {
        private const val PAGING_PARAM = "since"
    }


    override fun getProjects(next: Int?): Observable<PagingWrapper<Project>> {
        return service.getProjects(next).map { result ->

            result.toPagingWrapper(pagingParameter = PAGING_PARAM,
                                   mapper = { result.response()?.body()?.toProjectList() })
        }
    }

}