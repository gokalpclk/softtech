package com.baydijital.softtech.app.gen.service;

import com.baydijital.softtech.app.gen.entity.BaseAdditionalFields;
import com.baydijital.softtech.app.gen.entity.BaseEntity;
import com.baydijital.softtech.app.gen.enums.GenErrorMessage;
import com.baydijital.softtech.app.gen.exceptions.ItemNotFoundException;
import com.baydijital.softtech.app.sec.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Gokalp on 7/23/22
 */
@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity, D extends JpaRepository> {

    private static  final Integer DEFAULT_SIZE = 10;
    private static  final Integer DEFAULT_PAGE = 0;
    private final D dao;

    private AuthenticationService authenticationService;

    @Autowired
    public void setAuthenticationService(@Lazy AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public List<E> findAll() {
        return dao.findAll();
    }

    public Optional<E> findById(Long id) {
        return dao.findById(id);
    }

    public E save(E entity) {
        setAdditional(entity);
        entity = (E) dao.save(entity);
        return entity;
    }

    private void setAdditional(E entity) {
        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        Long currentCustomerId = getCurrentCustomerId();

        if (baseAdditionalFields == null) {
            baseAdditionalFields = new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }
        if (entity.getId() == null) {
            baseAdditionalFields.setCreateDate(new Date());
            baseAdditionalFields.setCreatedBy(currentCustomerId);
        }

        baseAdditionalFields.setUpdateDate(new Date());
        baseAdditionalFields.setUpdatedBy(currentCustomerId);
    }

    public Long getCurrentCustomerId() {
        Long currentCustomerId = authenticationService.getCurrentCustomerId();
        return currentCustomerId;
    }




    public E getByIdWithControl(Long id) {
        Optional<E> entityOptional = findById(id);

        E entity;

        if (entityOptional.isPresent()) {
            entity = entityOptional.get();
        } else {
            throw new ItemNotFoundException(GenErrorMessage.ITEM_NOTFOUND);
        }

        return entity;
    }
    public void delete(E entity) {
        dao.delete(entity);
    }

    public boolean existsById(Long id) {
        return dao.existsById(id);
    }

    public D getDao() {
        return dao;
    }

    protected static Integer getSize(Optional<Integer> sizeOptional) {

        Integer size = DEFAULT_SIZE;
        if (sizeOptional.isPresent()) {
            size = sizeOptional.get();
        }
        return size;
    }

    protected static Integer getPage(Optional<Integer> pageOptional) {

        Integer page = DEFAULT_PAGE;
        if (pageOptional.isPresent()) {
            page = pageOptional.get();
        }
        return page;
    }

    protected static PageRequest getPageRequest(Optional<Integer> pageOptional, Optional<Integer> sizeOptional) {
        Integer page = getPage(pageOptional);
        Integer size = getSize(sizeOptional);

        PageRequest pageRequest = PageRequest.of(page, size);
        return pageRequest;
    }
}
