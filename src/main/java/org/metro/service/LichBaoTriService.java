package org.metro.service;

import java.util.List;

import org.metro.DAO.LichBaoTriDAO;
import org.metro.model.LichBaoTriModel;
import org.metro.view.Panel.LichBaoTri;

public class LichBaoTriService {
    private LichBaoTriDAO lbtDAO = new LichBaoTriDAO();
    private LichBaoTri lbt;

    public LichBaoTriService(LichBaoTri lbt) {
        this.lbt = lbt;
    }

    public List<LichBaoTriModel> getAll() {
        return lbtDAO.selectAll();
    }

    public boolean insert(LichBaoTriModel lbtModel) {
        if (lbtDAO.insert(lbtModel) > 0) {
            lbt.getDsBaoTri().add(lbtModel);
            return true;
        }
        return false;
    }

    public int getIndexByMaBaoTri(int ma) {
        if (lbt == null || lbt.getDsBaoTri() == null) {
            return -1;
        }
        for (int i = 0; i < lbt.getDsBaoTri().size(); i++) {
            if (lbt.getDsBaoTri().get(i).getMabaotri() == ma) {
                return i;
            }
        }
        return -1;
    }

    public boolean update(LichBaoTriModel lbtModel) {
        if (lbtDAO.update(lbtModel) > 0) {
            lbt.getDsBaoTri().set(getIndexByMaBaoTri(lbtModel.getMabaotri()), lbtModel);
            return true;
        }
        return false;
    }

    public boolean delete(int mabaotri) {
        if (lbtDAO.delete(mabaotri) > 0)
            return true;
        return false;
    }

    public LichBaoTriModel getById(int mabaotri) {
        return lbtDAO.selectById(mabaotri);
    }

}
