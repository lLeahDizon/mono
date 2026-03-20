package com.leah.mono.common;

import com.leah.mono.common.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilsTest {

    @Test
    void shouldDistinctAndKeepOrder() {
        List<String> input = List.of("a", "b", "a", "c", "b");
        List<String> result = CollectionUtils.distinctPreserveOrder(input);
        assertEquals(List.of("a", "b", "c"), result);
    }

    @Test
    void shouldCountByKey() {
        List<String> input = List.of("java", "js", "java", "go");
        Map<Integer, Long> result = CollectionUtils.countByKey(input, String::length);
        assertEquals(2L, result.get(4));
        assertEquals(1L, result.get(2));
    }

    @Test
    void shouldReturnSubListWhenRangeValid() {
        List<Integer> input = List.of(1, 2, 3, 4, 5);
        List<Integer> result = CollectionUtils.safeSubList(input, 1, 4);
        assertEquals(List.of(2, 3, 4), result);
    }

    @Test
    void shouldThrowBusinessExceptionWhenRangeInvalid() {
        List<Integer> input = List.of(1, 2, 3);
        BusinessException ex = assertThrows(BusinessException.class, () -> CollectionUtils.safeSubList(input, -1, 2));
        assertTrue(ex.getMessage().contains("invalid subList range"));
    }

    @Test
    void shouldThrowBusinessExceptionWhenFromGreaterThanTo() {
        List<Integer> input = List.of(1, 2, 3);
        assertThrows(BusinessException.class, () -> CollectionUtils.safeSubList(input, 2, 1));
    }
}
