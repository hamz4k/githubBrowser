package com.hklouch.presentation.project.browse

import com.hklouch.domain.project.browse.interactor.GetProjectsUseCase
import com.hklouch.domain.project.browse.interactor.GetProjectsUseCase.Params
import com.hklouch.domain.project.model.Project
import com.hklouch.presentation.ResourceListViewModel
import com.hklouch.presentation.ResourceListViewModelFactory
import com.hklouch.presentation.model.UiProjectPreviewItem
import kotlin.reflect.KFunction1

class BrowseViewModelFactory(private val useCase: GetProjectsUseCase,
                             private val mapper: KFunction1<Project, UiProjectPreviewItem>) :
        ResourceListViewModelFactory<Project, UiProjectPreviewItem, Params>() {

    override fun supply(params: Params?): ResourceListViewModel<Project, UiProjectPreviewItem, Params> {
        return BrowseViewModel(useCase = useCase, mapper = mapper, params = params)
    }
}

class BrowseViewModel(useCase: GetProjectsUseCase, mapper: KFunction1<Project, UiProjectPreviewItem>, params: Params?) :
        ResourceListViewModel<Project, UiProjectPreviewItem, Params>(useCase, mapper, params)