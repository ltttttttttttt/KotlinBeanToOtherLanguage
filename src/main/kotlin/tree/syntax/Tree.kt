package tree.syntax

import kotlin.toString

sealed class AST(
	protected val name: String,
	protected vararg val properties: Pair<String, Any?>
) {
	final override fun toString() = toString(0)
	fun toString(depth: Int): String = buildString {
		append(name)
		append('(')
		if (properties.isNotEmpty()) {
			append(properties.joinToString(",\n", "\n", "\n") { (key, value) ->
				buildString {
					repeat(depth + 1) {
						append("  ")
					}
					append("$key: ")
					append(
						when (value) {
							is AST -> value.toString(depth + 1)
							is List<*> -> buildString {
								append('[')
								if (value.isNotEmpty()) {
									append(value.joinToString(",\n", "\n", "\n") {
										buildString {
											repeat(depth + 1) {
												append("  ")
											}
											append(
												when (it) {
													is AST -> it.toString(depth + 1)
													else -> it.toString()
												}
											)
										}
									})
									repeat(depth) {
										append("  ")
									}
								}
								append(']')
							}
							else -> value.toString()
						}
					)
				}
			})
			repeat(depth) {
				append("  ")
			}
		}
		append(')')
	}
}