package org.metro.service;

import java.util.List;

import org.metro.DAO.LichBaoTriDAO;
import org.metro.model.LichBaoTriModel;

public class LichBaoTriService {
    private LichBaoTriDAO lbtDAO = new LichBaoTriDAO();

    public List<LichBaoTriModel> getAll() {
        return lbtDAO.selectAll();
    }
}
