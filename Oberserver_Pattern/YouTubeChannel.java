import java.util.ArrayList;
import java.util.List;

public class YouTubeChannel {
    private List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void uploadVideo(String title) {
        System.out.println("Uploading:" + title);

        for (Subscriber subscriber : subscribers) {
            subscriber.receiveNofication(title);
        }
    }
}
