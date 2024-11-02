package com.jogonca.api_backend.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.jogonca.api_backend.exceptions.OperationErrorException;
import com.jogonca.api_backend.mapper.Mapper;

import jakarta.transaction.Transactional;

public abstract class AbstractCrudService<Dto extends AbstractDTOHateoas<Dto>, E extends IEntity, SendDto extends IDtoSend<TypeIdentifier>, TypeIdentifier> {

    protected String identifyLogClassName;
    protected Logger log;
    protected IJpaRepositoryIdentifier<E, Long, TypeIdentifier> repository;
    protected Class<E> entityClass;
    protected Class<Dto> dtoClass;

    public AbstractCrudService(String identifyLogClassName, IJpaRepositoryIdentifier<E, Long, TypeIdentifier> repository,
                               Class<E> entityClass, Class<Dto> dtoClass) {
        this.identifyLogClassName = identifyLogClassName;
        this.log = Logger.getLogger(identifyLogClassName);
        this.repository = repository;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Transactional
    public Dto insert(SendDto send) {
        log.info(String.format("Service insert init: %s [%s]", identifyLogClassName, send));
        if (send == null) {
            log.warning("Null object provided for insertion.");
            throw new OperationErrorException("Para persistir os dados, coloque um objeto diferente de nulo!");
        }

        E entity = repository.save(Mapper.parseObject(send, entityClass));
        Dto dto = Mapper.parseObject(entity, dtoClass);
        dto.setKey(entity.getId());
        log.info(String.format("Service successfully saved: %s", entity));
        return dto;
    }

    @Transactional
    public void delete(Dto send) {
        log.info(String.format("Service delete init: %s [%s]", identifyLogClassName, send));
        if (send == null || send.getKey() == null) {
            log.warning("Null ID provided for deletion.");
            throw new OperationErrorException("Null ID");
        }

        Optional<E> entityOpt = repository.findById(send.getKey());
        if (entityOpt.isPresent()) {
            repository.delete(entityOpt.get());
            log.info(String.format("Service completed deletion: [%s]", send));
        } else {
            log.warning("Entity not found for deletion.");
            throw new OperationErrorException(String.format("Não foi possível encontrar %s com o ID: %s", identifyLogClassName, send.getKey()));
        }
    }

    @Transactional
    public List<Dto> findAll() {
        log.info(String.format("Service find all init: %s", identifyLogClassName));
        List<Dto> dtoList = new ArrayList<>();
        repository.findAll().forEach(e -> {
            Dto d = Mapper.parseObject(e, dtoClass);
            d.setKey(e.getId());
            dtoList.add(d);
        });
        log.info(String.format("Service completed searching for all: %s", identifyLogClassName));
        return dtoList;
    }

    @Transactional
    public Dto findById(Long id) {
        if (id == null) {
            log.warning("Null ID provided for findById.");
            throw new OperationErrorException("Null ID");
        }

        log.info(String.format("Find by %s ID init: %s", identifyLogClassName, id));
        Optional<E> entityOpt = repository.findById(id);
        if (entityOpt.isPresent()) {
            Dto dto = Mapper.parseObject(entityOpt.get(), dtoClass);
            dto.setKey(entityOpt.get().getId());
            log.info("Service completed locating by ID.");
            return dto;
        } else {
            log.warning("Entity not found by ID.");
            throw new OperationErrorException(String.format("Não foi possível encontrar %s com o ID: %s", identifyLogClassName, id));
        }
    }

    @Transactional
    public Dto update(SendDto updated) {
        log.info(String.format("Service update init: %s [%s]", identifyLogClassName, updated));
        if (updated.getIdentifier() == null) {
            log.warning("Null identifier provided for update.");
            throw new OperationErrorException("Null ID");
        }

        Optional<E> entityOpt = repository.findByIdentifier(updated.getIdentifier());
        if (entityOpt.isEmpty()) {
            log.warning("Entity not found for update.");
            throw new OperationErrorException(String.format("Não foi possível encontrar %s com o ID: %s", identifyLogClassName, updated.getIdentifier()));
        }

        E entity = entityOpt.get();
        Dto oldDto = Mapper.parseObject(entity, dtoClass);
        Dto updatedDto = Mapper.parseObject(updated, dtoClass);
        updateData(entity, updatedDto);
        log.info(String.format("Service update completed: %s {[old:%s][new:%s]}", identifyLogClassName, oldDto, updatedDto));
        return Mapper.parseObject(repository.save(entity), dtoClass);
    }

    protected abstract void updateData(E entity, Dto dto);
}
