package br.com.lucolimac.repositories.domain

import br.com.lucolimac.repositories.core.UseCase
import br.com.lucolimac.repositories.data.model.Repository
import br.com.lucolimac.repositories.data.repository.RepositoryRepository
import kotlinx.coroutines.flow.Flow

class ListUserRepositoriesUseCase(private val repositoryRepository: RepositoryRepository) :
    UseCase<String, List<Repository>>() {
    override suspend fun execute(param: String): Flow<List<Repository>> {
        return repositoryRepository.listRepositories(param)
    }
}