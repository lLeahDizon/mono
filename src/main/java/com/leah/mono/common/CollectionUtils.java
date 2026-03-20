package com.leah.mono.common;

import com.leah.mono.common.exception.BusinessException;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CollectionUtils {

    private CollectionUtils() {
    }

    public static <T> List<T> distinctPreserveOrder(List<T> input) {
        return new ArrayList<>(new LinkedHashSet<>(input));
    }

    public static <T, K> Map<K, Long> countByKey(List<T> input, Function<T, K> keyMapper) {
        return input.stream().collect(Collectors.groupingBy(keyMapper, Collectors.counting()));
    }

    public static <T> List<T> safeSubList(List<T> input, int from, int to) {
        if (from < 0 || to > input.size() || from > to) {
            throw new BusinessException("invalid subList range: from=" + from + ", to=" + to);
        }
        return input.subList(from, to);
    }
}
