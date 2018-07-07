package com.hklouch.domain.issue.interactor

import com.hklouch.domain.Data
import com.hklouch.domain.issue.interactor.IssuesUseCase.Params
import com.hklouch.domain.issue.repository.IssuesRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class IssuesUseCaseTest {

    @Mock private lateinit var repository: IssuesRepository

    private lateinit var useCase: IssuesUseCase

    private val projectData = Data()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        useCase = IssuesUseCase(repository)
    }

    @Test
    fun should_search_complete() {
        //Given
        given(repository.getIssues("owner", "project", 2, 50))
                .willReturn(Observable.just(projectData.issueList))
        //When
        useCase.buildUseCaseObservable(Params("owner", "project", 2, 50)).test()
                //Test
                .assertComplete()
    }

    @Test
    fun should_search_call_repository() {
        //Given
        given(repository.getIssues("owner", "project", 2, 50))
                .willReturn(Observable.just(projectData.issueList))
        //When
        useCase.buildUseCaseObservable(Params("owner", "project", 2, 50)).test()
        //Test
        verify(repository).getIssues("owner", "project", 2, 50)
    }

    @Test
    fun should_search_return_data() {
        //Given
        given(repository.getIssues("owner", "project", 2, 50))
                .willReturn(Observable.just(projectData.issueList))
        val expected = projectData.issueList
        //When
        useCase.buildUseCaseObservable(Params("owner", "project", 2, 50)).test()
                //Test
                .assertValue(expected)
    }
}