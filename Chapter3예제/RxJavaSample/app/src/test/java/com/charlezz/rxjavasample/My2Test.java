package com.charlezz.rxjavasample;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * RxJava 연산자
 */
public class My2Test {

    // Observable을 생성하는 연산자.........

    @Test
    public void testDefer() {
        System.out.println("........ testDefer ...........");
        Observable<Long> justSrc = Observable.just(
                System.currentTimeMillis()
        );
        Observable<Long> deferSrc = Observable.defer(() ->
                Observable.just(System.currentTimeMillis())
        );

        System.out.println(">>> 1 now = " + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(">>> 2 now = " + System.currentTimeMillis());
        justSrc.subscribe(time -> System.out.println(">>> 1 time = " + time));
        deferSrc.subscribe(time -> System.out.println(">>> 2 time = " + time));
    }

    @Test
    public void testEmptyAndNever() {
        System.out.println("........ testEmptyAndNever ...........");
        Observable.empty()
                .doOnTerminate(() -> System.out.println("empty----"))
                .subscribe(System.out::println);
        Observable.never()
                .doOnTerminate(() -> System.out.println("never----"))
                .subscribe(System.out::println);
    }

    @Test
    public void testInterval() throws InterruptedException {
        System.out.println("........ testInterval ...........");
        Disposable d = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);
        Thread.sleep(5000);
        d.dispose();
    }

    @Test
    public void testRange() {
        System.out.println("........ testRange ...........");
        Observable.range(1, 3).subscribe(System.out::println);
    }

    @Test
    public void testTimer() throws InterruptedException {
        System.out.println("........ testTimer ...........");
        Observable src = Observable.timer(1, TimeUnit.SECONDS);
        System.out.println("구독!!!!!!");
        src.subscribe(event -> System.out.println("실행!!!"));
        Thread.sleep(5000);
    }

    @Test
    public void testMap() {
        System.out.println("........ testMap ...........");
        Observable<Integer> intSrc = Observable.just(1, 2, 3);
        Observable<Integer> strSrc = intSrc.map(value -> value * 20);
        strSrc.subscribe(System.out::println);
    }

    @Test
    public void testFlatMap() {
        System.out.println("........ testFlatMap ...........");
        Observable<String> src = Observable.just("a", "b", "c");
        src.flatMap(str -> Observable.just(str + 1, str + 2))
                .subscribe(System.out::println);
    }

    @Test
    public void testFlatMap2() {
        System.out.println("........ testFlatMap2 ...........");
        Observable.range(2, 8)
                .flatMap(x -> Observable.range(1, 9)
                        .map(y -> String.format("%d * %d = %d", x, y, x * y)))
                .subscribe(System.out::println);
    }

    @Test
    public void testBuffer() {
        System.out.println("........ testBuffer ...........");
        Observable.range(0, 10)
                .buffer(3).subscribe(integers -> {
            System.out.println("버퍼 데이터 발행~~~");
            for (Integer integer : integers) {
                System.out.println(">>> " + integer);
            }
        });
    }

    @Test
    public void testScan() {
        System.out.println("........ testScan ...........");
        Observable.range(1, 5)
                .scan((x, y) -> {
                    System.out.println(String.format("%d + %d = ", x, y));
                    return x + y;
                })
                .subscribe(System.out::println);
    }

    @Test
    public void testScan2() {
        System.out.println("........ testScan2 ...........");
        Observable.just("a", "s", "d", "f", "g")
                .scan((x, y) -> x + y)
                .subscribe(System.out::println);
    }

    @Test
    public void testGroupBy() {
        System.out.println("........ testGroupBy ...........");
        Observable.just("Magenta Circle",
                "Cyan Circle",
                "Yellow Triangle",
                "Yellow Circle", "Magenta Triangle", "Cyan Triangle")
                .groupBy(item -> {
                    if (item.contains("Circle")) {
                        return "C";
                    } else if (item.contains("Triangle")) {
                        return "T";
                    } else {
                        return "None";
                    }
                })
                .subscribe(group -> {
                    System.out.println(group.getKey() + "그룹 발행 시작");
                    group.subscribe(shape ->
                            System.out.println(group.getKey() + " : " + shape));
                });
    }

