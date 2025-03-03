package org.metro.service;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class IntegratedSearch extends JPanel {
    public JTextField txtSearchForm;
    public JComboBox<String> cbxChoose;
    public JButton btnReset;

    public IntegratedSearch(String[] options) {
        initComponents(options);
    }

    private void initComponents(String[] options) {
        this.setLayout(new BorderLayout(10, 0));
        this.setBackground(Color.WHITE);

        cbxChoose = new JComboBox<>(options);
        cbxChoose.setPreferredSize(new Dimension(100, 30));

        txtSearchForm = new JTextField();
        txtSearchForm.setPreferredSize(new Dimension(200, 30));

        btnReset = new JButton();
        URL iconUrl = getClass().getResource("/svg/refresh.svg");
        if (iconUrl != null) {
            btnReset.setIcon(new FlatSVGIcon(iconUrl).derive(20, 20));
        }
        btnReset.setPreferredSize(new Dimension(30, 30));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);

        cbxChoose.setBackground(new Color(250, 250, 250));
        txtSearchForm.setBackground(new Color(250, 250, 250));
        btnReset.setBackground(new Color(130, 190, 223));


        searchPanel.add(cbxChoose);
        searchPanel.add(txtSearchForm);
        searchPanel.add(btnReset);

        this.add(searchPanel, BorderLayout.WEST);
    }

}
