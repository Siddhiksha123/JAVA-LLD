

public class Subscriber {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void receiveNofication(String videoTitle) {
        System.out.println(this.name + " received notification :" + videoTitle);
    }

}
