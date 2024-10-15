package com.example.demo.controller;

import com.example.demo.dto.ModelDTO;
import com.example.demo.model.GenericModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenericController<Model extends GenericModel, DTO extends ModelDTO> {

    ModelMapper getModelMapper();

    default <T> ResponseEntity<T> okResponse(Object src, TypeToken<T> typeToken) {
        return ResponseEntity.ok(getModelMapper().map(src, typeToken.getType()));
    }

    Class<DTO> dtoClass();

    default DTO toDTO(Model src) {
        return getModelMapper().map(src, dtoClass());
    }

    default ResponseEntity<DTO> okResponse(Model src) {
        return ResponseEntity.ok(toDTO(src));
    }

    default ResponseEntity<List<DTO>> okResponse(List<Model> src) {
        return ResponseEntity.ok(src.stream().map(this::toDTO).toList());
    }

    default ResponseEntity<Page<DTO>> okResponse(Page<Model> src) {
        return ResponseEntity.ok(src.map(this::toDTO));
    }
}
