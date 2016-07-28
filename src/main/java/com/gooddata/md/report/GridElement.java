/*
 * Copyright (C) 2007-2014, GoodData(R) Corporation. All rights reserved.
 */
package com.gooddata.md.report;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Grid element
 * (metricGroup | AttributeInGrid ... metricGroup can be maximally
 * one time in rows or columns)
 */
public interface GridElement {

    class Serializer extends JsonSerializer<GridElement> {

        @Override
        public void serialize(GridElement value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value instanceof AttributeInGrid) {
                serializers.defaultSerializeValue(value, gen);
            } else if (value instanceof MetricGroup){
                gen.writeString(((MetricGroup) value).getValue());
            } else {
                throw new JsonGenerationException("Unsupported kind of GridElement: " + value.getClass().getName(), gen);
            }
        }
    }

    class Deserializer extends JsonDeserializer<GridElement> {

        @Override
        public GridElement deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            switch (jp.currentToken()) {
                case VALUE_STRING:
                    final String textValue = ctxt.readValue(jp, String.class);
                    if (MetricGroup.equals(textValue)) {
                        return MetricGroup.instance();
                    } else {
                        throw ctxt.mappingException("Unknown string representation of GridElement: %s", textValue);
                    }
                case START_OBJECT:
                    return ctxt.readValue(jp, AttributeInGrid.class);
                default:
                    throw ctxt.mappingException("Unknown type of GridElement");
            }
        }
    }



}
