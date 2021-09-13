package com.faez.movie.services;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

@Service
public class JacksonFilterService {

    public MappingJacksonValue filterJsonResponse(Object responseObject, String filterName, String... fields) {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept(fields);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter(filterName, simpleBeanPropertyFilter).setFailOnUnknownId(false);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(responseObject);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;

    }
}
