package com.charlezz.rxjavasample;

import org.junit.Test;

import io.reactivex.rxjava3.core.Observable;

public class MyTest {
    @Test
    public void testCreate() {
        Observable<String> source = Observable.create(emitter -> {
            emitter.onNext("Hello");
            emitter.onNext("Kook");
            emitter.onComplete();
        });
        source.subscribe(System.out::println);
    }

    @Test
    public void testCreate2() {
        Observable<String> source = Observable.create(emitter -> {
            emitter.onNext("hello2");
            emitter.onComplete();
            emitter.onNext("John");
        });
        source.subscribe(System.out::println);
    }

    @Test
    public void testCreateWithError() {
        Observable<String> source = Observable.create(emitter -> {
            emitter.onNext("hello3");
            emitter.onError(new Throwable());
            emitter.onNext("John");
        });
        source.subscribe(
                System.out::println,
                throwable -> System.out.println("Error!!!!"));
    }

    @Test
    public void textJust() {
        Observable<String> source = Observable.just("Hi", "Kook");
        source.subscribe(System.out::println);
    }

    // ..... 여기까지 테스트 했음 ......

    @Test
    public void testFromArray() {
        String[] itemArray = new String[]{"A", "B", "C"};
        Observable source = Observable.fromArray(itemArray);
        source.subscribe(System.out::println);
    }
    

}
