package com.hklouch.domain.issue.repository

import com.hklouch.domain.issue.model.Issue
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable

interface IssuesRepository {

    fun getIssues(ownerName: String, projectName: String, page: Int?, resultsPerPage: Int?): Observable<PagingWrapper<Issue>>

}