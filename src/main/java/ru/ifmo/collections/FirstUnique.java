package ru.ifmo.collections;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Design a class which contains integers and returns first unique integer (in order of addition).
 * FirstUniqueTest can be used as an example.
 */
public class FirstUnique {

    private Map<Integer, Integer> count;
    private Queue<Integer> order;

    public FirstUnique(int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Array shouldn't be null");
        }

        count = new HashMap<>();
        order = new ArrayDeque<>();

        for (int number : numbers) {
            add(number);
        }
    }

    public int showFirstUnique() {
        while (!order.isEmpty()) {
            if (count.get(order.element()) > 1) {
                order.poll();
            } else {
                return order.element();
            }
        }

        return -1;
    }

    public void add(int value) {
        count.merge(value, 1, Integer::sum);
        order.add(value);
    }
}
