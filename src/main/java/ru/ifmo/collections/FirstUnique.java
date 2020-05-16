package ru.ifmo.collections;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * Design a class which contains integers and returns first unique integer (in order of addition).
 * FirstUniqueTest can be used as an example.
 */
public class FirstUnique {

    private Set<Integer> unique;
    private Set<Integer> noUnique;
    private Queue<Integer> order;

    public FirstUnique(int[] numbers) {
        unique = new HashSet<>();
        noUnique = new HashSet<>();
        order = new ArrayDeque<>();

        for (int number : numbers) {
            add(number);
        }
    }

    public int showFirstUnique() {
        while (!order.isEmpty()) {
            if (noUnique.contains(order.element())) {
                order.poll();
            } else {
                return order.element();
            }
        }

        return -1;
    }

    public void add(int value) {
        if (!unique.contains(value)) {
            unique.add(value);
            order.add(value);
        } else {
            unique.remove(value);
            noUnique.add(value);
        }
    }
}
