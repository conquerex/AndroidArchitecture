package wta.architecture.chapter2.common;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;

/**
 * @BindsOptionalOf
 * 만약 컴포넌트 내에 Foo가 바인드된 적이 있다면 Optional의 상태는 present(참석)이고
 * 그렇지 않다면 absent(결석)
 * 어떤 타입의 의존성이 바인드되었는지 여부와 관계없이 @Inject를 이용해 주입할 수 있는 것이 특징
 */
public class Foo {
    @Inject
    public Optional<String> str; // @Nullable 바인딩은 허용하지 않음

    @Inject
    public Optional<Provider<String>> str2;

    @Inject
    public Optional<Lazy<String>> str3;
}
