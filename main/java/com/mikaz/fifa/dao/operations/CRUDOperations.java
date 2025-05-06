package com.mikaz.fifa.dao.operations;

import com.mikaz.fifa.model.Season;
import com.mikaz.fifa.model.Status;

import java.util.List;

public interface CRUDOperations<E> {
    List<E> getAll(Integer page, Integer size);
    List<E> saveAll(List<E> entities);
    Season updateStatus(Integer seasonStart, Status newStatus);
}
