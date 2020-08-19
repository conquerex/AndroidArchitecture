package wta.architecture.mydummy.data;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import wta.architecture.mydummy.data.entity.Post;

public interface PostService {
    @GET("/posts")
    Single<List<Post>> getPosts();
}
