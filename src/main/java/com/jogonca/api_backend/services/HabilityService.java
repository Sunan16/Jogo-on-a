package com.jogonca.api_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jogonca.api_backend.interfaces.AbstractCrudService;
import com.jogonca.api_backend.models.Hability;
import com.jogonca.api_backend.models.dtos.receiveDTOs.HabilityDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.HabilitySendDTO;
import com.jogonca.api_backend.repositories.HabilityRepository;

@Service
public class HabilityService extends AbstractCrudService<HabilityDTO, Hability, HabilitySendDTO, String>{

    public HabilityService(@Autowired HabilityRepository repository) {
        super(Hability.class.getName(), repository, Hability.class, HabilityDTO.class);
    }

    protected void updateData(Hability entity, HabilityDTO i) {
        if(i.getName() != null){
            entity.setName(i.getName());
        }
        if(i.getDescription() != null){
            entity.setDescription(i.getDescription());
        }
        if(i.getUrl() != null){
            entity.setUrl(i.getUrl());
        }
    }

}