    @Test
    public void testDebounce() throws InterruptedException {
        System.out.println("........ testDebounce ...........");
        Observable.create(emitter -> {
            emitter.onNext("2");
            Thread.sleep(100);
            emitter.onNext("3");
            emitter.onNext("4");
            emitter.onNext("5");
            emitter.onNext("6");
            Thread.sleep(100);
            emitter.onNext("7");
        })
                .debounce(10, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println);
        Thread.sleep(300);
    }

    @Test
    public void testDistinct() {
        System.out.println("........ testDistinct ...........");
        Observable.just(2, 1, 1, 2, 3).distinct()
                .subscribe(System.out::println);
    }

    @Test
    public void testElementAt() {
        System.out.println("........ testElementAt ...........");
        Observable.just(3, 4, 5, 6)
                .elementAt(2)
                .subscribe(System.out::println);
    }

    @Test
    public void testFilter() {
        System.out.println("........ testFilter ...........");
        Observable.just(2, 22, 3, 33, 4, 55)
                .filter(x -> x > 10)
                .subscribe(System.out::println);
    }

    @Test
    public void testSample() throws InterruptedException {
        System.out.println("........ testSample ...........");
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .sample(300, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println);
        Thread.sleep(1000);
    }

    @Test
    public void testSkip() {
        System.out.println("........ testSkip ...........");
        Observable.just(3, 4, 5, 6)
                .skip(2)
                .subscribe(System.out::println);
    }

    @Test
    public void testTake() {
        System.out.println("........ testTake ...........");
        Observable.just(3, 4, 5, 6)
                .take(2)
                .subscribe(System.out::println);
    }

    @Test
    public void testAll() {
        System.out.println("........ testAll ...........");
        Observable.just(3, 4, 5)
                .all(integer -> integer > 0)
                .subscribe(System.out::println);
    }

    @Test
    public void testAll2() {
        System.out.println("........ testAll2 ...........");
        Observable.just(3, 4, 0)
                .all(integer -> integer > 0)
                .subscribe(System.out::println);
    }

    @Test
    public void testAmb() {
        System.out.println("........ testAmb ...........");
        ArrayList<Observable<Integer>> list = new ArrayList<>();
        list.add(Observable.just(10, 20, 30)
                .delay(100, TimeUnit.MILLISECONDS));
        list.add(Observable.just(3, 4, 5));
        list.add(Observable.just(11, 22, 33)
                .delay(200, TimeUnit.MILLISECONDS));
        Observable.amb(list).subscribe(System.out::println);
    }

    @Test
    public void testCombineLatest() throws InterruptedException {
        System.out.println("........ testCombineLatest ...........");
        Observable<Integer> src1 = Observable.create(emitter ->
                new Thread(() -> {
                    for (int i = 0; i <= 5; i++) {
                        emitter.onNext(i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start()
        );

        Observable<String> src2 = Observable.create(emitter ->
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                        emitter.onNext("a");
                        Thread.sleep(700);
                        emitter.onNext("s");
                        Thread.sleep(100);
                        emitter.onNext("d");
                        Thread.sleep(700);
                        emitter.onNext("f");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start()
        );

        Observable.combineLatest(src1, src2, (num, str) -> num + str)
                .subscribe(System.out::println);
        Thread.sleep(5000);
    }

    @Test
    public void testZip() throws InterruptedException {
        System.out.println("........ testZip ...........");
        Observable<Integer> src1 = Observable.create(emitter ->
                new Thread(() -> {
                    for (int i = 0; i <= 5; i++) {
                        emitter.onNext(i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start()
        );

        Observable<String> src2 = Observable.create(emitter ->
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                        emitter.onNext("a");
                        Thread.sleep(700);
                        emitter.onNext("s");
                        Thread.sleep(100);
                        emitter.onNext("d");
                        Thread.sleep(700);
                        emitter.onNext("f");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start()
        );

        Observable.zip(src1, src2, (num, str) -> num + str)
                .subscribe(System.out::println);
        Thread.sleep(5000);
    }

    @Test
    public void testMerge() throws InterruptedException {
        System.out.println("........ testMerge ...........");
        Observable src1 = Observable.intervalRange(
                1, 5, 0, 100, TimeUnit.MILLISECONDS)
                .map(value -> value * 20
                );
        Observable src2 = Observable.create(emitter -> new Thread(() -> {
            try {
                Thread.sleep(350);
                emitter.onNext(1);
                Thread.sleep(250);
                emitter.onNext(111);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());
        Observable.merge(src1, src2).subscribe(System.out::println);
        Thread.sleep(1000);
    }

}
