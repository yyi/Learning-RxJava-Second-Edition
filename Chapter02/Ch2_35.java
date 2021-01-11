import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class Ch2_35 {
    private static final CompositeDisposable disposables = new CompositeDisposable();

    public static void main(String[] args) {
        ConnectableObservable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS).publish();
        //subscribe and capture disposables
        Disposable disposable1 =
                seconds.subscribe(l -> System.out.println("Observer 1: " + l));
        Disposable disposable2 =
                seconds.subscribe(l -> System.out.println("Observer 2: " + l));
        //put both disposables into CompositeDisposable
        disposables.addAll(disposable1, disposable2);
        seconds.connect();
        //sleep 5 seconds
        sleep(5100);
        //dispose all disposables
        disposables.dispose();
        //sleep 5 seconds to prove
        //there are no more emissions
        sleep(5000);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
