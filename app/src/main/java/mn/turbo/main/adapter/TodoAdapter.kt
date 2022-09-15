package mn.turbo.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mn.turbo.data.remote.dto.Todo
import mn.turbo.databinding.ItemTodoBinding

class TodoAdapter : ListAdapter<Todo, TodoViewHolder>(TodoDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.binding.apply {
                mTextView.text = item.title
                mCheckBox.isChecked = !item.completed
            }
        }
    }
}

class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

class TodoDiffUtil : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean =
        oldItem == newItem
}