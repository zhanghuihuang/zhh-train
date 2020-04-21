package com.zhh.train.order.observable;

import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.observables.SyncOnSubscribe;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/19 8:33 上午
 */
public class ObservableDemo {
    public static void main(String[] args) {
        //同步被观察者
        Observable<Object> syncObservable = Observable.create(new SyncOnSubscribe<Object, Object>() {
            private String state = "哈哈";

            @Override
            protected Object generateState() {
                return state;
            }

            @Override
            protected Object next(Object state, Observer<? super Object> observer) {
                observer.onNext(state);
                observer.onCompleted();
                return state;
            }
        });

        Subscriber<Object> subscriber = new Subscriber<Object>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void setProducer(Producer p) {
                super.setProducer(p);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext:" + o);
            }
        };

        Observable<Object> objectObservable = syncObservable.doOnNext(new Action1<Object>() {
            @Override
            public void call(Object o) {
                System.out.println(o);
            }
        });
        syncObservable.subscribe(subscriber);
        objectObservable.subscribe(subscriber);
    }
}
