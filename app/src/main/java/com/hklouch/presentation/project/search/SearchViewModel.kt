package com.hklouch.presentation.project.search

import com.hklouch.domain.project.model.Project
import com.hklouch.domain.project.search.interactor.interactor.SearchProjectsUseCase
import com.hklouch.domain.project.search.interactor.interactor.SearchProjectsUseCase.Params
import com.hklouch.presentation.ResourceListViewModel
import com.hklouch.presentation.ResourceListViewModelFactory
import com.hklouch.presentation.model.UiProjectPreviewItem
import kotlin.reflect.KFunction1

class SearchViewModelFactory(private val useCase: SearchProjectsUseCase,
                             private val mapper: KFunction1<Project, UiProjectPreviewItem>) :
        ResourceListViewModelFactory<Project, UiProjectPreviewItem, Params>() {

    override fun supply(params: Params?): ResourceListViewModel<Project, UiProjectPreviewItem, Params> {
        return SearchViewModel(useCase = useCase, mapper = mapper, params = params)
    }
}

class SearchViewModel(useCase: SearchProjectsUseCase, mapper: KFunction1<Project, UiProjectPreviewItem>, params: Params?) :
        ResourceListViewModel<Project, UiProjectPreviewItem, Params>(useCase, mapper, params, false)