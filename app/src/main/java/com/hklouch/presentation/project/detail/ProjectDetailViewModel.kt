package com.hklouch.presentation.project.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.hklouch.domain.project.detail.interactor.GetProjectDetailUseCase
import com.hklouch.domain.project.detail.interactor.GetProjectDetailUseCase.Params
import com.hklouch.domain.project.model.Project
import com.hklouch.presentation.model.UiProjectItem
import com.hklouch.presentation.model.toUiProjectItem
import com.hklouch.ui.State
import com.hklouch.ui.loading
import com.hklouch.ui.toState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectViewModelFactory @Inject constructor(private val getProjectDetailUseCase: GetProjectDetailUseCase) {
    fun supply(ownerName: String, projectName: String) = ProjectDetailViewModel(getProjectDetailUseCase, ownerName, projectName)
}

class ProjectDetailViewModel(private val getProjectDetailUseCase: GetProjectDetailUseCase,
                             val ownerName: String,
                             val projectName: String) : ViewModel() {

    private val liveData: MutableLiveData<State<UiProjectItem>> = MutableLiveData()

    init {
        fetchProjectDetail(ownerName, projectName)
    }

    override fun onCleared() {
        getProjectDetailUseCase.dispose()
        super.onCleared()
    }

    fun getResultProjectDetail() = liveData

    fun fetchProjectDetail(ownerName: String, projectName: String) {
        liveData.postValue(loading())
        getProjectDetailUseCase.execute(ProjectDetailSubscriber(), Params(ownerName, projectName))
    }

    inner class ProjectDetailSubscriber : DisposableSingleObserver<Project>() {

        override fun onSuccess(result: Project) {
            liveData.postValue(result.toUiProjectItem().toState())
        }

        override fun onError(e: Throwable) {
            liveData.postValue(e.toState())
        }

    }
}