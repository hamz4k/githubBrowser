package com.hklouch.domain.project.browse.repository

import com.hklouch.domain.project.model.Project
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable

interface BrowseRepository {

    fun getProjects(next: Int?): Observable<PagingWrapper<Project>>

}