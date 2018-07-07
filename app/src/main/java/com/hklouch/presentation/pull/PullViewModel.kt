package com.hklouch.presentation.pull

import com.hklouch.domain.pull.interactor.PullsUseCase
import com.hklouch.domain.pull.interactor.PullsUseCase.Params
import com.hklouch.domain.pull.model.Pull
import com.hklouch.presentation.ResourceListViewModel
import com.hklouch.presentation.ResourceListViewModelFactory
import com.hklouch.presentation.model.UiPullItem
import kotlin.reflect.KFunction1

class PullViewModelFactory(private val useCase: PullsUseCase,
                           private val mapper: KFunction1<Pull, UiPullItem>) :
        ResourceListViewModelFactory<Pull, UiPullItem, Params>() {

    override fun supply(params: Params?): ResourceListViewModel<Pull, UiPullItem, Params> {
        return PullViewModel(useCase = useCase, mapper = mapper, params = params)
    }
}

class PullViewModel(useCase: PullsUseCase, mapper: KFunction1<Pull, UiPullItem>, params: Params?) :
        ResourceListViewModel<Pull, UiPullItem, Params>(useCase, mapper, params)