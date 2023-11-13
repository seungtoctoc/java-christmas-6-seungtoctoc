package christmas.model;

import java.util.List;

public enum Menu {
    APPETIZER1("양송이수프", "에피타이저", 6_000),
    APPETIZER2("타파스", "에피타이저", 5_500),
    APPETIZER3("시저샐러드", "에피타이저", 8_000),
    MAIN1("티본스테이크", "메인", 55_000),
    MAIN2("바비큐립", "메인", 54_000),
    MAIN3("해산물파스타", "메인", 35_000),
    MAIN4("크리스마스파스타", "메인", 25_000),
    DESSERT1("초코케이크", "디저트", 15_000),
    DESSERT2("아이스크림", "디저트", 5_000),
    DRINK1("제로콜라", "음료", 3_000),
    DRINK2("레드와인", "음료", 60_000),
    DRINK3("샴페인", "음료", 25_000);


    private String name;
    private String category;
    private int price;

    private Menu (String name, String category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public static boolean isInMenu(String name) {
        for (Menu menu : Menu.values()) {
            String currentName = menu.name;

            if(currentName.equals(name)){
                return true;
            }
        }
        return false;
    }
}
