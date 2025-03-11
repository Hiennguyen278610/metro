package org.metro.model;

public class TramConModal {
    private int matramcon;
    private int matuyen;
    private String tentramcon;
    private String diachitramcon;

    public TramConModal(int matramcon, int matuyen, String tentramcon, String diachitramcon) {
        this.matramcon = matramcon;
        this.matuyen = matuyen;
        this.tentramcon = tentramcon;
        this.diachitramcon = diachitramcon;
    }

    public int getMatramcon() { return matramcon; }

    public int getMatuyen() { return matuyen; }

    public String getTentramcon() { return tentramcon; }

    public String getDiachitramcon() { return diachitramcon; }

    public void setMatramcon(int matramcon) { this.matramcon = matramcon; }

    public void setMatuyen(int matuyen) { this.matuyen = matuyen; }

    public void setTentramcon(String tentramcon) { this.tentramcon = tentramcon; }

    public void setDiachitramcon(String diachitramcon) { this.diachitramcon = diachitramcon; }
}
