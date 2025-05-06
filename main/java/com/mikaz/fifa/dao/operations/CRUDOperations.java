package com.mikaz.fifa.dao.operations;

import java.util.List;

public interface CRUDOperations<E> {
    List<E> getAll(Integer page, Integer size);
    List<E> saveAll(List<E> entities);
}
