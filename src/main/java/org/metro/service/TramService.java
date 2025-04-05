package org.metro.service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import org.metro.DAO.TramDAO;
import org.metro.model.TramModel;

public class TramService {
    private TramDAO tramDAO = new TramDAO();
    private List<TramModel> dsTram = new ArrayList<>();

    public boolean insert(TramModel tramModel) {
        if (tramDAO.insert(tramModel) > 0) {
            dsTram.add(tramModel);
            return true;
        }
        return false;
    }

    public boolean delete(int matram) {
        if (tramDAO.delete(matram) > 0) {
            return true;
        }
        return false;

    }

    public int getIndexByMaTram(int matram) {
        if (dsTram == null) {
            return -1;
        }
        for (int i = 0; i < dsTram.size(); i++) {
            if (dsTram.get(i).getMatram() == matram) {
                return i;
            }
        }
        return -1;

    }

    public boolean update(TramModel tramModel) {
        if (tramDAO.update(tramModel) > 0) {
            dsTram.set(getIndexByMaTram(tramModel.getMatram()), tramModel);
            return true;
        }
        return false;
    }

    public List<TramModel> getDsTram() {
        return dsTram;
    }

    public List<TramModel> search(String text, String type) {
        List<TramModel> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả":
                for (TramModel tramModel : dsTram) {
                    if (String.valueOf(tramModel.getMatram()).contains(text) || tramModel.getTentram().contains(text)
                            || tramModel.getDiachi().contains(text)) {
                        result.add(tramModel);
                    }
                }
                break;

            case "Mã trạm":
                for (TramModel tramModel : dsTram) {
                    if (String.valueOf(tramModel.getMatram()).contains(text)) {
                        result.add(tramModel);
                    }
                }
                break;

            case "Tên trạm":
                for (TramModel tramModel : dsTram) {
                    if (tramModel.getTentram().contains(text)) {
                        result.add(tramModel);
                    }
                }
                break;

            case "Địa chỉ":
                for (TramModel tramModel : dsTram) {
                    if (tramModel.getDiachi().contains(text)) {
                        result.add(tramModel);
                    }
                }
                break;

            default:
                System.out.println("Khong the tim kiem");
                break;
        }
        return dsTram;
    }

    public int getNextID() {
        int check = tramDAO.getAutoIncrement();
        return check != -1 ? check : -1;
    }

    public void setDsTram(List<TramModel> dsTram) {
        this.dsTram = dsTram;
    }

    public TramService() {
        this.dsTram = tramDAO.selectAll();
    }
}
