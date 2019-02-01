package com.example.manibala.espressokt

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

object SimpleRx{
    var bag = CompositeDisposable()
    fun simpleValues(){
        println("12345simplevalues")
        val someInfo = BehaviorRelay.createDefault("1")
        println("someInfo.value ${someInfo.value}")

        val plainString = someInfo.value
        println("plainString : $plainString")

        someInfo.accept("2")
        println("someInfo.value ${someInfo.value}")

        someInfo.subscribe{newValue ->
            println("value has changed: $newValue")
        }
        someInfo.accept("3")
        //Relays will never receive onError, and onComplete events
    }
    fun subjects(){
        val behaviorSubject = BehaviorSubject.createDefault(24)
        val disposable = behaviorSubject.subscribe({ newValue ->
            println("behaviorSubject subscription: $newValue")
        }, { error -> //onError
            println("error: ${ error.localizedMessage}")
        }, { // onCompleted
            println("completed")
        }, { disposable ->  // onSubscribed
            println("subscribed")
        })
        behaviorSubject.onNext(34)
        behaviorSubject.onNext(48)
        behaviorSubject.onNext(48) // duplicates show as new events by default

    }
}