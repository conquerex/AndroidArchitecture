package wta.architecture.mydummy.ui.user;

import android.content.Context;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import wta.architecture.mydummy.databinding.FragmentUserBinding;
import wta.architecture.mydummy.di.ActivityContext;
import wta.architecture.mydummy.di.FragmentScope;

/**
 * Created by jongkook on 2020.08.25
 * .
 * (p480)
 * UserModule에서는 UserFragment를 위한 바인딩 클래스를 제공한다.
 * UserModule을 서브 컴포넌트로 정의하기 위해 MainModule에 코드를 추가하자.
 */
@Module
public class UserModule {
    @Provides
    @FragmentScope
    FragmentUserBinding provideBinding(@ActivityContext Context context) {
        return FragmentUserBinding.inflate(LayoutInflater.from(context));
    }
}
