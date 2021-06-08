package coffeeshop;

public class Main {
    public static void main(String[] args) {
        CreditCard cc = new CreditCard();
        CoffeeShop regularCoffeeShop = new CoffeeShop();

        regularCoffeeShop.Charge(cc, regularCoffeeShop.buyCoffee());
        regularCoffeeShop.Charge(cc, regularCoffeeShop.buyCoffees(5));
    }
}
