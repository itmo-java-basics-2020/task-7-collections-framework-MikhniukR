package ru.ifmo.collections;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents sorted set of unique values.
 * create() returns a SortedSet instance with natural ordering. (i.e. from smallest to largest in case of integer numbers)
 * from() is used to create a SortedSet instance with given Comparator.
 * Instances of a class can be created using only these two methods above.
 * <p>
 * This class should not be abstract and should be capable of adding/removing single/multiple elements.
 * It has two more methods: getSorted() and getReversed() which return an array of set contents in forward and reverse order respectively.
 * <p>
 * NB! This class must have only map(s) as an internal data structure(s).
 *
 * @param <T> set contents type
 */
public class SortedSet<T> extends AbstractSet<T> {

    private static final Object EXISTS = new Object();
    private TreeMap<T, Object> contents;

    private SortedSet() {
        this.contents = new TreeMap<>();
    }

    private SortedSet(Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator shouldn't be null");
        }

        contents = new TreeMap<>(comparator);
    }

    @Override
    public Iterator<T> iterator() {
        return contents.keySet().iterator();
    }

    @Override
    public int size() {
        return contents.size();
    }

    @Override
    public boolean add(T item) {
        return contents.put(item, EXISTS) == null;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Collection for adding shouldn't be null");
        }

        boolean changes = false;
        for (T t : collection) {
            changes |= (contents.put(t, EXISTS) == null);
        }

        return changes;
    }

    @Override
    public boolean remove(Object o) {
        return contents.remove(o) != null;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Collection for adding shouldn't be null");
        }

        boolean changes = false;
        for (Object o : collection) {
            changes |= (contents.remove(o) != null);
        }

        return changes;
    }

    public static <T> SortedSet<T> create() {
        return new SortedSet<>();
    }

    public static <T> SortedSet<T> from(Comparator<T> comparator) {
        return new SortedSet<>(comparator);
    }

    public List<T> getSorted() {
        return new ArrayList<>(contents.keySet());
    }

    public List<T> getReversed() {
        return new ArrayList<>(contents.descendingKeySet());
    }
}
