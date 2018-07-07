package com.hklouch.data.network.project.detail

import com.hklouch.data.network.GithubReposService
import com.hklouch.data.network.project.toProject
import com.hklouch.domain.project.detail.repository.ProjectRepository
import com.hklouch.domain.project.model.Project
import io.reactivex.Single
import javax.inject.Inject

class NetworkProjectRepository @Inject constructor(private val service: GithubReposService) : ProjectRepository {

    override fun getProject(ownerName: String, projectName: String): Single<Project> {
        return service.getProject(ownerName, projectName).map { it.toProject() }
    }
}