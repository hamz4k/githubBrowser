package com.hklouch.domain.project.search.repository

import com.hklouch.domain.project.model.Project
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable

interface SearchRepository {

    fun searchProjects(query: String, page: Int?, resultsPerPage: Int?): Observable<PagingWrapper<Project>>

}