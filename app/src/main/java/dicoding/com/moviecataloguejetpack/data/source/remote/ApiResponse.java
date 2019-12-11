package dicoding.com.moviecataloguejetpack.data.source.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ApiResponse<T> {

    @NonNull
    public final StatusResponse status;

    @Nullable
    public final String message;

    @Nullable
    public final T body;

    public ApiResponse(@NonNull StatusResponse status, @Nullable T body, @Nullable String message){
        this.status = status;
        this.body = body;
        this.message = message;
    }

    public static <T> ApiResponse<T> success(@Nullable T body){
        return new ApiResponse<>(StatusResponse.SUCCESS, body, null);
    }

    public static <T> ApiResponse<T> empty(@Nullable T body){
        return new ApiResponse<>(StatusResponse.EMPTY, body, null);
    }
}
