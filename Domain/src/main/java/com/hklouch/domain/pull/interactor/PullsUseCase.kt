package com.hklouch.domain.pull.interactor

import com.hklouch.domain.pull.interactor.PullsUseCase.Params
import com.hklouch.domain.pull.interactor.PullsUseCase.Params.State.OPEN
import com.hklouch.domain.pull.model.Pull
import com.hklouch.domain.pull.repository.PullsRepository
import com.hklouch.domain.shared.interactor.ObservableUseCase
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable
import javax.inject.Inject

open class PullsUseCase @Inject constructor(private val repository: PullsRepository) : ObservableUseCase<PagingWrapper<Pull>, Params>() {

    override fun buildUseCaseObservable(params: Params?): Observable<PagingWrapper<Pull>> {
        if (params == null) throw IllegalArgumentException("Params can not be null")
        return repository.getPulls(params.ownerName,
                                   params.projectName,
                                   params.state,
                                   params.page,
                                   params.resultsPerPage)
    }

    data class Params(val ownerName: String,
                      val projectName: String,
                      val state: State = OPEN,
                      val page: Int? = null,
                      val resultsPerPage: Int? = null) {

        enum class State { OPEN, CLOSED, ALL }
    }
}