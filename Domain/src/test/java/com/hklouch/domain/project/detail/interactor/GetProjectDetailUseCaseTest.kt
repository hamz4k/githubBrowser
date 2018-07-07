package com.hklouch.domain.project.detail.interactor

import com.hklouch.domain.Data
import com.hklouch.domain.project.detail.interactor.GetProjectDetailUseCase.Params
import com.hklouch.domain.project.detail.repository.ProjectRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GetProjectDetailUseCaseTest {

    @Mock private lateinit var repository: ProjectRepository

    private lateinit var getProjectDetailUseCase: GetProjectDetailUseCase

    private val projectData = Data()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        getProjectDetailUseCase = GetProjectDetailUseCase(repository)
    }

    @Test
    fun should_get_project_detail_complete() {
        //Given
        given(repository.getProject("owner", "project"))
                .willReturn(Single.just(projectData.project))
        //When
        getProjectDetailUseCase.buildUseCaseObservable(Params("owner", "project")).test()
                //Test
                .assertComplete()
    }

    @Test
    fun should_get_project_detail_call_repository() {
        //Given
        given(repository.getProject("owner", "project"))
                .willReturn(Single.just(projectData.project))
        //When
        getProjectDetailUseCase.buildUseCaseObservable(Params("owner", "project")).test()
        //Test
        verify(repository).getProject("owner", "project")
    }


    @Test
    fun should_get_project_detail_return_data() {
        //Given
        given(repository.getProject("owner", "project"))
                .willReturn(Single.just(projectData.project))
        val expected = projectData.project
        //When
        getProjectDetailUseCase.buildUseCaseObservable(Params("owner", "project")).test()
                //Test
                .assertValue(expected)
    }

}