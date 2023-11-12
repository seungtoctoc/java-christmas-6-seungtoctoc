package christmas.model;

public class Order {
    private String name;
    private int number;

    public Order(String order) {
        String[] nameAndNum = order.split("-");
        if(nameAndNum.length != 2) {
우
        }
        this.name = name;
        this.number = number;
    }
}
