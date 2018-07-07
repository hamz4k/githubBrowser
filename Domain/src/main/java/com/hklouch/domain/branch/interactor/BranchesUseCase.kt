package com.hklouch.domain.branch.interactor

import com.hklouch.domain.branch.interactor.BranchesUseCase.Params
import com.hklouch.domain.branch.model.Branch
import com.hklouch.domain.branch.repository.BranchesRepository
import com.hklouch.domain.shared.interactor.ObservableUseCase
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable
import javax.inject.Inject

open class BranchesUseCase @Inject constructor(private val repository: BranchesRepository) :
        ObservableUseCase<PagingWrapper<Branch>, Params?>() {

    override fun buildUseCaseObservable(params: Params?): Observable<PagingWrapper<Branch>> {
        if (params == null) throw IllegalArgumentException("Params can not be null")
        return repository.getBranches(params.ownerName,
                                      params.projectName,
                                      params.page,
                                      params.resultsPerPage)
    }

    data class Params(val ownerName: String,
                      val projectName: String,
                      val page: Int? = null,
                      val resultsPerPage: Int? = null)
}