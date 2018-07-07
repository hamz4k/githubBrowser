package com.hklouch.data.network.pull

import com.hklouch.data.network.GithubReposService
import com.hklouch.data.network.util.Data
import com.hklouch.domain.pull.interactor.PullsUseCase.Params.State.OPEN
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

class NetworkPullsRepositoryTest {

    private lateinit var projectData: Data

    @Mock private lateinit var githubReposService: GithubReposService

    private lateinit var repository: NetworkPullsRepository

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        projectData = Data()
        repository = NetworkPullsRepository(githubReposService)
    }

    @Test
    fun should_get_pulls_return_data() {
        //Given
        val response = Response.success(projectData.json.pullList)
        val result = Result.response(response)
        given(githubReposService.getPulls("owner", "project", page = 1, resultsPerPage = 50, state = "open"))
                .willReturn(Observable.just(result))
        val expected = PagingWrapper(nextPage = null,
                                     lastPage = null,
                                     items = projectData.domain.pullList)
        //When
        repository.getPulls("owner", "project", page = 1, resultsPerPage = 50, state = OPEN).test()
                //Then
                .assertValue(expected)
    }

    @Test
    fun should_get_pulls_complete() {
        //Given
        val response = Response.success(projectData.json.pullList)
        val result = Result.response(response)
        given(githubReposService.getPulls("owner", "project", page = 1, resultsPerPage = 50, state = "open"))
                .willReturn(Observable.just(result))
        //When
        repository.getPulls("owner", "project", page = 1, resultsPerPage = 50, state = OPEN).test()
                //Then
                .assertComplete()
    }
}