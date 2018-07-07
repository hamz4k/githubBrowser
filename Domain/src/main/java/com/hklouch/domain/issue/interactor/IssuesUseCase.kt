package com.hklouch.domain.issue.interactor

import com.hklouch.domain.issue.interactor.IssuesUseCase.Params
import com.hklouch.domain.issue.model.Issue
import com.hklouch.domain.issue.repository.IssuesRepository
import com.hklouch.domain.shared.interactor.ObservableUseCase
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable
import javax.inject.Inject

open class IssuesUseCase @Inject constructor(private val repository: IssuesRepository) : ObservableUseCase<PagingWrapper<Issue>, Params>() {

    override fun buildUseCaseObservable(params: Params?): Observable<PagingWrapper<Issue>> {
        if (params == null) throw IllegalArgumentException("Params can not be null")
        return repository.getIssues(params.ownerName,
                                    params.projectName,
                                    params.page,
                                    params.resultsPerPage)
    }

    data class Params(val ownerName: String,
                      val projectName: String,
                      val page: Int? = null,
                      val resultsPerPage: Int? = null)
}