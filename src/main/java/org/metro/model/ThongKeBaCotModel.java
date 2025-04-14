package org.metro.model;

// ThongKeBaCotModel.java
public class ThongKeBaCotModel {
    private final String label;
    private final double cost;
    private final double revenue;

    public ThongKeBaCotModel(String label, double cost, double revenue) {
        this.label = label;
        this.cost = cost;
        this.revenue = revenue;
    }
    public String getLabel()   { return label; }
    public double getCost()    { return cost; }
    public double getRevenue(){ return revenue; }
    public double getProfit()  { return revenue - cost; }
}
