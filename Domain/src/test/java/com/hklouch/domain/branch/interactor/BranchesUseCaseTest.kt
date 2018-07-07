package com.hklouch.domain.branch.interactor

import com.hklouch.domain.Data
import com.hklouch.domain.branch.interactor.BranchesUseCase.Params
import com.hklouch.domain.branch.repository.BranchesRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class BranchesUseCaseTest {

    @Mock private lateinit var repository: BranchesRepository

    private lateinit var branchesUseCase: BranchesUseCase

    private val projectData = Data()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        branchesUseCase = BranchesUseCase(repository)
    }

    @Test
    fun should_search_complete() {
        //Given
        given(repository.getBranches("owner", "project", 2, 50))
                .willReturn(Observable.just(projectData.branchList))
        //When
        branchesUseCase.buildUseCaseObservable(Params("owner", "project", 2, 50)).test()
                //Test
                .assertComplete()
    }

    @Test
    fun should_search_call_repository() {
        //Given
        given(repository.getBranches("owner", "project", 2, 50))
                .willReturn(Observable.just(projectData.branchList))
        //When
        branchesUseCase.buildUseCaseObservable(Params("owner", "project", 2, 50)).test()
        //Test
        verify(repository).getBranches("owner", "project", 2, 50)
    }

    @Test
    fun should_search_return_data() {
        //Given
        given(repository.getBranches("owner", "project", 2, 50))
                .willReturn(Observable.just(projectData.branchList))
        val expected = projectData.branchList
        //When
        branchesUseCase.buildUseCaseObservable(Params("owner", "project", 2, 50)).test()
                //Test
                .assertValue(expected)
    }
}