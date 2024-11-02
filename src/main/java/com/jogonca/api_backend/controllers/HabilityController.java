package com.jogonca.api_backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jogonca.api_backend.interfaces.AbstractCrudController;
import com.jogonca.api_backend.models.Hability;
import com.jogonca.api_backend.models.dtos.receiveDTOs.HabilityDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.HabilitySendDTO;
import com.jogonca.api_backend.services.HabilityService;

@RestController
@RequestMapping("/hability")
public class HabilityController extends AbstractCrudController<HabilityDTO, Hability, HabilitySendDTO, String> {

    public HabilityController(HabilityService service) {
        super(service);
    }

}
