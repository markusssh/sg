package dev.sg.utils;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

@Getter
public final class PairOfReportsAndPageLimit<S, T> {

    private final S report;
    private final T pageLimit;

    private PairOfReportsAndPageLimit(S report, T pageLimit) {

        Assert.notNull(report, "First must not be null");
        Assert.notNull(pageLimit, "Second must not be null");

        this.report = report;
        this.pageLimit = pageLimit;
    }

    public static <S, T> PairOfReportsAndPageLimit<S, T> of(S first, T second) {
        return new PairOfReportsAndPageLimit<>(first, second);
    }

    public static <S, T> Collector<PairOfReportsAndPageLimit<S, T>, ?, Map<S, T>> toMap() {
        return Collectors.toMap(PairOfReportsAndPageLimit::getReport, PairOfReportsAndPageLimit::getPageLimit);
    }

    @Override
    public boolean equals(@Nullable Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof PairOfReportsAndPageLimit<?, ?> pair)) {
            return false;
        }

        if (!ObjectUtils.nullSafeEquals(report, pair.report)) {
            return false;
        }

        return ObjectUtils.nullSafeEquals(pageLimit, pair.pageLimit);
    }

    @Override
    public int hashCode() {
        int result = ObjectUtils.nullSafeHashCode(report);
        result = 31 * result + ObjectUtils.nullSafeHashCode(pageLimit);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s->%s", this.report, this.pageLimit);
    }
}
