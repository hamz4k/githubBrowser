package com.hklouch.domain.contributor.repository

import com.hklouch.domain.shared.model.PagingWrapper
import com.hklouch.domain.shared.model.User
import io.reactivex.Observable

interface ContributorsRepository {

    fun getContributors(ownerName: String, projectName: String, page: Int?, resultsPerPage: Int?): Observable<PagingWrapper<User>>

}