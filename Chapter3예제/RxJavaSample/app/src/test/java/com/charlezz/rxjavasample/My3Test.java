package com.charlezz.rxjavasample;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class My3Test {
    // 오류를 다루는 연산자
    @Test
    public void testNumberFormatException() {
        Observable.just("111", "2222", "aa", "444")
                .map(i -> Integer.parseInt(i))
                .subscribe(System.out::println);
    }

    @Test
    public void testErrorHandling() {
        Observable.just("111", "2222", "aa", "444")
                .map(i -> Integer.parseInt(i))
                .subscribe(System.out::println,
                        throwable -> System.out.println("Error!!!")
                );
    }

    @Test
    public void testOnErrorReturn() {
        Observable.just("111", "2222", "aa", "444")
                .map(i -> Integer.parseInt(i))
                .onErrorReturn(throwable -> -1)
                .subscribe(System.out::println);
    }

    @Test
    public void testOnErrorResumeNext() {
        Observable.just("111", "2222", "aa", "444")
                .map(i -> Integer.parseInt(i))
                .onErrorResumeNext(throwable -> Observable.just(11, 22, 33))
                .subscribe(System.out::println);
    }

    @Test
    public void testRetry() {
//        Observable.just("111", "2222", "aa", "444")
//                .map(i -> Integer.parseInt(i))
//                .retry()
//                .subscribe(System.out::println);

        Observable.just("11111", "2222", "aa", "444")
                .map(i -> Integer.parseInt(i))
                .retry(2)
                .subscribe(System.out::println);
    }

    @Test
    public void testDoOnEach() {
        Observable.just(1, 3, 5)
                .doOnEach(notification -> {
                    Integer i = notification.getValue();
                    boolean isOnNext = notification.isOnNext();
                    boolean isOnComplete = notification.isOnComplete();
                    boolean isOnError = notification.isOnError();
                    Throwable throwable = notification.getError();
                    System.out.println(">>> i            : " + i);
                    System.out.println(">>> isOnNext     : " + isOnNext);
                    System.out.println(">>> isOnComplete : " + isOnComplete);
                    System.out.println(">>> isOnError    : " + isOnError);
                    if (throwable != null) {
                        throwable.printStackTrace();
                    }
                }).subscribe(value -> System.out.println(">>>>> Subscribed :: " + value));
    }

    @Test
    public void testDoOnNext() {
        // testDoOnEach에서 나오는 notification 대신에 간단히 발행된 item으로.
        Observable.just(1, 3, 5)
                .doOnNext(item -> {
                    if (item > 1) {
                        throw new IllegalArgumentException();
                    }
                }).subscribe(System.out::println, throwable -> throwable.printStackTrace());
    }

    @Test
    public void testDoOnSubscribe() {
        Observable.just(1, 3, 5)
                .doOnSubscribe(disposable -> System.out.println("구독 시작!!!"))
                .subscribe(System.out::println);
    }

    @Test
    public void testDoOnComplete() {
        Observable.just(1, 3, 5)
                .doOnComplete(() -> System.out.println("완료!!!"))
                .subscribe(System.out::println);
    }

    @Test
    public void testDoOnError() {
        Observable.just(11, 22, 0)
                .map(i -> 10 / i)
                .doOnError(throwable -> System.out.println("오류!!"))
                .subscribe(System.out::println, t -> t.printStackTrace());
    }

    @Test
    public void testDoOnTerminate() {
        Observable.just(11, 22, 0)
                .map(i -> 10 / i)
                .doOnComplete(() -> System.out.println("doOnComplete"))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .subscribe(System.out::println, t -> t.printStackTrace());
    }

    @Test
    public void testDoOnDispose() throws InterruptedException {
        Observable src = Observable.interval(500, TimeUnit.MILLISECONDS)
                .doOnDispose(() -> System.out.println("doOnDispose"));
        Disposable disposable = src.subscribe(System.out::println);
        Thread.sleep(1100);
        disposable.dispose();
    }

    @Test
    public void testDoFinally() throws InterruptedException {
        Observable src = Observable.interval(500, TimeUnit.MILLISECONDS)
                .doFinally(() -> System.out.println("doFinally"));
        Disposable disposable = src.subscribe(System.out::println);
        Thread.sleep(1100);
        disposable.dispose();
    }

    @Test
    public void testSubscribeOnAndObserveOn() {
        Observable<Integer> src = Observable.create(emitter -> {
            for (int i = 0; i < 3; i++) {
                String threadName = Thread.currentThread().getName();
                System.out.println("#Subs on " + threadName + " : " + i);
                emitter.onNext(i);
                Thread.sleep(100);
            }
            emitter.onComplete();
        });
        src.subscribe(s -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("#Obsv on " + threadName + " : " + s);
        });
    }

    @Test
    public void testSubscribeOnAndObserveOn2() throws InterruptedException {
        Observable<Integer> src = Observable.create(emitter -> {
            for (int i = 0; i < 3; i++) {
                String threadName = Thread.currentThread().getName();
                System.out.println("#Subs on " + threadName + " : " + i);
                emitter.onNext(i);
                Thread.sleep(100);
            }
            emitter.onComplete();
        });
        src.subscribeOn(Schedulers.io())
                .subscribe(s -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("#Obsv on " + threadName + " : " + s);
                });
        Thread.sleep(500);
    }

    @Test
    public void testSubscribeOnAndObserveOn3() throws InterruptedException {
        Observable<Integer> src = Observable.create(emitter -> {
            for (int i = 0; i < 3; i++) {
                String threadName = Thread.currentThread().getName();
                System.out.println("#Subs on " + threadName + " : " + i);
                emitter.onNext(i);
                Thread.sleep(100);
            }
            emitter.onComplete();
        });

        src.observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .subscribe(s -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("#Obsv on " + threadName + " : " + s);
                });
        Thread.sleep(500);
    }

    @Test
    public void testSubscribeOnAndObserveOn4() throws InterruptedException {
        Observable.interval(200, TimeUnit.MILLISECONDS)
                // Schedulers.io를 선택해도 RxComputationThreadPool로 잡힌다.
                .subscribeOn(Schedulers.io())
                .subscribe(value -> System.out.println(Thread.currentThread().getName() + ":" + value));
        Thread.sleep(1000);
    }

