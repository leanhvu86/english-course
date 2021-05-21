package com.selflearning.englishcourses.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface BaseCurdService<T, ID extends Serializable> {

    T get(UUID id);

    void save(T obj);

    default void saveAll(List<T> list) {
        throw new RuntimeException("Not support yet.");
    }

    default void delete(T obj) {
        throw new RuntimeException("Not support yet.");
    }

    default void deleteAll(List<T> list) {
        throw new RuntimeException("Not support yet.");
    }

    Page<T> findAll(Pageable pageable);

}
