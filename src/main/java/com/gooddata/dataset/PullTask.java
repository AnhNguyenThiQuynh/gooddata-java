/*
 * Copyright (C) 2007-2014, GoodData(R) Corporation. All rights reserved.
 */
package com.gooddata.dataset;

import static com.gooddata.util.Validate.notEmpty;
import static org.apache.commons.lang.Validate.notNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.web.util.UriTemplate;

/**
 * Asynchronous ETL Pull 2 task (for internal use).
 * Deserialization only.
 */
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("pull2Task")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PullTask {

    public static final String URI = "/gdc/md/{projectId}/tasks/task/{taskId}";
    public static final UriTemplate TEMPLATE = new UriTemplate(URI);

    private final Links links;

    @JsonCreator
    private PullTask(@JsonProperty("links") Links links) {
        notNull(links, "links cannot be null!");

        this.links = links;
    }

    public Links getLinks() {
        return links;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Links {

        private final String uri;

        @JsonCreator
        private Links(@JsonProperty("poll") String uri) {
            notEmpty(uri, "uri");

            this.uri = uri;
        }

        public String getUri() {
            return uri;
        }
    }
}
