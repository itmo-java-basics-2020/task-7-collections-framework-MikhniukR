package ru.ifmo.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Design a class to find the kth largest element in a stream. k is from 1 to numbers.length.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Constructor accepts an integer k and an integer array numbers, which contains initial elements from the stream.
 * For each call to the method KthLargest.add(), return the element representing the kth largest element in the stream.
 */
public class KthLargest {

    private PriorityQueue<Integer> queue;

    public KthLargest(int k, int[] numbers) {
        if (numbers.length < k) {
            throw new IllegalArgumentException("k should be not less than numbers.length");
        }

        queue = Arrays.stream(numbers).boxed()
                .collect(Collectors.toCollection(PriorityQueue::new));

        while (queue.size() > k) {
            queue.poll();
        }
    }

    public int add(int val) {
        if (val > queue.element()) {
            queue.add(val);
            queue.poll();
        }

        return queue.element();
    }
}