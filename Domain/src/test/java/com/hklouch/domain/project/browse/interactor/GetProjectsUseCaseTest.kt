package com.hklouch.domain.project.browse.interactor

import com.hklouch.domain.Data
import com.hklouch.domain.project.browse.interactor.GetProjectsUseCase.Params
import com.hklouch.domain.project.browse.repository.BrowseRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GetProjectsUseCaseTest {

    @Mock private lateinit var repository: BrowseRepository

    private lateinit var getProjectsUseCase: GetProjectsUseCase

    private val projectData = Data()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        getProjectsUseCase = GetProjectsUseCase(repository)
    }

    @Test
    fun should_search_complete() {
        //Given
        given(repository.getProjects(321))
                .willReturn(Observable.just(projectData.projectList))
        //When
        getProjectsUseCase.buildUseCaseObservable(Params(321)).test()
                //Test
                .assertComplete()
    }

    @Test
    fun should_search_call_repository() {
        //Given
        given(repository.getProjects(321))
                .willReturn(Observable.just(projectData.projectList))
        //When
        getProjectsUseCase.buildUseCaseObservable(Params(321)).test()
        //Test
        verify(repository).getProjects(321)
    }

    @Test
    fun should_search_return_data() {
        //Given
        given(repository.getProjects(321))
                .willReturn(Observable.just(projectData.projectList))
        val expected = projectData.projectList
        //When
        getProjectsUseCase.buildUseCaseObservable(Params(321)).test()
                //Test
                .assertValue(expected)
    }

}