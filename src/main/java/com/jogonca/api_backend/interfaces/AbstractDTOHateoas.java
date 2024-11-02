package com.jogonca.api_backend.interfaces;

import org.springframework.hateoas.RepresentationModel;

public abstract class AbstractDTOHateoas<C extends RepresentationModel<C>> extends RepresentationModel<C> implements IDto {

    public abstract Long getKey() ;

    public abstract void setKey(Long key) ;
    
}
