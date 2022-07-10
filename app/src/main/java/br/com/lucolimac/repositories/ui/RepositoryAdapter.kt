package br.com.lucolimac.repositories.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.lucolimac.repositories.data.model.Repository
import br.com.lucolimac.repositories.databinding.ItemRepositoryBinding
import com.bumptech.glide.Glide

class RepositoryAdapter : ListAdapter<Repository, RepositoryAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepositoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemRepositoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Repository) {
            binding.tvRepoName.text = item.name
            binding.tvRepoDescription.text = item.description
            binding.tvRepoLanguage.text = item.language
            binding.chipStar.text = item.stargazers_count.toString()

            Glide.with(binding.root.context)
                .load(item.owner.avatar_url).into(binding.ivOwner)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Repository, newItem: Repository) =
        oldItem.id == newItem.id
}