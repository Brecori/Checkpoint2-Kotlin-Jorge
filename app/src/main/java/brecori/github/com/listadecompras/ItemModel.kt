package brecori.github.com.listadecompras

data class ItemModel(
    val name: String,
    val value: Double,
    val onRemove: (ItemModel) -> Unit
)