package com.brocode.model;
public class ServiceItem {
    private int id; private String name; private int durationMin; private double price;
    public ServiceItem(int id,String name,int durationMin,double price){this.id=id;this.name=name;this.durationMin=durationMin;this.price=price;}
    public int getId(){return id;} public String getName(){return name;} public int getDurationMin(){return durationMin;} public double getPrice(){return price;}
    public void setName(String v){name=v;} public void setDurationMin(int v){durationMin=v;} public void setPrice(double v){price=v;}
    @Override public String toString(){return name+" ("+durationMin+" min)";}
}
