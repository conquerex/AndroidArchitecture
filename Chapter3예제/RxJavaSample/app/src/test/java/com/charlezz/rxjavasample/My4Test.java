package com.charlezz.rxjavasample;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import io.reactivex.rxjava3.subjects.Subject;
import io.reactivex.rxjava3.subjects.UnicastSubject;

public class My4Test {

    /**
     * <Subject>
     * Observable과 Observer를 모두 구현한 추상 타입으로
     * 하나의 소스로부터 다중의 구독자에게 멀티 캐스팅이 가능
     */
    @Test
    public void testPublishSubject() {
        Subject<String> src = PublishSubject.create();
        src.subscribe(item -> System.out.println("A : " + item),
                t -> t.printStackTrace(),
                () -> System.out.println("A : onComplete"));
        src.subscribe(item -> System.out.println("B : " + item),
                t -> t.printStackTrace(),
                () -> System.out.println("B : onComplete"));
        src.onNext("Hellllo");
        src.onNext("Woooorld");
        src.onNext("!!!!!!!");
        src.onComplete();
    }


    /*
    발행자
    Hot Observable, 아이템을 발행한 뒤 구독하면 아무런 아이템도 소비할 수 없다
     */
    @Test
    public void testPublishSubject2() {
        Subject<String> src = PublishSubject.create();
        src.onNext("Helllloooo");
        src.onNext("Woooorld");
        src.onNext("!!!!!!!");
        src.onComplete();
        src.map(String::length).subscribe(System.out::println);
    }

    /*
    구독자
    Observer일 때
     */
    @Test
    public void testPublishSubject3() throws InterruptedException {
        Observable src1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable src2 = Observable.interval(500, TimeUnit.MILLISECONDS);
        PublishSubject subject = PublishSubject.create();
        src1.map(item -> "A : " + item).subscribe(subject);
        src2.map(item -> "B : " + item).subscribe(subject);
        // 다시 Observable로 발행하여 다른 구독자에게 전달
        subject.subscribe(System.out::println);
        Thread.sleep(2500);
    }

    @Test
    public void testSerializedSubject() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();
        Subject<Object> subject = PublishSubject.create();
        subject.doOnNext(i -> counter.incrementAndGet())
                .doOnNext(i -> counter.decrementAndGet())
                .filter(i -> counter.get() != 0)
                .subscribe(System.out::println, throwable -> throwable.printStackTrace());
        Runnable runnable = () -> {
            for (int i = 0; i < 100000; i++) {
                try {
                    Thread.sleep(1);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                subject.onNext(i);
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        Thread.sleep(1000);
        System.out.println("종료~!!!!");
    }

    /*
    두 개의 스레드가 동시에 메모리에 접근하다 보니 이를 통과하는 경우가 생겨
    결국 임의로 만든 IllegalArgumentException이 발생.
    이처럼 스레드에 안전하지 않은 경우를 해결하려면
    다음과 같이 subject.toSerialized() 메서드를 통해 SerializedSubject를 객체로 생성할 수 있다

    testSerializedSubject  : 숫자가 출력된 후 위 종료가 출력 됨
    testSerializedSubject2 : 바로 종료가 출력 됨
     */
    @Test
    public void testSerializedSubject2() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();
        Subject<Object> subject = PublishSubject.create().toSerialized();
        subject.doOnNext(i -> counter.incrementAndGet())
                .doOnNext(i -> counter.decrementAndGet())
                .filter(i -> counter.get() != 0)
                .subscribe(System.out::println, throwable -> throwable.printStackTrace());
        Runnable runnable = () -> {
            for (int i = 0; i < 100000; i++) {
                try {
                    Thread.sleep(1);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                subject.onNext(i);
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        Thread.sleep(1000);
        System.out.println("종료~~~");
    }

    // 가장 최근 상태값을 가져오는 것이 중요할 때
    @Test
    public void testBehaviorSubject() {
        BehaviorSubject<Integer> subject = BehaviorSubject.create();
        subject.subscribe(item -> System.out.println("A ::: " + item));
        subject.onNext(1);
        subject.onNext(2);
        subject.subscribe(item -> System.out.println("B ::: " + item));
        subject.onNext(3);
        subject.subscribe(item -> System.out.println("C ::: " + item));
    }

    // 새로운 구독자가 구독을 요청하면 이전에 발행했던 아이템 모두를 구독자에게 전달
    @Test
    public void testReplaySubject() {
        ReplaySubject<Integer> subject = ReplaySubject.create();
        subject.subscribe(item -> System.out.println("A ::: " + item));
        subject.onNext(1);
        subject.onNext(2);
        subject.subscribe(item -> System.out.println("B ::: " + item));
        subject.onNext(3);
        subject.subscribe(item -> System.out.println("C ::: " + item));
    }

    // onComplete 호출 직전에 발행된 아이템만을 구독자들에게 전달
    @Test
    public void testAsyncSubject() {
        AsyncSubject<Integer> subject = AsyncSubject.create();
        subject.subscribe(item -> System.out.println("A ::: " + item));
        subject.onNext(1);
        subject.onNext(2);
        subject.subscribe(item -> System.out.println("B ::: " + item));
        subject.onNext(3);
        subject.onComplete();
        subject.subscribe(item -> System.out.println("C ::: " + item));
    }

    /*
    Observer가 UnicastSubject에 구독하기 전까지는 발행하는 아이템을 버퍼에 저장하고
    구독이 시작될 때 버퍼에 있던 아이템을 모두 발행하고 버퍼를 비워낸다.
    그러므로 구독자를 여러 개 둘 수 없다.
    첫번째 구독자가 모든 아이템을 다 소비, 두번째 구독자로부터는 아이템을 수신할 수 없음
     */
    @Test
    public void testUnicastSubject() throws InterruptedException {
        Subject<Long> subject = UnicastSubject.create();
        Observable.interval(1, TimeUnit.SECONDS).subscribe(subject);
        Thread.sleep(3000);
        subject.subscribe(i -> System.out.println("X :::: " + i));
        Thread.sleep(2000);
    }
}
