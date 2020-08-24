package wta.architecture.mydummy.ui.post;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;
import wta.architecture.mydummy.data.PostService;
import wta.architecture.mydummy.util.SingleLiveEvent;

/**
 * Created by jongkook on 2020.08.20
 */
public class PostViewModel extends AndroidViewModel implements PostItem.EventListener {

    @NonNull
    private final PostService postService;
    @NonNull
    private final SingleLiveEvent<Throwable> errorEvent;

    // RecyclerView에 표현할 아이템들을 LiveData로 관리
    private final MutableLiveData<List<PostItem>> livePosts = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(true);
    private final SingleLiveEvent<PostItem> postClickEvent = new SingleLiveEvent<>();

    /*
        errorEvent 매개변수의 @Named("errorEvent")가 빠졌을 때...
        error: [Dagger/MissingBinding] wta.architecture.mydummy.util.SingleLiveEvent<java.lang.Throwable> cannot be provided without an @Inject constructor or an @Provides-annotated method.
     */
    @Inject
    public PostViewModel(@NonNull Application application,
                         @NonNull PostService postService,
                         @Named("errorEvent") SingleLiveEvent<Throwable> errorEvent) {
        super(application);
        this.postService = postService;
        this.errorEvent = errorEvent;
    }

    /**
     * 게시 글 목록 불러오기
     * .
     * (p449)
     * PostFragment에서 PostViewModel의 loadPosts() 메서드를 호출 하면
     * Retrofit 객체를 통해 서버로부터 글 목록 데이터를 불러온다.
     */
    public void loadPosts() {
        compositeDisposable.add(postService.getPosts()
                .flatMapObservable(Observable::fromIterable)
                .map(post -> new PostItem(post, this))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(item -> loading.postValue(false))
                .subscribe(livePosts::setValue, errorEvent::setValue));
    }

    @NonNull
    public MutableLiveData<List<PostItem>> getLivePosts() {
        return livePosts;
    }

    /**
     * ViewModel은 생명주기를 알고 동작한다.
     * 뷰 모델이 파괴될 때, RxJava의 Disposable과 같은 리소스 등을 해제할 수 있도록 한다.
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        Timber.d("* * * onCleared");
        compositeDisposable.dispose();
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    // PostItem 클릭 이벤트 구현
    @Override
    public void onPostClick(PostItem postItem) {
        // Fragment로 이벤트를 전달 하도록 SingleLiveEvent의 값을 변경한다.
        postClickEvent.setValue(postItem);
    }

    // PostFragment로 postClickEvent 변수를 노출
    public SingleLiveEvent<PostItem> getPostClickEvent() {
        return postClickEvent;
    }
}
