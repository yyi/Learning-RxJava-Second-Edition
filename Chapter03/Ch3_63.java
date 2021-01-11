import io.reactivex.rxjava3.core.Observable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Ch3_63 {
    public static void main(String[] args) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM:ss");
        System.out.println(LocalDateTime.now().format(f));
        Observable.just("Alpha", "Beta", "Gamma")
                .delay(3, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println(LocalDateTime.now().format(f) + " Received: " + s));
        sleep(5000);
        System.out.println("---------------------");
        Observable.just("Alpha", "Beta", "Gamma")
                .flatMap(t -> Observable.just(t).delay(1, TimeUnit.SECONDS))
                .subscribe(s -> System.out.println(LocalDateTime.now().format(f) + " Received: " + s));
        sleep(5000);
        System.out.println("---------------------");
        Observable.zip(Observable.just("Alpha", "Beta", "Gamma"), Observable.interval(1, TimeUnit.SECONDS), (a, b) -> a)
                .subscribe(s -> System.out.println(LocalDateTime.now().format(f) + " Received: " + s));

        sleep(5000);

    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
