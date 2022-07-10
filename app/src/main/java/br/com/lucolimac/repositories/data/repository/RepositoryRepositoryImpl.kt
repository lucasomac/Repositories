package br.com.lucolimac.repositories.data.repository

import br.com.lucolimac.repositories.data.model.Repository
import br.com.lucolimac.repositories.data.service.GithubService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryRepositoryImpl(private val githubService: GithubService) : RepositoryRepository {
    override suspend fun listRepositories(user: String): Flow<List<Repository>> {
        return flow {
            emit(githubService.listRepos(user))
        }
    }
}