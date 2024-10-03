package com.example.job.dto.mapper;

public interface Mapper<E, D> {
    E toEntity(D dto);
    D toDto(E entity);
}
