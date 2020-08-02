package com.charlezz.rxjavasample;

import org.junit.Test;
import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

    // 간단히 Observable로 변환하기.............

    @Test
    public void testFromArray() {
        String[] itemArray = new String[]{"A", "B", "C..............."};
        Observable source = Observable.fromArray(itemArray);
        source.subscribe(System.out::println);
    }

    @Test
    public void testFromIterable() {
        ArrayList itemList = new ArrayList();
        itemList.add("aa");
        itemList.add("bbb");
        itemList.add("cccc");
        Observable source = Observable.fromIterable(itemList);
        source.subscribe(System.out::println);
    }

    @Test
    public void testFromFuture() {
        Future<String> future = Executors.newSingleThreadExecutor()
                .submit(() -> {
                    Thread.sleep(5000);
                    return "Hello kooook";
                });
        Observable source = Observable.fromFuture(future);
        source.subscribe(System.out::println);
    }

    @Test
    public void testFromPublisher() {
        Publisher<String> publisher = subscriber -> {
            subscriber.onNext("1111");
            subscriber.onNext("2222");
            subscriber.onNext("3333");
        };
        Observable source = Observable.fromPublisher(publisher);
        source.subscribe(System.out::println);
    }

    @Test
    public void testFromCallable() {
        Callable<String> callable = () -> "Hello World";
        Observable source = Observable.fromCallable(callable);
        source.subscribe(System.out::println);
    }

    //.......

}
