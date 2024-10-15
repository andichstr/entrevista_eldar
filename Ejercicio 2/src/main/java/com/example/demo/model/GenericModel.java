package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

/**
 * This defines the standard models of the application.
 * All models are supposed to have id and UUID, and manage their creation timestamp and logical deletion.
 */
@MappedSuperclass
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    protected Date timestamp;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date deletedAt;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;

    @NonNull
    protected String UUID;
}
