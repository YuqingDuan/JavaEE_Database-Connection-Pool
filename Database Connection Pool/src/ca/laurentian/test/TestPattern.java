package ca.laurentian.test;

import pattern.decoration.Waiter;
import pattern.decoration.WaiterWrapper;
import pattern.decoration.Waitress;

public class TestPattern {
    public static void main(String[] args) {
        // 面向接口编程
        Waiter waiter = new WaiterWrapper(new Waitress());
        waiter.service();
    }
}
