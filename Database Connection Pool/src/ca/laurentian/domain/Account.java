package ca.laurentian.domain;

public class Account {
    private String name;
    private int money;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account [name=" + name + ", money=" + money + "]";
    }
}
