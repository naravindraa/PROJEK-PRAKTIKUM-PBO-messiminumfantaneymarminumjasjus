package com.brocode.model;
public class Barber {
    private int id; private String name; private boolean active;
    public Barber(int id,String name,boolean active){this.id=id;this.name=name;this.active=active;}
    public int getId(){return id;} public String getName(){return name;} public boolean isActive(){return active;}
    public void setName(String v){name=v;} public void setActive(boolean v){active=v;}
    @Override public String toString(){return name+(active?"":" (Inactive)");}
}
