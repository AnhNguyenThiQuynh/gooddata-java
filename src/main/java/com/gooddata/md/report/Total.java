/*
 * Copyright (C) 2007-2016, GoodData(R) Corporation. All rights reserved.
 */

package com.gooddata.md.report;

import static com.gooddata.util.Validate.notNull;

/**
 * Represents type of Total for {@link AttributeInGrid}
 */
public enum Total {
    SUM,
    AVG,
    MAX,
    MIN,
    NAT,
    MED;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static Total of(String total) {
        notNull(total, "total");
        return Total.valueOf(total.toUpperCase());
    }
}
