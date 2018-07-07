package com.hklouch.domain.project.detail.interactor

import com.hklouch.domain.project.detail.interactor.GetProjectDetailUseCase.Params
import com.hklouch.domain.project.detail.repository.ProjectRepository
import com.hklouch.domain.project.model.Project
import com.hklouch.domain.shared.interactor.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

open class GetProjectDetailUseCase @Inject constructor(private val repository: ProjectRepository) : SingleUseCase<Project, Params>() {

    override fun buildUseCaseObservable(params: Params?): Single<Project> {
        if (params == null) throw IllegalArgumentException("Params can not be null")
        return repository.getProject(params.ownerName, params.projectName)
    }

    data class Params(val ownerName: String, val projectName: String)
}