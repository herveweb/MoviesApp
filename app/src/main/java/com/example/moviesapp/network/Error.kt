
import retrofit2.HttpException
import java.io.IOException

sealed class Error(open val throwable: Throwable) {
    data class ApiError(override val throwable: Throwable) : Error(throwable)
    data class NetworkError(override val throwable: Throwable) : Error(throwable)
    data class GenericError(override val throwable: Throwable) : Error(throwable)

    companion object {
        fun fromThrowable(throwable: Throwable): Error {
            if (throwable is HttpException) {
                return ApiError(throwable)
            }
            if (throwable is IOException) {
                return NetworkError(throwable)
            }
            return GenericError(throwable)
        }
    }
}
