
sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Failure<out Nothing>(val errorMessage: String?) : ApiResult<Nothing>()
}
