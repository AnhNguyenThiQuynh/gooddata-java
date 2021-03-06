package com.gooddata.md;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class UseManyEntries {

    private final String uri;

    private final Collection<Entry> entries;

    @JsonCreator
    UseManyEntries(@JsonProperty("uri") final String uri,
                   @JsonProperty("entries") final Collection<Entry> entries) {
        this.uri = uri;
        this.entries = entries;
    }

    public String getUri() {
        return uri;
    }

    public Collection<Entry> getEntries() {
        return entries;
    }
}
