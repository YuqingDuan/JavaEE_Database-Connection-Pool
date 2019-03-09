package pattern.decoration;

public class WaiterWrapper implements Waiter {
    Waiter waiter = null;

    public WaiterWrapper(Waiter waiter) {
        this.waiter = waiter;
    }

    @Override
    public void service() {
        System.out.println("微笑:::");
        waiter.service();
    }
}
