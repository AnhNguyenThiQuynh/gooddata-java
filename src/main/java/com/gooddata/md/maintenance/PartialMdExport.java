package com.gooddata.md.maintenance;

import static com.gooddata.util.Validate.notEmpty;
import static java.util.Arrays.stream;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gooddata.util.Validate;

import org.springframework.web.util.UriTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Partial metadata export configuration structure.
 * Serialization only.
 */
@JsonTypeName("partialMDExport")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PartialMdExport {

    public static final String URI = "/gdc/md/{projectId}/maintenance/partialmdexport";
    public static final UriTemplate TEMPLATE = new UriTemplate(URI);

    private final Set<String> uris;
    private final boolean crossDataCenterExport;
    private final boolean exportAttributeProperties;

    /**
     * Creates new PartialMdExport. At least one uri should be given.
     * Sets crossDataCenterExport to false and exportAttributeProperties to true.
     *
     * @param mdObjectsUris list of uris to metadata objects which should be exported
     */
    public PartialMdExport(String... mdObjectsUris) {
        this(stream(mdObjectsUris).collect(Collectors.<String>toSet()));
    }

    /**
     * Creates new PartialMdExport. At least one uri should be given.
     * Sets crossDataCenterExport to false and exportAttributeProperties to true.
     *
     * @param mdObjectsUris list of uris to metadata objects which should be exported
     */
    public PartialMdExport(Set<String> mdObjectsUris) {
        this(true, false, mdObjectsUris);
    }

    /**
     * Creates new PartialMdExport. At least one uri should be given.
     *
     * @param exportAttributeProperties whether to add necessary data to be able to clone attribute properties
     * @param crossDataCenterExport whether export should be usable in any Data Center
     * @param mdObjectsUris list of uris to metadata objects which should be exported
     */
    public PartialMdExport(boolean exportAttributeProperties, boolean crossDataCenterExport, String... mdObjectsUris) {
        this(exportAttributeProperties, crossDataCenterExport, stream(mdObjectsUris).collect(Collectors.<String>toSet()));
    }

    /**
     * Creates new PartialMdExport.  At least one uri should be given.
     *
     * @param exportAttributeProperties whether to add necessary data to be able to clone attribute properties
     * @param crossDataCenterExport whether export should be usable in any Data Center
     * @param mdObjectsUris list of uris to metadata objects which should be exported
     */
    public PartialMdExport(boolean exportAttributeProperties, boolean crossDataCenterExport, Set<String> mdObjectsUris) {
        notEmpty(mdObjectsUris, "uris");
        this.uris = mdObjectsUris;
        this.crossDataCenterExport = crossDataCenterExport;
        this.exportAttributeProperties = exportAttributeProperties;
    }

    public Set<String> getUris() {
        return uris;
    }

    public boolean isCrossDataCenterExport() {
        return crossDataCenterExport;
    }

    public boolean isExportAttributeProperties() {
        return exportAttributeProperties;
    }
}
