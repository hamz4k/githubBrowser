package com.hklouch.domain.project.browse.interactor

import com.hklouch.domain.project.browse.interactor.GetProjectsUseCase.Params
import com.hklouch.domain.project.browse.repository.BrowseRepository
import com.hklouch.domain.project.model.Project
import com.hklouch.domain.shared.interactor.ObservableUseCase
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable
import javax.inject.Inject

open class GetProjectsUseCase @Inject constructor(private val repository: BrowseRepository)
    : ObservableUseCase<PagingWrapper<Project>, Params?>() {

    override fun buildUseCaseObservable(params: Params?): Observable<PagingWrapper<Project>> {
        return repository.getProjects(params?.next)
    }

    data class Params(val next: Int?)
}