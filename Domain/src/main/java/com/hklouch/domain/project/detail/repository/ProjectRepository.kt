package com.hklouch.domain.project.detail.repository

import com.hklouch.domain.project.model.Project
import io.reactivex.Single

interface ProjectRepository {

    fun getProject(ownerName: String, projectName: String): Single<Project>

}