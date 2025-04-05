package org.metro.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import org.metro.view.Dialog.TramDialog;
import org.metro.view.Panel.Tram;

public class TramController implements MouseListener, ItemListener {
    private Tram tram;
    private TramDialog tramDialog;
    private JFrame parent;

    public TramController(Tram tram, TramDialog tramDialog) {
        this.tram = tram;
        this.tramDialog = tramDialog;
        parent = (JFrame) SwingUtilities.getWindowAncestor(tram);
    }

    public TramController(Tram tram) {
        this.tram = tram;
        this.tramDialog = null;
        parent = (JFrame) SwingUtilities.getWindowAncestor(tram);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'itemStateChanged'");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == tram.getMainFunction().btn.get("create")) {
            tramDialog = new TramDialog(parent, "Thêm trạm", "create", tram);
        } else if (e.getSource() == tram.getMainFunction().btn.get("update")) {

        } else if (e.getSource() == tram.getMainFunction().btn.get("delete")) {

        } else if (tramDialog != null && e.getSource() == tramDialog.getBtnExit()) {
            tramDialog.dispose();
        } else if (tramDialog != null && e.getSource() == tramDialog.getBtnAdd() && tramDialog.validation()) {
            int matram = tram.getTramService().getNextID();
            String tentram = tramDialog.getTenField();
            String diachi = tramDialog.getAddressField();
            int x = Integer.parseInt(tramDialog.getxField());
            int y = Integer.parseInt(tramDialog.getyField());
        } else if (tramDialog != null && e.getSource() == tramDialog.getBtnUpdate()) {

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

}
