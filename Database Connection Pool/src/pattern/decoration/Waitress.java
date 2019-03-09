package pattern.decoration;

public class Waitress implements Waiter {
    @Override
    public void service() {
        System.out.println("在服务...");
    }
}
