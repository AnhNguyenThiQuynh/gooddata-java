/*
 * Copyright (C) 2007-2016, GoodData(R) Corporation. All rights reserved.
 */

package com.gooddata.md.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.gooddata.JsonMatchers.serializesToJson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;

public class GridElementTest {

    @Test
    public void testSerializer() throws Exception {
        final GridElements elems = new GridElements();
        elems.add(new AttributeInGrid("/uriValue", "aliasValue"));
        elems.add(MetricGroup.instance());
        assertThat(elems, serializesToJson("/md/report/gridElements.json"));
    }

    @Test
    public void testDeserializer() throws Exception {
        final GridElements elems = new ObjectMapper().readValue(
                getClass().getResourceAsStream("/md/report/gridElements.json"), GridElements.class);

        assertThat(elems, is(notNullValue()));
        assertThat(elems, hasSize(2));
        assertThat(elems.get(0), instanceOf(AttributeInGrid.class));
        assertThat(elems.get(1), instanceOf(MetricGroup.class));
    }

    @JsonSerialize(contentUsing = GridElement.Serializer.class)
    @JsonDeserialize(contentUsing = GridElement.Deserializer.class)
    private static class GridElements extends ArrayList<GridElement> {}
}