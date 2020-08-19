package wta.architecture.mydummy.di;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import retrofit2.Retrofit;
import wta.architecture.mydummy.data.CommentService;
import wta.architecture.mydummy.data.PostService;
import wta.architecture.mydummy.data.UserService;

@Module
public class RetrofitModule {
    @Provides
    @Reusable
    PostService providePostService(Retrofit retrofit) {
        return retrofit.create(PostService.class);
    }

    @Provides
    @Reusable
    CommentService provideCommentService(Retrofit retrofit) {
        return retrofit.create(CommentService.class);
    }

    @Provides
    @Reusable
    UserService provideUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }
}
