/*
 * Copyright (C) 2007-2015, GoodData(R) Corporation. All rights reserved.
 */
package com.gooddata.gdc;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.web.util.UriTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * @deprecated use {@link com.gooddata.featureflag.FeatureFlagService} and {@link com.gooddata.featureflag.FeatureFlags} instead
 */
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("featureFlags")
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
@SuppressWarnings("deprecation")
public class FeatureFlags {

    public static final String AGGREGATED_FEATURE_FLAGS_URI = "/gdc/internal/projects/{projectId}/featureFlags";
    public static final UriTemplate AGGREGATED_FEATURE_FLAGS_TEMPLATE = new UriTemplate(AGGREGATED_FEATURE_FLAGS_URI);

    private final List<FeatureFlag> featureFlags = new ArrayList<>();

    /* protected helper method for JSON deserialization */
    @JsonAnySetter
    protected void addFlag(final String name, final boolean enabled) {
        notNull(name);
        featureFlags.add(new FeatureFlag(name, enabled));
    }

    /**
     * @deprecated use {@link com.gooddata.featureflag.FeatureFlags#isEnabled(String)} method or its {@link Iterable} feature
     * @return list of feature flags
     */
    @Deprecated
    public List<FeatureFlag> getFeatureFlags() {
        return featureFlags;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final FeatureFlags that = (FeatureFlags) o;

        return !(featureFlags != null ? !featureFlags.equals(that.featureFlags) : that.featureFlags != null);
    }

    @Override
    public int hashCode() {
        return featureFlags != null ? featureFlags.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FeatureFlags{" + featureFlags + "}";
    }
}