/*
    @Test
    public void testBackPressure() throws InterruptedException {
        Observable.range(1, Integer.MAX_VALUE)
                .map(item -> {
                    System.out.println("아이템 발행 : " + item);
                    return item;
                })
                .subscribe(item -> {
                    Thread.sleep(100);
                    System.out.println("아이템 소비 : " + item);
                });
        Thread.sleep(5 * 1000);
    }

    @Test
    public void testBackPressure2() throws InterruptedException {
        // 배압인 상태
        Observable.range(1, Integer.MAX_VALUE)
                .map(item -> {
                    System.out.println("아이템 발행 : " + item);
                    return item;
                })
                .observeOn(Schedulers.io())
                .subscribe(item -> {
                    Thread.sleep(100);
                    System.out.println("아이템 소비 : " + item);
                });
        Thread.sleep(5 * 1000);
    }
*/

    @Test
    public void testFlowable() throws InterruptedException {
        /**
         * <소비가 발행보다 적은 이유>
         * 다시 생산자가 발행하기까지 걸리는 시간으로 인해
         * 소비자가 기다리는 일이 없도록 여유를 두기 위함
         */
        Flowable.range(1, Integer.MAX_VALUE)
                .map(item -> {
                    System.out.println("아이템 발행 : " + item);
                    return item;
                })
                .observeOn(Schedulers.io())
                .subscribe(item -> {
                    Thread.sleep(100);
                    System.out.println("아이템 소비 : " + item);
                });
        Thread.sleep(2 * 1000);
    }

    @Test
    public void testFlowable2() throws InterruptedException {
        // MissingBackpressureException 발생
        // interval과 같은 연산자들은 스케줄러의 설정과 관계없이 시간을 기반으로 충실히 아이템을 발행
        // 생산하는 쪽에서 블로킹 이슈가 발생하면 배압 제어 전략과 관계없이 MissingBackpressureException이 발생
        Flowable.interval(10, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .map(item -> {
                    Thread.sleep(500);
                    System.out.println("아이템 발행 : " + item);
                    return item;
                })
                .subscribe(item -> {
                    System.out.println("아이템 소비 : " + item);
                }, throwable -> throwable.printStackTrace());
        Thread.sleep(3 * 1000);
    }

    // 배압 제어 연산자

    @Test
    public void testOnBackpressureBuffer() throws InterruptedException {
        Flowable.interval(10, TimeUnit.MILLISECONDS)
                .onBackpressureBuffer()
                .observeOn(Schedulers.io())
                .map(item -> {
                    Thread.sleep(500);
                    System.out.println("아이템 발행 : " + item);
                    return item;
                })
                .subscribe(item -> {
                    System.out.println("아이템 소비 : " + item);
                }, throwable -> throwable.printStackTrace());
        Thread.sleep(5 * 1000);
    }

    @Test
    public void testOnBackpressureLatest() throws InterruptedException {
        /**
         * 아이템 소비 : 126
         * 아이템 소비 : 127
         * 아이템 소비 : 224
         * 아이템 소비 : 225
         * 아이템 소비 : 226
         *
         * 스트림 버퍼가 가득차면 최신의 아이템을 버퍼에 유지하려고 오래된 아이템을 버리는 연산자
         */
        Flowable.interval(10, TimeUnit.MILLISECONDS)
                .onBackpressureLatest()
                .observeOn(Schedulers.io())
                .subscribe(item -> {
                    Thread.sleep(20);
                    System.out.println("아이템 소비 : " + item);
                }, throwable -> throwable.printStackTrace());
        Thread.sleep(5 * 1000);
    }

    @Test
    public void testOnBackpressureDrop() throws InterruptedException {
        /**
         * 128번째 아이템 이후로는 메시지 출력 안됨
         * 구독자가 아이템을 소비하는 동안 129부터의 모든 아이템이 전부 버려졌기 때문
         */
        Flowable.range(1, Integer.MAX_VALUE)
                .onBackpressureDrop()
                .observeOn(Schedulers.io())
                .subscribe(item -> {
                    Thread.sleep(10);
                    System.out.println("아이템 소비 : " + item);
                }, throwable -> throwable.printStackTrace());
        Thread.sleep(5 * 1000);
    }

    @Test
    public void testOnBackpressureDrop2() throws InterruptedException {
        Flowable.interval(10, TimeUnit.MILLISECONDS)
                .onBackpressureDrop(item ->
                        System.out.println(">> 아이템 버림 : " + item))
                .observeOn(Schedulers.io())
                .subscribe(item -> {
                    Thread.sleep(20);
                    System.out.println("아이템 소비 : " + item);
                }, throwable -> throwable.printStackTrace());
        Thread.sleep(5 * 1000);
    }

    @Test
    public void testBackpressureStrategy() throws InterruptedException {
        Flowable.create((FlowableOnSubscribe<Integer>) emitter -> {
            for (int i = 0; i <= 100; i++) {
                if (emitter.isCancelled()) { // 다운스트림 취소 및 폐기 시 true
                    return;
                }
                emitter.onNext(i);
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER) // 배압 제어 전략
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .subscribe(System.out::println, throwable -> throwable.printStackTrace());
        Thread.sleep(100);
    }
}
