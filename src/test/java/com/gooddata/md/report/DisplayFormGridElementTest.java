/*
 * Copyright (C) 2007-2014, GoodData(R) Corporation. All rights reserved.
 */
package com.gooddata.md.report;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

import static com.gooddata.JsonMatchers.serializesToJson;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertThat;

public class DisplayFormGridElementTest {

    public static final String URI = "/URI";
    public static final String ALIAS = "ALIAS";

    @Test
    public void testDeserialization() throws Exception {
        final InputStream is = getClass().getResourceAsStream("/md/report/displayFormGridElement.json");
        final DisplayFormGridElement attr = new ObjectMapper().readValue(is, DisplayFormGridElement.class);

        assertThat(attr, is(notNullValue()));
        assertThat(attr.getUri(), is(URI));
        assertThat(attr.getAlias(), is(ALIAS));

        assertThat(attr.getTotals(), is(notNullValue()));
        assertThat(attr.getTotals(), hasSize(2));
        final Iterator<Collection<String>> i = attr.getTotals().iterator();
        final Collection<String> subTotal1 = i.next();
        assertThat(subTotal1, hasSize(1));
        assertThat(subTotal1.iterator().next(), is("TOTAL1"));
        final Collection<String> subTotal2 = i.next();
        assertThat(subTotal2, hasSize(2));
        assertThat(subTotal2.iterator().next(), isOneOf("TOTAL2", "TOTAL3"));
        assertThat(subTotal2.iterator().next(), isOneOf("TOTAL2", "TOTAL3"));
    }

    @Test
    public void testSerialization() throws Exception {
        final Collection<Collection<String>> totals = asList((Collection<String>) asList("TOTAL1"),
                asList("TOTAL2", "TOTAL3"));
        final DisplayFormGridElement attr = new DisplayFormGridElement(URI, totals, ALIAS);
        assertThat(attr, serializesToJson("/md/report/displayFormGridElement.json"));
    }

}