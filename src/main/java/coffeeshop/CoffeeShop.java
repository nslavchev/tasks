package coffeeshop;

import java.util.*;

public class CoffeeShop {
    public Map<Coffee, Long> buyCoffee() {
        Coffee coffee = createCoffee();
        return Collections.singletonMap(coffee, coffee.price);
    }

    public Map<Coffee, Long> buyCoffees(int numCoffees) {
        Map<Coffee, Long> order = new HashMap<>();

        for(int i=0;i<numCoffees;i++) {
            order.putAll(buyCoffee());
        }

        return order;
    }

    public long Charge(CreditCard cc, Map<Coffee, Long> bill){
        long amount = bill.values().stream().mapToLong(l -> l).sum();
        cc.charge(amount);
        return amount;
    }

    protected Coffee createCoffee(){
        return new Coffee(10);
    };
}
