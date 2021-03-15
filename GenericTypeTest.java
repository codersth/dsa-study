package com.example.myapplication.dsa;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import kotlin.NotImplementedError;

/**
 * @author zhanglei1
 * @date 2021/3/12-10:05
 * @since V1.0.0
 */
public class GenericTypeTest<T> {

    public static void main(String[] args) {
        // Instantiate a Human Object and call eat method by passing an instance of Apple.
        Human human = new Human();
        // Eat an apple.
        Strength<Apple> appleStrength = human.eat(new Apple());
        human.use(appleStrength);
        // Eat an orange.
        Strength<Orange> orangeStrength = human.eat(new Orange());
        human.use(orangeStrength);
        // Covariance in Arrays and Collections.
//        Eatable[] eatArray = new Apple[10];
//        ArrayList<Eatable> eatList = new ArrayList<Apple>();
    }

    /**
     * Detect instanceof in generic type.
     */
    @Test
    public void instanceofTest() {
        // Create an apple strength.
//        Strength<Apple> appleStrength = new Strength<>(new Apple());
        // Set to a more generic type.
//        Strength<? extends Eatable> eatStrength = appleStrength;
        // Generic converting from super to child.
//        Strength<Orange> orangeStrength = (Strength<Orange>)eatStrength;
        // 这一句会不会报错？？？
//        System.out.println("instanceofTest " + orangeStrength.source.getPetalCount());
    }
}

class Human {
    public <T extends Eatable> Strength<T> eat(T sth) {
        // 嚼
        // 吞
        return null;
    }

    public void use(Strength<? extends Eatable> strength) {

    }
}

interface Eatable {

}

class Strength<T> {

    /***
     * Add strength with another.
     * @param another strength with same source.
     * @return sum of two strength.
     */
    public /*static*/ Strength<T> add(Strength<T> another) {
        return null;
    }
}

class Food implements Eatable {

}

class Apple extends Food{
    private String color;
}

class Orange extends Food {
    public int getPetalCount() {
        return petalCount;
    }

    private int petalCount;
}