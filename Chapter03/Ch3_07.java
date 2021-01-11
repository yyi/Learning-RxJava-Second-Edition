import io.reactivex.rxjava3.core.Observable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Ch3_07 {
    public static void main(String[] args) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("ss:SSS");
        System.out.println(LocalDateTime.now().format(f));
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(5, TimeUnit.SECONDS)
                .count()
                .subscribe(i -> System.out.println(LocalDateTime.now().format(f) + " RECEIVED: " + i));
        sleep(6000);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
