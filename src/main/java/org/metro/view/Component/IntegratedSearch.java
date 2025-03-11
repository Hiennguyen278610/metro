package org.metro.view.Component;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import mdlaf.components.button.MaterialButtonUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.text.AttributedCharacterIterator;

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
        btnReset.setOpaque(true);
        btnReset.setBackground(Color.CYAN);
        btnReset.setContentAreaFilled(false);
        btnReset.paintComponents(getGraphics());
        URL iconUrl = getClass().getResource("/svg/refresh.svg");
        if (iconUrl != null) {
            btnReset.setIcon(new FlatSVGIcon(iconUrl).derive(20, 20));
        }
        btnReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource() == btnReset) btnReset.setBackground(Color.BLUE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource() == btnReset) btnReset.setBackground(Color.CYAN);
            }
        });
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
        this.repaint();
        this.revalidate();
    }

    public JTextField getTxtSearchForm() {
        return txtSearchForm;
    }

    public void setTxtSearchForm(JTextField txtSearchForm) {
        this.txtSearchForm = txtSearchForm;
    }

    public JComboBox<String> getCbxChoose() {
        return cbxChoose;
    }

    public void setCbxChoose(JComboBox<String> cbxChoose) {
        this.cbxChoose = cbxChoose;
    }

    public JButton getBtnReset() {
        return btnReset;
    }

    public void setBtnReset(JButton btnReset) {
        this.btnReset = btnReset;
    }

    //getter va setter
    
}
