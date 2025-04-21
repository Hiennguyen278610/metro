package org.metro.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.metro.DAO.LichBaoTriDAO;
import org.metro.model.LichBaoTriModel;
import org.metro.view.Panel.LichBaoTri;

public class LichBaoTriService {
    private LichBaoTriDAO lbtDAO = new LichBaoTriDAO();
    // private LichBaoTri lbt;
    private List<LichBaoTriModel> dsBaoTri = new ArrayList<>();

    public LichBaoTriService() {
        this.dsBaoTri = lbtDAO.selectAll();
    }

    // public LichBaoTriService(LichBaoTri lbt) {
    // this.lbt = lbt;
    // }

    // public List<LichBaoTriModel> getAll() {
    // return lbtDAO.selectAll();
    // }

    public boolean insert(LichBaoTriModel lbtModel) {
        if (lbtDAO.insert(lbtModel) > 0) {
            dsBaoTri.add(lbtModel);
            return true;
        }
        return false;
    }

    public int getIndexByMaBaoTri(int ma) {
        if (dsBaoTri == null) {
            return -1;
        }
        for (int i = 0; i < dsBaoTri.size(); i++) {
            if (dsBaoTri.get(i).getMabaotri() == ma) {
                return i;
            }
        }
        return -1;
    }

    public boolean update(LichBaoTriModel lbtModel) {
        if (lbtDAO.update(lbtModel) > 0) {
            dsBaoTri.set(getIndexByMaBaoTri(lbtModel.getMabaotri()), lbtModel);
            return true;
        }
        return false;
    }

    // String[] optSearch = { "Tất cả", "Mã bảo trì", "Mã tàu", "Ngày bảo trì",
    // "Trạng thái" };
    public List<LichBaoTriModel> search(String text, String type) {
        List<LichBaoTriModel> result = new ArrayList<>();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả":
                for (LichBaoTriModel lbt : dsBaoTri) {
                    if (String.valueOf(lbt.getMabaotri()).contains(text) ||
                            String.valueOf(lbt.getMatau()).contains(text) ||
                            lbt.getNgaybaotri().format(formatTime).contains(text) ||
                            lbt.getTrangthaibaotri().toLowerCase().contains(text)) {
                        result.add(lbt);
                    }
                }
                break;
            case "Mã bảo trì":
                for (LichBaoTriModel lbt : dsBaoTri) {
                    if (String.valueOf(lbt.getMabaotri()).contains(text)) {
                        result.add(lbt);
                    }
                }
                break;
            case "Mã tàu":
                for (LichBaoTriModel lbt : dsBaoTri) {
                    if (String.valueOf(lbt.getMatau()).contains(text)) {
                        result.add(lbt);
                    }
                }
                break;
            case "Ngày bảo trì":
                for (LichBaoTriModel lbt : dsBaoTri) {
                    if (lbt.getNgaybaotri().format(formatTime).contains(text)) {
                        result.add(lbt);
                    }
                }
                break;
            case "Trạng thái":
                for (LichBaoTriModel lbt : dsBaoTri) {
                    if (lbt.getTrangthaibaotri().toLowerCase().contains(text)) {
                        result.add(lbt);
                    }
                }
                break;
            default:
                System.out.println("Khong the tim kiem");
                break;
        }
        return result;
    }

    public int getNextID() {
        int check = lbtDAO.getAutoIncrement();
        return check != -1 ? check : -1;
        // return lbtDAO.getAutoIncrement() != -1 ? true : false;
    }

    public boolean delete(int mabaotri) {
        if (lbtDAO.delete(mabaotri) > 0)
            return true;
        return false;
    }

    public LichBaoTriModel getById(int mabaotri) {
        return lbtDAO.selectById(mabaotri);
    }

    public List<LichBaoTriModel> getDsBaoTri() {
        return dsBaoTri;
    }
}
