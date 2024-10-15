package com.example.demo.dto;

import com.example.demo.model.GenericModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ModelDTO<Model extends GenericModel> {
    @NonNull
    private Long id;
    @NonNull
    private Long UUID;
}
