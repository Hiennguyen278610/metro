package org.metro.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class DateTimeUtil {
    // Option thời gian fix cứng như zalo :))))
    public static String[] timeOptions = {
            "05:00", "05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45",
            "07:00", "07:15", "07:30", "07:45", "08:00", "08:15", "08:30", "08:45",
            "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45",
            "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45",
            "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45",
            "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45",
            "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45",
            "19:00", "19:15", "19:30", "19:45", "20:00", "20:15", "20:30", "20:45",
            "21:00", "21:15", "21:30", "21:45", "22:00", "22:15", "22:30", "22:45"
    };

    // Lấy thời gian từ JDateChooser và JComboBox
    public static LocalDateTime getTimeComponents(JDateChooser dateChooser, JComboBox<String> timeComboBox) {
        Date selectedDate = dateChooser.getDate();
        if (selectedDate == null) throw new IllegalArgumentException("Vui lòng chọn ngày");
        LocalDate date = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String timeString = (String) timeComboBox.getSelectedItem();
        String[] parts = timeString.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return LocalDateTime.of(date, LocalTime.of(hour, minute, 0));
    }

    // Thiết lập thời gian cho JDateChooser và JComboBox
    public static void setDateTime(JDateChooser dateChooser, JComboBox<String> timeComboBox, LocalDateTime dateTime) {
        if(dateTime == null) {dateChooser.setDate(new Date());timeComboBox.setSelectedItem("08:00");return;}
        Date date = Date.from(dateTime.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        dateChooser.setDate(date);
        String timeStr = String.format("%02d:%02d", dateTime.getHour(), dateTime.getMinute());
        boolean found = false;
        for(String option: timeOptions)
            if(option.equals(timeStr)) {
                timeComboBox.setSelectedItem(option);
                found = true;
                break;
            }
        if(!found) timeComboBox.setSelectedIndex(0);
    }
}
