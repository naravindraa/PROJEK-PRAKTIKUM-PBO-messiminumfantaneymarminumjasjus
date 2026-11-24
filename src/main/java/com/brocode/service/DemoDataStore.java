package com.brocode.service;

import com.brocode.model.*;
import java.time.LocalDateTime;
import java.util.*;

public class DemoDataStore {
    public final List<Customer> customers = new ArrayList<>();
    public final List<ServiceItem> services = new ArrayList<>();
    public final List<Barber> barbers = new ArrayList<>();
    public final List<QueueEntry> queues = new ArrayList<>();
    public final List<QueueHistory> history = new ArrayList<>();
    private int customerId=4, serviceId=4, barberId=4, queueId=6;
    public DemoDataStore() {
        customers.add(new Customer(1,"Farrel","081234567801"));
        customers.add(new Customer(2,"Rizky","081234567802"));
        customers.add(new Customer(3,"Dimas","081234567803"));
        services.add(new ServiceItem(1,"Haircut",30,35000));
        services.add(new ServiceItem(2,"Wash",15,15000));
        services.add(new ServiceItem(3,"Haircut + Wash",45,45000));
        barbers.add(new Barber(1,"Andi",true)); barbers.add(new Barber(2,"Budi",true)); barbers.add(new Barber(3,"Raka",true));
        LocalDateTime now=LocalDateTime.now().withSecond(0).withNano(0);
        addSeed(1,"20261118-A001",customers.get(0),services.get(0),barbers.get(0),30,"In Progress",now.minusMinutes(15));
        addSeed(2,"20261118-A002",customers.get(1),services.get(0),barbers.get(1),30,"Waiting",now.minusMinutes(8));
        addSeed(3,"20261118-A003",customers.get(2),services.get(1),barbers.get(0),15,"Waiting",now.minusMinutes(4));
        addSeed(4,"20261118-A004",customers.get(0),services.get(2),barbers.get(2),45,"Completed",now.minusHours(2));
        addSeed(5,"20261118-A005",customers.get(1),services.get(0),barbers.get(1),30,"Cancelled",now.minusHours(1));
    }
    private void addSeed(int id,String no,Customer c,ServiceItem s,Barber b,int d,String st,LocalDateTime at){
        QueueEntry q=new QueueEntry(id,no,c,s,b,d,st,at); if(st.equals("In Progress"))q.setStartedAt(at);
        if(st.equals("Completed")){q.setStartedAt(at);q.setFinishedAt(at.plusMinutes(d));}
        queues.add(q); history.add(new QueueHistory(no,null,st,at));
    }
    public int nextCustomerId(){return customerId++;} public int nextServiceId(){return serviceId++;}
    public int nextBarberId(){return barberId++;} public int nextQueueId(){return queueId++;}
}
