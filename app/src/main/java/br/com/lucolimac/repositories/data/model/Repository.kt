package br.com.lucolimac.repositories.data.model

data class Repository(
    val description: String,
    val html_url: String,
    val id: Int,
    val language: String,
    val name: String,
    val owner: Owner,
    val stargazers_count: Int
)