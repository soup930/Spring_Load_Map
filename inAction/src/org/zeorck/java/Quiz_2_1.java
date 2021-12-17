package org.zeorck.java;

import java.util.ArrayList;
import java.util.List;

interface AppleFormatter {
    String accept(Apple a);
}

class AppleFancyFomatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A" + characteristic + " " + apple.getColor() + "apple";
    }
}

class AppleSimpleFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}

public class Quiz_2_1 {

    public static void prettyPrintApple(List<Apple> inventory,
                                        AppleFormatter formatter) {
        for(Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(150, "green"));
        inventory.add(new Apple(130, "red"));
        prettyPrintApple(inventory, new AppleFancyFomatter());
    }
}
