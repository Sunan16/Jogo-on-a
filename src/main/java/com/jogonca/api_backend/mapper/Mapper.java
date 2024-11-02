package com.jogonca.api_backend.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public class Mapper {

    private static ModelMapper mapper = new ModelMapper();

    public static <O, D> D parseObject(O origin, Class<D> destinClass) {
        return mapper.map(origin, destinClass);
    }

    public static <O, D> List<D> parseListObject(List<O> origin, Class<D> destinClass) {
        List<D> listDestinClass = new ArrayList<>();
        for (O o : origin) {
            listDestinClass.add(mapper.map(o, destinClass));
        }
        return listDestinClass;
    }

}
