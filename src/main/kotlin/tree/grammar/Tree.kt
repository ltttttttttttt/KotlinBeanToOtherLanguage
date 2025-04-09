package tree.grammar

sealed class Tree {
	final override fun toString() = toString(0)
	abstract fun toString(depth: Int): String
}

class TreeString(private val value: String) : Tree() {
	override fun toString(depth: Int) = value
}

class TreeCharacter(private val value: Char) : Tree() {
	override fun toString(depth: Int) = value.toString()
}

class TreeNumber(private val value: Number) : Tree() {
	override fun toString(depth: Int) = value.toString()
}

class TreeBoolean(private val value: Boolean) : Tree() {
	override fun toString(depth: Int) = value.toString()
}

class TreeList<T : TreeNode>(private val list: List<T>) : Tree(), List<T> by list {
	override fun toString(depth: Int) = buildString {
		append('[')
		if (list.isNotEmpty()) {
			append(list.joinToString(",\n", "\n", "\n") {
				buildString {
					repeat(depth + 1) {
						append("  ")
					}
					append(it.toString(depth + 1))
				}
			})
			repeat(depth) {
				append("  ")
			}
		}
		append(']')
	}
}

sealed class TreeNode(
	protected val name: String,
	protected vararg val properties: Pair<String, Tree?>
) : Tree() {
	final override fun toString(depth: Int) = buildString {
		append(name)
		append('(')
		val list = properties.filter { (_, value) ->
			value != null && !(value is TreeList<*> && value.isEmpty())
		}.map { it.first to it.second!! }
		if (list.isNotEmpty()) {
			append(list.joinToString(",\n", "\n", "\n") { (key, value) ->
				buildString {
					repeat(depth + 1) {
						append("  ")
					}
					append("$key: ${value.toString(depth + 1)}")
				}
			})
			repeat(depth) {
				append("  ")
			}
		}
		append(')')
	}
}