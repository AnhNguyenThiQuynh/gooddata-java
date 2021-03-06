/*
 * Copyright (C) 2007-2014, GoodData(R) Corporation. All rights reserved.
 */
package com.gooddata.project;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

/**
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Projects {

    public static final String URI = "/gdc/projects";

    private final Collection<Project> projects;

    @JsonCreator
    public Projects(@JsonProperty("projects") Collection<Project> projects) {
        this.projects = projects;
    }

    public Collection<Project> getProjects() {
        return projects;
    }
}
