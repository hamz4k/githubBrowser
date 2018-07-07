package com.hklouch.domain.pull.interactor

import com.hklouch.domain.Data
import com.hklouch.domain.pull.interactor.PullsUseCase.Params
import com.hklouch.domain.pull.interactor.PullsUseCase.Params.State.CLOSED
import com.hklouch.domain.pull.interactor.PullsUseCase.Params.State.OPEN
import com.hklouch.domain.pull.repository.PullsRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PullsUseCaseTest {

    @Mock private lateinit var repository: PullsRepository

    private lateinit var useCase: PullsUseCase

    private val projectData = Data()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        useCase = PullsUseCase(repository)
    }

    @Test
    fun should_search_complete() {
        //Given
        given(repository.getPulls("owner", "project", OPEN, 2, 50))
                .willReturn(Observable.just(projectData.pullList))
        //When
        useCase.buildUseCaseObservable(Params("owner", "project", OPEN, 2, 50)).test()
                //Test
                .assertComplete()
    }

    @Test
    fun should_search_call_repository() {
        //Given
        given(repository.getPulls("owner", "project", OPEN, 2, 50))
                .willReturn(Observable.just(projectData.pullList))
        //When
        useCase.buildUseCaseObservable(Params("owner", "project", OPEN, 2, 50)).test()
        //Test
        verify(repository).getPulls("owner", "project", OPEN, 2, 50)
    }

    @Test
    fun should_search_return_data() {
        //Given
        given(repository.getPulls("owner", "project", CLOSED, 2, 50))
                .willReturn(Observable.just(projectData.pullList))
        val expected = projectData.pullList
        //When
        useCase.buildUseCaseObservable(Params("owner", "project", CLOSED, 2, 50)).test()
                //Test
                .assertValue(expected)
    }
}