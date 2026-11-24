package com.brocode.model;
import java.time.LocalDateTime;
public class QueueEntry {
    private int id; private String number; private Customer customer; private ServiceItem service; private Barber barber;
    private int durationSnapshot; private String status; private LocalDateTime registeredAt, startedAt, finishedAt;
    public QueueEntry(int id,String number,Customer customer,ServiceItem service,Barber barber,int durationSnapshot,String status,LocalDateTime registeredAt){
        this.id=id;this.number=number;this.customer=customer;this.service=service;this.barber=barber;this.durationSnapshot=durationSnapshot;this.status=status;this.registeredAt=registeredAt;
    }
    public int getId(){return id;} public String getNumber(){return number;} public Customer getCustomer(){return customer;}
    public ServiceItem getService(){return service;} public Barber getBarber(){return barber;} public int getDurationSnapshot(){return durationSnapshot;}
    public String getStatus(){return status;} public LocalDateTime getRegisteredAt(){return registeredAt;}
    public LocalDateTime getStartedAt(){return startedAt;} public LocalDateTime getFinishedAt(){return finishedAt;}
    public void setBarber(Barber v){barber=v;} public void setStatus(String v){status=v;}
    public void setStartedAt(LocalDateTime v){startedAt=v;} public void setFinishedAt(LocalDateTime v){finishedAt=v;}
}
