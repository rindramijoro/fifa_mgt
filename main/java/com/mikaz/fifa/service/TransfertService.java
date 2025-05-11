package com.mikaz.fifa.service;

import com.mikaz.fifa.dao.operations.TransfertCRUDOperations;
import com.mikaz.fifa.model.Transfert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransfertService {
    private final TransfertCRUDOperations transfertCRUDOperations;

    public TransfertService(TransfertCRUDOperations transfertCRUDOperations) {
        this.transfertCRUDOperations = transfertCRUDOperations;
    }

    public List<Transfert> getAllTransferts(int page, int size) {
        return transfertCRUDOperations.getAll(page, size);
    }
}
