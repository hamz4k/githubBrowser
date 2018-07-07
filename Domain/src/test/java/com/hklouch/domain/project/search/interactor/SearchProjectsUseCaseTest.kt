package com.hklouch.domain.project.search.interactor

import com.hklouch.domain.Data
import com.hklouch.domain.project.search.interactor.interactor.SearchProjectsUseCase
import com.hklouch.domain.project.search.interactor.interactor.SearchProjectsUseCase.Params
import com.hklouch.domain.project.search.repository.SearchRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchProjectsUseCaseTest {

    @Mock private lateinit var repository: SearchRepository

    private lateinit var searchProjectsUseCase: SearchProjectsUseCase

    private val projectData = Data()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        searchProjectsUseCase = SearchProjectsUseCase(repository)
    }

    @Test
    fun should_search_complete() {
        //Given
        given(repository.searchProjects("q", null, null))
                .willReturn(Observable.just(projectData.projectList))
        //When
        searchProjectsUseCase.buildUseCaseObservable(Params("q")).test()
                //Test
                .assertComplete()
    }

    @Test
    fun should_search_call_repository() {
        //Given
        given(repository.searchProjects("q", null, null))
                .willReturn(Observable.just(projectData.projectList))
        //When
        searchProjectsUseCase.buildUseCaseObservable(Params("q")).test()
        //Test
        verify(repository).searchProjects("q", null, null)
    }


    @Test
    fun should_search_return_data() {
        //Given
        given(repository.searchProjects("q", null, null))
                .willReturn(Observable.just(projectData.projectList))
        val expected = projectData.projectList
        //When
        searchProjectsUseCase.buildUseCaseObservable(Params("q")).test()
                //Test
                .assertValue(expected)
    }

}