package org.metro.util;


import org.metro.model.TauModel;
import org.metro.model.NhanVienModel;
import org.metro.model.LichTrinhModel;
import org.metro.model.TuyenDuongModel;
import org.metro.service.TauService;
import org.metro.service.NhanVienService;
import org.metro.service.LichTrinhService;
import org.metro.service.TuyenDuongService;

import javax.swing.*;
import java.util.List;
import java.util.function.Supplier;

public class ComboBoxUtil {
    /**
     * Phương thức chung để nạp dữ liệu vào DefaultComboBoxModel sử dụng Supplier
     */
    public static <T> void loadComboBoxData(DefaultComboBoxModel<T> model, Supplier<List<T>> dataSupplier) {
        model.removeAllElements();
        List<T> dataList = dataSupplier.get();
        for (T item : dataList) {
            model.addElement(item);
        }
    }

    /**
     * Phương thức chung để chọn phần tử trong ComboBox dựa trên điều kiện so khớp
     */
    @FunctionalInterface
    public interface ItemMatcher<T> {
        boolean matches(T item);
    }

    public static <T> void selectItemInComboBox(JComboBox<T> comboBox, DefaultComboBoxModel<T> model, ItemMatcher<T> matcher) {
        for (int i = 0; i < model.getSize(); i++) {
            T item = model.getElementAt(i);
            if (matcher.matches(item)) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    public static <T> Supplier<List<T>> getDataSupplier(Class<T> modelClass) {
        if (modelClass == NhanVienModel.class) {
            return () -> (List<T>) NhanVienService.getListnv();
        } else if (modelClass == TauModel.class) {
            return () -> (List<T>) TauService.getAll();
        } else if (modelClass == TuyenDuongModel.class) {
            return () -> (List<T>) TuyenDuongService.getAll();
        } else if (modelClass == LichTrinhModel.class) {
            return () -> (List<T>) LichTrinhService.getAll();
        }
        return () -> List.of();
    }
}