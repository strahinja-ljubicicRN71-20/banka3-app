package utils

/**
 * Interface for mapping instance of T to instance of R and vice-versa.
 */
interface IMapper<T, R> {
    fun map(value: T): R
    fun map(value: List<T>): List<R> = value.map { map(it) }
}