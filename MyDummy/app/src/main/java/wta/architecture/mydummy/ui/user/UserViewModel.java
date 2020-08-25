package wta.architecture.mydummy.ui.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;
import wta.architecture.mydummy.data.UserService;
import wta.architecture.mydummy.data.entity.User;
import wta.architecture.mydummy.util.SingleLiveEvent;

/**
 * Created by jongkook on 2020.08.25
 */
public class UserViewModel extends AndroidViewModel {
    @NonNull
    private final UserService userService;
    @NonNull
    private final SingleLiveEvent<Throwable> errorEvent;

    private final MutableLiveData<User> liveItem = new MutableLiveData<>();
    // todo : 이 형식으로 사용해도 되나
//    private final LiveData<String> name = Transformations.map(liveItem, input -> input.getEmail());
//    private final LiveData<String> name = Transformations.map(liveItem, User::getName); // Lambda can be replaced with method reference
//    private final LiveData<String> email = Transformations.map(liveItem, User::getEmail);
//    private final LiveData<String> homepage = Transformations.map(liveItem, User::getWebsite);
//    private final LiveData<String> phone = Transformations.map(liveItem, User::getPhone);

    private final LiveData<String> name = Transformations.map(liveItem, input -> input.getName());
    private final LiveData<String> email = Transformations.map(liveItem, input -> input.getEmail());
    private final LiveData<String> homepage = Transformations.map(liveItem, input -> input.getWebsite());
    private final LiveData<String> phone = Transformations.map(liveItem, input -> input.getPhone());
    private final LiveData<String> location = Transformations.map(liveItem, input -> input.getAddress().toString());
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(true);

    /*
        @Inject를 안해줄 시...
        [Dagger/MissingBinding] wta.architecture.mydummy.ui.user.UserViewModel cannot be provided without an @Inject constructor or an @Provides-annotated method.
          AppModule --> provideApp
          RetrofitModule --> provideUserService
          AppModule --> provideErrorEvent
     */
    @Inject
    public UserViewModel(@NonNull Application application,
                         @NonNull UserService userService,
                         @Named("errorEvent") SingleLiveEvent<Throwable> errorEvent) {
        super(application);
        Timber.d("* * * UserViewModel created");
        this.userService = userService;
        this.errorEvent = errorEvent;
    }

    public void loadUser(long userId) {
        compositeDisposable.add(userService.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(item -> loading.postValue(false))
                .subscribe(liveItem::setValue, errorEvent::setValue)
        );
    }

    @NonNull
    public UserService getUserService() {
        return userService;
    }

    @NonNull
    public SingleLiveEvent<Throwable> getErrorEvent() {
        return errorEvent;
    }

    public MutableLiveData<User> getLiveItem() {
        return liveItem;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    public LiveData<String> getName() {
        return name;
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public LiveData<String> getHomepage() {
        return homepage;
    }

    public LiveData<String> getPhone() {
        return phone;
    }

    public LiveData<String> getLocation() {
        return location;
    }

}
