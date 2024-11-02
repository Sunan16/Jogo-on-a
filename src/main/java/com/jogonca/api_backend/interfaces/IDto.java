package com.jogonca.api_backend.interfaces;

import java.io.Serializable;

public interface IDto extends Serializable {

    // o atributo 'key' é substituido por 'id' por conta do conflito com HATEOAS

    public Long getKey();

    public void setKey(Long key);
    
}
