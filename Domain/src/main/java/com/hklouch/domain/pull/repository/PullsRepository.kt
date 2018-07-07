package com.hklouch.domain.pull.repository

import com.hklouch.domain.pull.interactor.PullsUseCase.Params.State
import com.hklouch.domain.pull.model.Pull
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable

interface PullsRepository {

    fun getPulls(ownerName: String, projectName: String, state: State, page: Int?, resultsPerPage: Int?): Observable<PagingWrapper<Pull>>

}