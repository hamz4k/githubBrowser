package com.hklouch.domain.branch.repository

import com.hklouch.domain.branch.model.Branch
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable

interface BranchesRepository {

    fun getBranches(ownerName: String, projectName: String, page: Int?, resultsPerPage: Int?): Observable<PagingWrapper<Branch>>

}