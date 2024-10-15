package com.example.demo.service;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.GenericModel;
import com.example.demo.repository.GenericRepository;
import com.example.demo.utils.UUIDGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public interface GenericService<
        Model extends GenericModel, Repository extends GenericRepository<Model>> {

    ModelMapper getModelMapper();

    Repository getRepository();

    @Transactional
    default Model persist(Model model) {
        if (Objects.isNull(model.getUUID())) {
            model.setUUID(UUIDGenerator.generate());
        }
        return getRepository().save(model);
    }

    @Transactional
    default List<Model> persistAll(List<Model> models) {
        models.stream()
                .filter(model -> Objects.isNull(model.getUUID()))
                .forEach(model -> model.setUUID(UUIDGenerator.generate()));

        return getRepository().saveAll(models);
    }

    @Transactional(readOnly = true)
    default List<Model> findAll() {
        return getRepository().findAll();
    }

    @Transactional(readOnly = true)
    default Optional<Model> findById(Long id) {
        return getRepository().findById(id);
    }

    @Transactional(readOnly = true)
    default Page<Model> findPage(int page, int pageSize) {
        return getRepository().findAll(PageRequest.of(page, pageSize));
    }

    @Transactional(readOnly = true)
    default Model getById(Long id) {
        return getRepository().findById(id).orElseThrow(() -> new CustomException("Entity with id " + id + " was not found."));
    }

    @Transactional
    default void deleteById(Long id) {
        getRepository().deleteById(id);
    }

    @Transactional(readOnly = true)
    default Long count() {
        return getRepository().count();
    }

    @Transactional
    default void delete(Model model) {
        getRepository().delete(model);
    }
}
