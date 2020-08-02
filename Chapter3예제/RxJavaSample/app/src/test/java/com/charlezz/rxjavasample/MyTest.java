package com.charlezz.rxjavasample;

import org.junit.Test;
import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

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

    // 다양한 Observable의 형태.........

    @Test
    public void testSingle() {
        Single.just("Single ------- Hello").subscribe(System.out::println);
    }

    @Test
    public void testSingle2() {
        Single.create(emitter -> emitter.onSuccess("success"))
                .subscribe(System.out::println);
    }

    @Test
    public void testTransforming() {
        Observable<Integer> src = Observable.just(2, 3, 4);
        Single<Boolean> singleSrc1 = src.all(i -> i > 0);
        Single<Integer> singleSrc2 = src.first(-1);
        Single<List<Integer>> singleSrc3 = src.toList();
        Single<String> singleSrc = Single.just("Single transforming");
        Observable<String> observableSrc = singleSrc.toObservable();
    }

    @Test
    public void testMaybe() {
        Maybe.create(emitter -> {
            emitter.onSuccess(200);
            emitter.onComplete();
        })
                .doOnSuccess(item -> System.out.println("doOnSuccess1"))
                .doOnComplete(() -> System.out.println("doOnComplete1"))
                .subscribe(System.out::println);

        Maybe.create(emitter -> emitter.onComplete())
                .doOnSuccess(item -> System.out.println("doOnSuccess2"))
                .doOnComplete(() -> System.out.println("doOnComplete2"))
                .subscribe(System.out::println);
    }

    @Test
    public void testObservableToMaybe() {
        Observable<Integer> src1 = Observable.just(4, 5, 6);
        Maybe<Integer> srcMaybe1 = src1.firstElement();
        srcMaybe1.subscribe(System.out::println);

        Observable<Integer> src2 = Observable.empty();
        Maybe<Integer> srcMaybe2 = src2.firstElement();
        srcMaybe2.subscribe(
                System.out::println,
                throwable -> {
                },
                () -> System.out.println("onComplete!!!"));
    }

    @Test
    public void testComplete() {
        Completable.create(emitter -> {
            //do something here
            emitter.onComplete();
        }).subscribe(() -> System.out.println("onComplete....1"));

        Completable.fromRunnable(() -> {
            //do something here
        }).subscribe(() -> System.out.println("onComplete....2"));
    }

    @Test
    public void testColdAndHot() throws InterruptedException {
        Observable src = Observable.interval(1, TimeUnit.SECONDS);
        src.subscribe(value -> System.out.println(">>> 1 : " + value));
        Thread.sleep(2000);
        src.subscribe(value -> System.out.println(">>> 2 : " + value));
        Thread.sleep(2000);
    }

    @Test
    public void testPublishAndConnect() throws InterruptedException {
        ConnectableObservable src = Observable.interval(1, TimeUnit.SECONDS).publish();
        src.connect();
        src.subscribe(value -> System.out.println(">>> 3 : " + value));
        Thread.sleep(2000);
        src.subscribe(value -> System.out.println(">>> 4 : " + value));
        Thread.sleep(2000);
    }

    @Test
    public void testAutoConnect() throws InterruptedException {
        Observable<Long> src = Observable.interval(100, TimeUnit.MILLISECONDS)
                .publish()
                .autoConnect(2);
        src.subscribe(i -> System.out.println("A :::: " + i));
        src.subscribe(i -> System.out.println("B :::: " + i));
        Thread.sleep(500);
    }

    @Test
    public void testDisposable() {
        Observable source = Observable.just("a", "c", "e");
        Disposable disposable = source.subscribe(o ->
                System.out.println(source));
    }

    @Test
    public void testDisposable2() throws InterruptedException {
        // 1초에 한 번씩 아이템을 발행
        Observable source = Observable.interval(1000, TimeUnit.MILLISECONDS);
        Disposable disposable = source.subscribe(System.out::println);
        new Thread(() -> {
            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            disposable.dispose();
        }).start();

        Thread.sleep(4000);
    }

    @Test
    public void testCompositeDisposable() {
        Observable source = Observable.interval(1000, TimeUnit.MILLISECONDS);
        Disposable d1 = source.subscribe(System.out::println);
        Disposable d2 = source.subscribe(System.out::println);
        Disposable d3 = source.subscribe(System.out::println);
        CompositeDisposable cd = new CompositeDisposable();
        cd.add(d1);
        cd.add(d2);
        cd.add(d3);
        // or
        cd.addAll(d1, d2, d3);
        // 특정 시점에 폐기하기
        cd.dispose();
    }

}
