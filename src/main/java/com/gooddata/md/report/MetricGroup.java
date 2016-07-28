/*
 * Copyright (C) 2007-2016, GoodData(R) Corporation. All rights reserved.
 */

package com.gooddata.md.report;

/**
 * Marker element marking the placement of metrics in Grid report.
 * Can be contained either in rows or columns of {@link Grid}.
 */
public final class MetricGroup implements GridElement {

    private final String value;

    private static final String JSON_VALUE = "metricGroup";
    private static final MetricGroup INSTANCE = new MetricGroup(JSON_VALUE);

    /**
     * @return singleton instance of MetricGroup
     */
    public static MetricGroup instance() {
        return INSTANCE;
    }

    private MetricGroup(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * @param string string to compare whether is metricGroup
     * @return true when the {@link #instance()}'s string value equals to ther argument, false otherwise
     */
    public static boolean equals(String string) {
        return instance().getValue().equals(string);
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return getValue() != null ? getValue().hashCode() : 0;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
