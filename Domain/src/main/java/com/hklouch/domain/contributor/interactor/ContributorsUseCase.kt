package com.hklouch.domain.contributor.interactor

import com.hklouch.domain.contributor.interactor.ContributorsUseCase.Params
import com.hklouch.domain.contributor.repository.ContributorsRepository
import com.hklouch.domain.shared.interactor.ObservableUseCase
import com.hklouch.domain.shared.model.PagingWrapper
import com.hklouch.domain.shared.model.User
import io.reactivex.Observable
import javax.inject.Inject

open class ContributorsUseCase @Inject constructor(private val repository: ContributorsRepository) : ObservableUseCase<PagingWrapper<User>, Params>() {

    override fun buildUseCaseObservable(params: Params?): Observable<PagingWrapper<User>> {
        if (params == null) throw IllegalArgumentException("Params can not be null")
        return repository.getContributors(params.ownerName,
                                          params.projectName,
                                          params.page,
                                          params.resultsPerPage)
    }

    data class Params(val ownerName: String,
                      val projectName: String,
                      val page: Int? = null,
                      val resultsPerPage: Int? = null)
}