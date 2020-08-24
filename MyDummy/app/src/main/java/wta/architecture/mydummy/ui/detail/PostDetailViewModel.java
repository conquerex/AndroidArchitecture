package wta.architecture.mydummy.ui.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;
import wta.architecture.mydummy.data.CommentService;
import wta.architecture.mydummy.data.UserService;
import wta.architecture.mydummy.data.entity.Comment;
import wta.architecture.mydummy.data.entity.Post;
import wta.architecture.mydummy.util.SingleLiveEvent;

/**
 * Created by jongkook on 2020.08.25
 * .
 * PostDetailFragment를 위한 뷰 모델도 추가한다.
 * 사용자 정보, 게시 글, 댓글을 모두 표현해야 하므로 해당 데이터를 서버에 요청할 때
 * RxJava의 zip 연산자를 사용하여, 응답을 묶어서 처리하는 것을 확인할 수 있다.
 */
public class PostDetailViewModel extends AndroidViewModel implements PostDetailUserItem.EventListener {

    private final MutableLiveData<List<PostDetailItem>> liveItems = new MutableLiveData<>();
    private final UserService userService;
    private final CommentService commentService;
    @NonNull
    private final SingleLiveEvent<Throwable> errorEvent;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(true);

    private final SingleLiveEvent<Long> userClickEvent = new SingleLiveEvent<>();

    @Inject
    public PostDetailViewModel(@NonNull Application application,
                               UserService userService,
                               CommentService commentService,
                               @Named("errorEvent") SingleLiveEvent<Throwable> errorEvent) {
        super(application);
        Timber.d("* * * * PostDetailViewModel created");
        this.userService = userService;
        this.commentService = commentService;
        this.errorEvent = errorEvent;
    }

    public void load(Post post) {
        compositeDisposable.add(Single.zip(userService.getUser(post.getUserId()),
                Single.just(post),
                commentService.getComments(post.getId()),
                (user, p, comments) -> {
                    List<PostDetailItem> list = new ArrayList<>();
                    list.add(new PostDetailUserItem(user, this));
                    list.add(new PostDetailPostItem(p));
                    for (Comment comment : comments) {
                        list.add(new PostDetailCommentItem(comment));
                    }
                    return list;
                })
                .retry(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(postDetailItems -> loading.postValue(false))
                .subscribe(liveItems::setValue, errorEvent::setValue)
        );
    }

    public MutableLiveData<List<PostDetailItem>> getLiveItems() {
        return liveItems;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Timber.d("* * * * onCleared");
        this.compositeDisposable.dispose();
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public SingleLiveEvent<Long> getUserClickEvent() {
        return userClickEvent;
    }

    @Override
    public void onUserClick(long userId) {
        userClickEvent.setValue(userId);
    }
}
