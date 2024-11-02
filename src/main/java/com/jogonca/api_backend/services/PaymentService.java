package com.jogonca.api_backend.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jogonca.api_backend.exceptions.NotFoundException;
import com.jogonca.api_backend.exceptions.OperationErrorException;
import com.jogonca.api_backend.mapper.Mapper;
import com.jogonca.api_backend.models.Payment;
import com.jogonca.api_backend.models.dtos.receiveDTOs.PaymentDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.PayRequestDTO;
import com.jogonca.api_backend.repositories.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {

    private String identifyLogClassName = Payment.class.getName();
    private Logger log = Logger.getLogger(Payment.class.getName());

    @Autowired
    private PaymentRepository repository;

    public PaymentService(@Autowired PaymentRepository repository) { }

    @Transactional
    public PaymentDTO findById(Long key) {
        var dto = repository.findByIdentifier(key).get();
        if(dto == null){
            new NotFoundException("Não encontrado com esse ID: " + key);
        }
        return Mapper.parseObject(dto, PaymentDTO.class);
    }

    @Transactional
    public PaymentDTO insert(PayRequestDTO send) {
        log.info(String.format("Service insert init: %s [%s]\n", identifyLogClassName, send));
        if (send == null) {
            nullObject();
            return null;
        }
        Payment entity = repository.save(Mapper.parseObject(send, Payment.class));
        var dto = Mapper.parseObject(entity, PaymentDTO.class);
        dto.setKey(entity.getId());
        log.info(String.format("Service was completed and successful to save: %s\n", entity));
        return dto;
    }

    @Transactional
    public PaymentDTO update(PayRequestDTO updated) {
        log.info(String.format("Service update init: %s [%s]\n", identifyLogClassName, updated));
        if (updated.getIdentifier() == null) {
            nullIdException();
            return null;
        }

        if (repository.findByIdentifier(updated.getIdentifier()) == null) {
            notFoundException(updated);
            return null;
        }

        Payment entity = repository.findByIdentifier(updated.getIdentifier()).get();
        PaymentDTO old = Mapper.parseObject(entity, PaymentDTO.class);
        PaymentDTO updatedNew = Mapper.parseObject(updated, PaymentDTO.class);
        updateData(entity, updatedNew);
        log.info(String.format("Service updated completed %s {[old:%s][new:%s]}\n", identifyLogClassName, old, updatedNew));
        return Mapper.parseObject(repository.save(entity), PaymentDTO.class);
    }

    protected void updateData(Payment entity, PaymentDTO i) {
        if(i.getStatus() != null){
            entity.setStatus(i.getStatus());
        }
        if(i.getValue() != null){
            entity.setValue(i.getValue());
        }
    }

    protected void nullIdException() {
        log.warning("Null ID");
        throw new OperationErrorException("Null ID");
    }

    protected void notFoundException(PayRequestDTO dto) {
        log.warning("Not found ID");
        throw new OperationErrorException(String.format("Não foi possível encontrar %s com o ID: %s - Erro SQL",
                identifyLogClassName, dto.getIdentifier()));
    }

    protected void notFoundException() {
        log.warning("Not found ID");
        throw new OperationErrorException(String.format("Não foi possível encontrar %s - Erro SQL",
                identifyLogClassName));
    }

    protected void nullObject() {
        log.warning("Null Object");
        throw new OperationErrorException("Para persistir os dados, coloque um objeto diferente de nulo!");
    }

}
