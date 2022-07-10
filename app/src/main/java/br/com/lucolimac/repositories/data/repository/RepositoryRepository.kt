package br.com.lucolimac.repositories.data.repository

import br.com.lucolimac.repositories.data.model.Repository
import kotlinx.coroutines.flow.Flow

interface RepositoryRepository {
    suspend fun listRepositories(user: String): Flow<List<Repository>>
}