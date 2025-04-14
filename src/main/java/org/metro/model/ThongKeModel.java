package org.metro.model;

public class ThongKeModel {
    private String label;
    private long value;

    public ThongKeModel(){
    }
    public ThongKeModel(String label, long value) {
        this.label = label;
        this.value = value;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public long getValue(){
        return value;
    }
    public void setValue(long value){
        this.value = value;
    }
}
