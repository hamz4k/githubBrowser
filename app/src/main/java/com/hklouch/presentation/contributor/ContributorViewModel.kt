package com.hklouch.presentation.contributor

import com.hklouch.domain.contributor.interactor.ContributorsUseCase
import com.hklouch.domain.contributor.interactor.ContributorsUseCase.Params
import com.hklouch.domain.shared.model.User
import com.hklouch.presentation.ResourceListViewModel
import com.hklouch.presentation.ResourceListViewModelFactory
import com.hklouch.presentation.model.UiUserItem
import kotlin.reflect.KFunction1

class ContributorViewModelFactory(private val useCase: ContributorsUseCase,
                                  private val mapper: KFunction1<User, UiUserItem>) :
        ResourceListViewModelFactory<User, UiUserItem, Params>() {

    override fun supply(params: Params?): ResourceListViewModel<User, UiUserItem, Params> {
        return ContributorViewModel(useCase = useCase, mapper = mapper, params = params)
    }
}

class ContributorViewModel(useCase: ContributorsUseCase, mapper: KFunction1<User, UiUserItem>, params: Params?) :
        ResourceListViewModel<User, UiUserItem, Params>(useCase, mapper, params)