package wta.architecture.mydummy.data;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wta.architecture.mydummy.data.entity.Comment;

public interface CommentService {
    @GET("/comments")
    Single<List<Comment>> getComments(@Query("postId") long id);
}
