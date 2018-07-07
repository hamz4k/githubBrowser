package com.hklouch.di

import com.hklouch.data.network.GithubReposService
import com.hklouch.data.network.GithubReposServiceFactory
import com.hklouch.data.network.branch.NetworkBranchesRepository
import com.hklouch.data.network.contributor.NetworkContributorsRepository
import com.hklouch.data.network.issue.NetworkIssuesRepository
import com.hklouch.data.network.project.browse.NetworkBrowseRepository
import com.hklouch.data.network.project.detail.NetworkProjectRepository
import com.hklouch.data.network.project.search.NetworkSearchRepository
import com.hklouch.data.network.pull.NetworkPullsRepository
import com.hklouch.domain.branch.repository.BranchesRepository
import com.hklouch.domain.contributor.repository.ContributorsRepository
import com.hklouch.domain.issue.repository.IssuesRepository
import com.hklouch.domain.project.browse.repository.BrowseRepository
import com.hklouch.domain.project.detail.repository.ProjectRepository
import com.hklouch.domain.project.search.repository.SearchRepository
import com.hklouch.domain.pull.repository.PullsRepository
import com.hklouch.githubrepos4cs.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubReposService {
            return GithubReposServiceFactory.makeGithubReposService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindNetworkProjectsRepository(repository: NetworkBrowseRepository): BrowseRepository

    @Binds
    abstract fun bindNetworkProjectRepository(repository: NetworkProjectRepository): ProjectRepository

    @Binds
    abstract fun bindNetworkSearchRepository(repository: NetworkSearchRepository): SearchRepository

    @Binds
    abstract fun bindNetworkBranchesRepository(repository: NetworkBranchesRepository): BranchesRepository

    @Binds
    abstract fun bindNetworkPullssRepository(repository: NetworkPullsRepository): PullsRepository

    @Binds
    abstract fun bindNetworkIssuesRepository(repository: NetworkIssuesRepository): IssuesRepository

    @Binds
    abstract fun bindNetworkContributorsRepository(repository: NetworkContributorsRepository): ContributorsRepository


}