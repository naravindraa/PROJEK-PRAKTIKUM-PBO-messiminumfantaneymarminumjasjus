package com.brocode.service;

import com.brocode.model.*;
import java.time.LocalDateTime;
import java.util.*;

public class QueueService {
    private final DemoDataStore db;
    public QueueService(DemoDataStore db){this.db=db;}
    public Barber recommendBarber(){
        Map<Integer,Integer> load=new HashMap<>();
        for(Barber b:db.barbers) if(b.isActive()) load.put(b.getId(),0);
        for(QueueEntry q:db.queues) if(q.getBarber()!=null && load.containsKey(q.getBarber().getId())
                && (q.getStatus().equals("Waiting")||q.getStatus().equals("In Progress")))
            load.put(q.getBarber().getId(),load.get(q.getBarber().getId())+q.getDurationSnapshot());
        return db.barbers.stream().filter(Barber::isActive)
            .min(Comparator.comparingInt(b->load.getOrDefault(b.getId(),0))).orElse(null);
    }
    public int estimatedWait(Barber barber){
        if(barber==null)return 0;
        return db.queues.stream().filter(q->q.getBarber()!=null&&q.getBarber().getId()==barber.getId())
            .filter(q->q.getStatus().equals("Waiting")||q.getStatus().equals("In Progress"))
            .mapToInt(QueueEntry::getDurationSnapshot).sum();
    }
    public void changeStatus(QueueEntry q,String next){
        String old=q.getStatus(); q.setStatus(next);
        if(next.equals("In Progress"))q.setStartedAt(LocalDateTime.now());
        if(next.equals("Completed")||next.equals("Cancelled"))q.setFinishedAt(LocalDateTime.now());
        db.history.add(new QueueHistory(q.getNumber(),old,next,LocalDateTime.now()));
    }
    public String nextQueueNumber(){
        String date=java.time.LocalDate.now().toString().replace("-","");
        long count=db.queues.stream().filter(q->q.getNumber().startsWith(date)).count()+1;
        return date+"-A"+String.format("%03d",count);
    }
    public Map<Integer,Long> peakHours(){
        Map<Integer,Long> result=new TreeMap<>();
        db.queues.forEach(q->result.merge(q.getRegisteredAt().getHour(),1L,Long::sum));
        return result;
    }
}
