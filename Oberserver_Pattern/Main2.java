public class Main2 {

      public static void main(String args[]) {
            YouTubeChannel channel = new YouTubeChannel();

            Subscriber Swati = new Subscriber("Swati");
            Subscriber Shubham = new Subscriber("Shubham");
            channel.subscribe(Swati);
            channel.subscribe(Shubham);
            channel.uploadVideo("How to learn java programming");

      }
}
