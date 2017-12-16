package com.example.cowboy.authmodule.realm;

import com.example.cowboy.authmodule.model.User;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Cowboy on 16.12.2017.
 */

public class RealmService implements IRealmService {

    private Realm mRealm;

    public RealmService(final Realm realm) {
        mRealm = realm;
    }

    @Override
    public Realm get() {
        return null;
    }

    @Override
    public void closeRealm() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public <T extends RealmObject> Observable<T> addObject(T object, Class<T> clazz) {
        long id;
        try {
            id = mRealm.where(clazz).max("id").intValue() + 1;
        } catch (Exception e) {
            id = 0L;
        }
        ((User) object).setId(id);
        return Observable.just(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable::printStackTrace)
                .flatMap(t -> Observable.just(t)
                        .doOnSubscribe(mRealm::beginTransaction)
                        .doOnUnsubscribe(() -> {
                            mRealm.commitTransaction();
                        })
                        .doOnError(Throwable::printStackTrace)
                        .doOnNext(next -> mRealm.copyToRealmOrUpdate(next))
                );
    }

    @Override
    public <T extends RealmObject> Observable<RealmResults<T>> getObjects(Class<T> clazz) {
        return Observable.just(clazz)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable::printStackTrace)
                .flatMap(t -> Observable.just(t)
                        .doOnSubscribe(mRealm::beginTransaction)
                        .doOnUnsubscribe(() -> {
                            mRealm.commitTransaction();
                        })
                        .doOnError(Throwable::printStackTrace)
                        .map(type -> mRealm.where(clazz).findAll())
                );
    }

    @Override
    public <T extends RealmObject> Observable<Class<T>> deleteObject(long id, Class<T> clazz) {
        return Observable.just(clazz)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable::printStackTrace)
                .doOnSubscribe(mRealm::beginTransaction)
                .doOnUnsubscribe(() -> {
                    mRealm.commitTransaction();
                })
                .doOnNext(type -> mRealm.where(clazz).equalTo("id", id).findFirst().removeFromRealm());
    }

    @Override
    public <T extends RealmObject> Observable<Class<T>> deleteAllObjects(Class<T> clazz) {
        return Observable.just(clazz)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable::printStackTrace)
                .doOnSubscribe(mRealm::beginTransaction)
                .doOnUnsubscribe(() -> {
                    mRealm.commitTransaction();
                })
                .doOnNext(type -> mRealm.where(clazz).findAll().clear());
    }

    @Override
    public <T extends RealmObject> Observable<T> getLastObject(Class<T> clazz) {
        return Observable.just(clazz)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable::printStackTrace)
                .flatMap(t -> Observable.just(t)
                        .doOnSubscribe(mRealm::beginTransaction)
                        .doOnUnsubscribe(() -> {
                            mRealm.commitTransaction();
                        })
                        .doOnError(Throwable::printStackTrace)
                        .map(type -> mRealm.where(clazz).findAll().last())
                );
    }

    @Override
    public <T extends RealmObject> Observable<T> getObject(Class<T> clazz, long id) {
        return Observable.just(clazz)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable::printStackTrace)
                .flatMap(t -> Observable.just(t)
                        .doOnSubscribe(mRealm::beginTransaction)
                        .doOnUnsubscribe(() -> {
                            mRealm.commitTransaction();
                        })
                        .doOnError(Throwable::printStackTrace)
                        .map(type -> mRealm.where(clazz).equalTo("id", id).findFirst())
                );
    }
}
