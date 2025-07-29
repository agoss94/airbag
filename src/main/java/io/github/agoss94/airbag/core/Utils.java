package io.github.agoss94.airbag.core;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

/**
 * A utility class for various purposes.
 */
public final class Utils {

    /**
     * Private constructor to prevent instantiation.
     */
    private Utils() {
    }

    /**
     * Checks if two lists are equal using a custom predicate.
     *
     * @param list1 the first list.
     * @param list2 the second list.
     * @param predicate the predicate to use for comparing elements.
     * @param <T> the type of the elements in the lists.
     * @return {@code true} if the lists are equal, {@code false} otherwise.
     */
    public static <T> boolean listEquals(List<? extends T> list1, List<? extends T> list2, BiPredicate<? super T, ? super T> predicate) {
        if (list1 == list2) {
            return true;
        }
        if (list1 == null || list2 == null || list1.size() != list2.size()) {
            return false;
        }
        return IntStream.range(0, list1.size())
                .allMatch(i -> predicate.test(list1.get(i), list2.get(i)));
    }

}
