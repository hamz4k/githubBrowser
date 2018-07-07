package com.hklouch.domain.project.search.interactor.interactor

import com.hklouch.domain.project.model.Project
import com.hklouch.domain.project.search.interactor.interactor.SearchProjectsUseCase.Params
import com.hklouch.domain.project.search.repository.SearchRepository
import com.hklouch.domain.shared.interactor.ObservableUseCase
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable
import javax.inject.Inject

open class SearchProjectsUseCase @Inject constructor(private val repository: SearchRepository)
    : ObservableUseCase<PagingWrapper<Project>, Params?>() {

    override fun buildUseCaseObservable(params: Params?): Observable<PagingWrapper<Project>> {
        if (params == null) throw IllegalArgumentException("Params can not be null")
        return repository.searchProjects(params.query, params.nextPage, params.resultsPerPage)
    }

    data class Params(val query: String,
                      val nextPage: Int? = null,
                      val resultsPerPage: Int? = null)
}