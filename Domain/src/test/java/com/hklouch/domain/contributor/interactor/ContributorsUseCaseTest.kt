package com.hklouch.domain.contributor.interactor

import com.hklouch.domain.Data
import com.hklouch.domain.contributor.interactor.ContributorsUseCase.Params
import com.hklouch.domain.contributor.repository.ContributorsRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ContributorsUseCaseTest {

    @Mock private lateinit var repository: ContributorsRepository

    private lateinit var useCase: ContributorsUseCase

    private val projectData = Data()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        useCase = ContributorsUseCase(repository)
    }

    @Test
    fun should_search_complete() {
        //Given
        given(repository.getContributors("owner", "project", 2, 50))
                .willReturn(Observable.just(projectData.contributorList))
        //When
        useCase.buildUseCaseObservable(Params("owner", "project", 2, 50)).test()
                //Test
                .assertComplete()
    }

    @Test
    fun should_search_call_repository() {
        //Given
        given(repository.getContributors("owner", "project", 2, 50))
                .willReturn(Observable.just(projectData.contributorList))
        //When
        useCase.buildUseCaseObservable(Params("owner", "project", 2, 50)).test()
        //Test
        verify(repository).getContributors("owner", "project", 2, 50)
    }

    @Test
    fun should_search_return_data() {
        //Given
        given(repository.getContributors("owner", "project", 2, 50))
                .willReturn(Observable.just(projectData.contributorList))
        val expected = projectData.contributorList
        //When
        useCase.buildUseCaseObservable(Params("owner", "project", 2, 50)).test()
                //Test
                .assertValue(expected)
    }
}