package com.brocode.service;

import com.brocode.config.DatabaseConfig;
import com.brocode.model.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Penyimpan data aplikasi.
 * Jika MySQL tersedia (Laragon aktif), semua data dibaca dari dan ditulis ke database brocode_queue.
 * Jika database/tabel belum ada, otomatis dibuat dan diisi data awal (seed).
 */
public class DemoDataStore {
    public final List<Customer> customers = new ArrayList<>();
    public final List<ServiceItem> services = new ArrayList<>();
    public final List<Barber> barbers = new ArrayList<>();
    public final List<QueueEntry> queues = new ArrayList<>();
    public final List<QueueHistory> history = new ArrayList<>();
    private int customerId=9, serviceId=6, barberId=6, queueId=7;
    private boolean dbMode;

    public DemoDataStore() {
        try {
            ensureDatabaseReady();
            try (Connection c = DatabaseConfig.getConnection()) {
                dbMode = true;
                reload();
            }
        } catch (Exception e) {
            dbMode = false;
            System.err.println("[Brocode] MySQL tidak tersedia, memakai data demo. Penyebab: " + e.getMessage());
            customers.clear(); services.clear(); barbers.clear(); queues.clear(); history.clear();
            seedDemo();
        }
    }

    public boolean isDatabaseMode() { return dbMode; }

    /** Memastikan database brocode_queue, tabel, dan data awal tersedia di MySQL. */
    private void ensureDatabaseReady() throws SQLException {
        String serverUrl = "jdbc:mysql://localhost:3306/?serverTimezone=Asia/Jakarta&useSSL=false&allowPublicKeyRetrieval=true";
        String user = System.getenv().getOrDefault("BROCODE_DB_USER", "root");
        String pass = System.getenv().getOrDefault("BROCODE_DB_PASSWORD", "");
        try (Connection rootConn = DriverManager.getConnection(serverUrl, user, pass);
             Statement st = rootConn.createStatement()) {
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS brocode_queue");
        }

        try (Connection c = DatabaseConfig.getConnection();
             Statement st = c.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
                    + "id BIGINT PRIMARY KEY AUTO_INCREMENT,"
                    + "username VARCHAR(50) NOT NULL UNIQUE,"
                    + "password_hash VARCHAR(255) NOT NULL)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS customers ("
                    + "id BIGINT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(100) NOT NULL,"
                    + "phone VARCHAR(25))");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS services ("
                    + "id BIGINT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(100) NOT NULL,"
                    + "duration_min INT NOT NULL,"
                    + "price DECIMAL(12,2) NOT NULL DEFAULT 0)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS barbers ("
                    + "id BIGINT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(100) NOT NULL,"
                    + "is_active BOOLEAN NOT NULL DEFAULT TRUE)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS queues ("
                    + "id BIGINT PRIMARY KEY AUTO_INCREMENT,"
                    + "queue_number VARCHAR(20) NOT NULL UNIQUE,"
                    + "customer_id BIGINT NOT NULL,"
                    + "service_id BIGINT NOT NULL,"
                    + "barber_id BIGINT NULL,"
                    + "duration_snapshot INT NOT NULL,"
                    + "status ENUM('Waiting','In Progress','Completed','Cancelled') NOT NULL DEFAULT 'Waiting',"
                    + "registered_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "started_at DATETIME NULL,"
                    + "finished_at DATETIME NULL,"
                    + "CONSTRAINT fk_queue_customer FOREIGN KEY (customer_id) REFERENCES customers(id),"
                    + "CONSTRAINT fk_queue_service FOREIGN KEY (service_id) REFERENCES services(id),"
                    + "CONSTRAINT fk_queue_barber FOREIGN KEY (barber_id) REFERENCES barbers(id))");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS queue_history ("
                    + "id BIGINT PRIMARY KEY AUTO_INCREMENT,"
                    + "queue_id BIGINT NOT NULL,"
                    + "old_status VARCHAR(30),"
                    + "new_status VARCHAR(30) NOT NULL,"
                    + "changed_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "CONSTRAINT fk_history_queue FOREIGN KEY (queue_id) REFERENCES queues(id))");

            // Seed data awal otomatis jika tabel master masih kosong
            try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM users")) {
                if (rs.next() && rs.getInt(1) == 0) {
                    st.executeUpdate("INSERT INTO users (username, password_hash) VALUES ('admin', 'admin123')");
                }
            }
            boolean emptyMaster = false;
            try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM barbers")) {
                if (rs.next() && rs.getInt(1) == 0) emptyMaster = true;
            }
            if (emptyMaster) {
                st.executeUpdate("INSERT INTO customers (name, phone) VALUES "
                        + "('Farrel','081234567801'),('Rizky','081234567802'),('Dimas','081234567803'),"
                        + "('Naufal','081234567804'),('Bagas','081234567805'),('Reza','081234567806'),"
                        + "('Alif','081234567807'),('Gilang','081234567808')");
                st.executeUpdate("INSERT INTO services (name, duration_min, price) VALUES "
                        + "('Haircut',30,35000),('Wash',15,15000),('Haircut + Wash',45,45000),"
                        + "('Beard Trim',20,25000),('Full Grooming',60,65000)");
                st.executeUpdate("INSERT INTO barbers (name, is_active) VALUES "
                        + "('Andi',TRUE),('Budi',TRUE),('Raka',TRUE),('Fajar',TRUE),('Ilham',FALSE)");
                st.executeUpdate("INSERT INTO queues (queue_number, customer_id, service_id, barber_id, duration_snapshot, status, registered_at, started_at, finished_at) VALUES "
                        + "(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),'-A001'),1,1,1,30,'In Progress',NOW()-INTERVAL 15 MINUTE,NOW()-INTERVAL 15 MINUTE,NULL),"
                        + "(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),'-A002'),2,3,2,45,'In Progress',NOW()-INTERVAL 10 MINUTE,NOW()-INTERVAL 10 MINUTE,NULL),"
                        + "(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),'-A003'),3,2,3,15,'Waiting',NOW()-INTERVAL 6 MINUTE,NULL,NULL),"
                        + "(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),'-A004'),4,1,4,30,'Waiting',NOW()-INTERVAL 3 MINUTE,NULL,NULL),"
                        + "(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),'-A005'),5,3,3,45,'Completed',NOW()-INTERVAL 2 HOUR,NOW()-INTERVAL 2 HOUR,NOW()-INTERVAL 75 MINUTE),"
                        + "(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),'-A006'),6,1,2,30,'Cancelled',NOW()-INTERVAL 1 HOUR,NULL,NOW()-INTERVAL 50 MINUTE)");
                st.executeUpdate("INSERT INTO queue_history (queue_id, old_status, new_status, changed_at) "
                        + "SELECT id, NULL, status, registered_at FROM queues");
            }
        }
    }

    // ------------------------------------------------------------------ data demo (fallback)
    private void seedDemo() {
        customers.add(new Customer(1,"Farrel","081234567801"));
        customers.add(new Customer(2,"Rizky","081234567802"));
        customers.add(new Customer(3,"Dimas","081234567803"));
        customers.add(new Customer(4,"Naufal","081234567804"));
        customers.add(new Customer(5,"Bagas","081234567805"));
        customers.add(new Customer(6,"Reza","081234567806"));
        customers.add(new Customer(7,"Alif","081234567807"));
        customers.add(new Customer(8,"Gilang","081234567808"));
        services.add(new ServiceItem(1,"Haircut",30,35000));
        services.add(new ServiceItem(2,"Wash",15,15000));
        services.add(new ServiceItem(3,"Haircut + Wash",45,45000));
        services.add(new ServiceItem(4,"Beard Trim",20,25000));
        services.add(new ServiceItem(5,"Full Grooming",60,65000));
        barbers.add(new Barber(1,"Andi",true)); barbers.add(new Barber(2,"Budi",true));
        barbers.add(new Barber(3,"Raka",true)); barbers.add(new Barber(4,"Fajar",true));
        barbers.add(new Barber(5,"Ilham",false));
        LocalDateTime now=LocalDateTime.now().withSecond(0).withNano(0);
        String today = now.toLocalDate().toString().replace("-", "");
        addSeed(1,today+"-A001",customers.get(0),services.get(0),barbers.get(0),30,"In Progress",now.minusMinutes(15));
        addSeed(2,today+"-A002",customers.get(1),services.get(2),barbers.get(1),45,"In Progress",now.minusMinutes(10));
        addSeed(3,today+"-A003",customers.get(2),services.get(1),barbers.get(2),15,"Waiting",now.minusMinutes(6));
        addSeed(4,today+"-A004",customers.get(3),services.get(0),barbers.get(3),30,"Waiting",now.minusMinutes(3));
        addSeed(5,today+"-A005",customers.get(4),services.get(2),barbers.get(2),45,"Completed",now.minusHours(2));
        addSeed(6,today+"-A006",customers.get(5),services.get(0),barbers.get(1),30,"Cancelled",now.minusHours(1));
    }
    private void addSeed(int id,String no,Customer c,ServiceItem s,Barber b,int d,String st,LocalDateTime at){
        QueueEntry q=new QueueEntry(id,no,c,s,b,d,st,at); if(st.equals("In Progress"))q.setStartedAt(at);
        if(st.equals("Completed")){q.setStartedAt(at);q.setFinishedAt(at.plusMinutes(d));}
        queues.add(q); history.add(new QueueHistory(no,null,st,at));
    }
    private int nextCustomerId(){return customerId++;} private int nextServiceId(){return serviceId++;}
    private int nextBarberId(){return barberId++;} private int nextQueueId(){return queueId++;}

    // ------------------------------------------------------------------ validasi customer & barber sedang In Progress
    public boolean isCustomerInProgress(Customer cust) {
        return isCustomerInProgressExcept(cust, -1);
    }

    public boolean isCustomerInProgressExcept(Customer cust, int excludeQueueId) {
        if (cust == null) return false;
        if (dbMode) {
            return sql(c -> {
                try (PreparedStatement ps = c.prepareStatement(
                        "SELECT COUNT(*) FROM queues WHERE customer_id=? AND status='In Progress' AND id<>?")) {
                    ps.setInt(1, cust.getId());
                    ps.setInt(2, excludeQueueId);
                    try (ResultSet rs = ps.executeQuery()) {
                        return rs.next() && rs.getInt(1) > 0;
                    }
                }
            });
        }
        return queues.stream().anyMatch(q ->
                q.getId() != excludeQueueId
                && q.getCustomer() != null
                && q.getCustomer().getId() == cust.getId()
                && "In Progress".equalsIgnoreCase(q.getStatus()));
    }

    public boolean isBarberInProgress(Barber barber) {
        return isBarberInProgressExcept(barber, -1);
    }

    public boolean isBarberInProgressExcept(Barber barber, int excludeQueueId) {
        if (barber == null) return false;
        if (dbMode) {
            return sql(c -> {
                try (PreparedStatement ps = c.prepareStatement(
                        "SELECT COUNT(*) FROM queues WHERE barber_id=? AND status='In Progress' AND id<>?")) {
                    ps.setInt(1, barber.getId());
                    ps.setInt(2, excludeQueueId);
                    try (ResultSet rs = ps.executeQuery()) {
                        return rs.next() && rs.getInt(1) > 0;
                    }
                }
            });
        }
        return queues.stream().anyMatch(q ->
                q.getId() != excludeQueueId
                && q.getBarber() != null
                && q.getBarber().getId() == barber.getId()
                && "In Progress".equalsIgnoreCase(q.getStatus()));
    }

    // ------------------------------------------------------------------ helper JDBC
    private interface SqlWork<T> { T run(Connection c) throws SQLException; }

    private <T> T sql(SqlWork<T> work) {
        try (Connection c = DatabaseConfig.getConnection()) {
            return work.run(c);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static void bind(PreparedStatement ps, Object... p) throws SQLException {
        for (int i = 0; i < p.length; i++) ps.setObject(i + 1, p[i]);
    }

    private static int exec(Connection c, String sql, Object... p) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement(sql)) { bind(ps, p); return ps.executeUpdate(); }
    }

    private static int insert(Connection c, String sql, Object... p) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(ps, p); ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) { k.next(); return k.getInt(1); }
        }
    }

    private static LocalDateTime ts(Timestamp t) { return t == null ? null : t.toLocalDateTime(); }
    private static Timestamp ts(LocalDateTime t) { return t == null ? null : Timestamp.valueOf(t); }

    /** Pesan error yang mudah dibaca untuk ditampilkan di dialog. */
    public static String errorMessage(RuntimeException ex) {
        String m = String.valueOf(ex.getMessage());
        if (m.toLowerCase().contains("foreign key"))
            return "Data ini masih dipakai oleh antrean, jadi tidak bisa dihapus.";
        return "Operasi database gagal: " + m;
    }

    // ------------------------------------------------------------------ muat ulang dari database
    public void reload() {
        if (!dbMode) return;
        sql(c -> {
            customers.clear(); services.clear(); barbers.clear(); queues.clear(); history.clear();
            Map<Integer, Customer> cm = new HashMap<>();
            Map<Integer, ServiceItem> sm = new HashMap<>();
            Map<Integer, Barber> bm = new HashMap<>();
            try (Statement st = c.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT id,name,phone FROM customers ORDER BY id")) {
                    while (rs.next()) { Customer x = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3)); customers.add(x); cm.put(x.getId(), x); }
                }
                try (ResultSet rs = st.executeQuery("SELECT id,name,duration_min,price FROM services ORDER BY id")) {
                    while (rs.next()) { ServiceItem x = new ServiceItem(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)); services.add(x); sm.put(x.getId(), x); }
                }
                try (ResultSet rs = st.executeQuery("SELECT id,name,is_active FROM barbers ORDER BY id")) {
                    while (rs.next()) { Barber x = new Barber(rs.getInt(1), rs.getString(2), rs.getBoolean(3)); barbers.add(x); bm.put(x.getId(), x); }
                }
                try (ResultSet rs = st.executeQuery("SELECT id,queue_number,customer_id,service_id,barber_id,duration_snapshot,status,"
                        + "registered_at,started_at,finished_at FROM queues ORDER BY id")) {
                    while (rs.next()) {
                        int bid = rs.getInt(5); boolean noBarber = rs.wasNull();
                        QueueEntry q = new QueueEntry(rs.getInt(1), rs.getString(2), cm.get(rs.getInt(3)), sm.get(rs.getInt(4)),
                                noBarber ? null : bm.get(bid), rs.getInt(6), rs.getString(7), ts(rs.getTimestamp(8)));
                        q.setStartedAt(ts(rs.getTimestamp(9))); q.setFinishedAt(ts(rs.getTimestamp(10)));
                        queues.add(q);
                    }
                }
                try (ResultSet rs = st.executeQuery("SELECT q.queue_number,h.old_status,h.new_status,h.changed_at FROM queue_history h "
                        + "JOIN queues q ON q.id=h.queue_id ORDER BY h.changed_at,h.id")) {
                    while (rs.next()) history.add(new QueueHistory(rs.getString(1), rs.getString(2), rs.getString(3), ts(rs.getTimestamp(4))));
                }
            }
            return null;
        });
    }

    // ------------------------------------------------------------------ customers
    public Customer addCustomer(String name, String phone) {
        int id = dbMode ? sql(c -> insert(c, "INSERT INTO customers(name,phone) VALUES(?,?)", name, phone)) : nextCustomerId();
        Customer x = new Customer(id, name, phone); customers.add(x); return x;
    }
    public void updateCustomer(Customer x, String name, String phone) {
        if (dbMode) sql(c -> exec(c, "UPDATE customers SET name=?,phone=? WHERE id=?", name, phone, x.getId()));
        x.setName(name); x.setPhone(phone);
    }
    public void deleteCustomer(Customer x) {
        if (dbMode) sql(c -> exec(c, "DELETE FROM customers WHERE id=?", x.getId()));
        customers.remove(x);
    }

    // ------------------------------------------------------------------ services
    public ServiceItem addService(String name, int durationMin, double price) {
        int id = dbMode ? sql(c -> insert(c, "INSERT INTO services(name,duration_min,price) VALUES(?,?,?)", name, durationMin, price)) : nextServiceId();
        ServiceItem x = new ServiceItem(id, name, durationMin, price); services.add(x); return x;
    }
    public void updateService(ServiceItem x, String name, int durationMin, double price) {
        if (dbMode) sql(c -> exec(c, "UPDATE services SET name=?,duration_min=?,price=? WHERE id=?", name, durationMin, price, x.getId()));
        x.setName(name); x.setDurationMin(durationMin); x.setPrice(price);
    }
    public void deleteService(ServiceItem x) {
        if (dbMode) sql(c -> exec(c, "DELETE FROM services WHERE id=?", x.getId()));
        services.remove(x);
    }

    // ------------------------------------------------------------------ barbers
    public Barber addBarber(String name, boolean active) {
        int id = dbMode ? sql(c -> insert(c, "INSERT INTO barbers(name,is_active) VALUES(?,?)", name, active)) : nextBarberId();
        Barber x = new Barber(id, name, active); barbers.add(x); return x;
    }
    public void updateBarber(Barber x, String name, boolean active) {
        if (dbMode) sql(c -> exec(c, "UPDATE barbers SET name=?,is_active=? WHERE id=?", name, active, x.getId()));
        x.setName(name); x.setActive(active);
    }
    public void deleteBarber(Barber x) {
        if (dbMode) sql(c -> exec(c, "DELETE FROM barbers WHERE id=?", x.getId()));
        barbers.remove(x);
    }

    // ------------------------------------------------------------------ queues
    public QueueEntry createQueue(String number, Customer cust, ServiceItem svc, Barber barber) {
        if (isCustomerInProgress(cust)) {
            throw new IllegalStateException("Customer '" + cust.getName() + "' sedang dalam layanan (In Progress) dan tidak bisa mengambil service lagi sebelum selesai.");
        }
        LocalDateTime now = LocalDateTime.now().withNano(0);
        int id;
        if (dbMode) {
            id = sql(c -> {
                int qid = insert(c, "INSERT INTO queues(queue_number,customer_id,service_id,barber_id,duration_snapshot,status,registered_at) "
                        + "VALUES(?,?,?,?,?,?,?)", number, cust.getId(), svc.getId(), barber == null ? null : barber.getId(),
                        svc.getDurationMin(), "Waiting", ts(now));
                exec(c, "INSERT INTO queue_history(queue_id,old_status,new_status,changed_at) VALUES(?,?,?,?)", qid, null, "Waiting", ts(now));
                return qid;
            });
        } else id = nextQueueId();
        QueueEntry q = new QueueEntry(id, number, cust, svc, barber, svc.getDurationMin(), "Waiting", now);
        queues.add(q); history.add(new QueueHistory(number, null, "Waiting", now));
        return q;
    }

    public void updateQueueStatus(QueueEntry q, String oldStatus, String newStatus, LocalDateTime started, LocalDateTime finished) {
        if ("In Progress".equalsIgnoreCase(newStatus)) {
            if (q.getBarber() == null) {
                throw new IllegalStateException("Pilih barber terlebih dahulu sebelum memulai service.");
            }
            if (!q.getBarber().isActive()) {
                throw new IllegalStateException("Barber '" + q.getBarber().getName() + "' sedang tidak aktif.");
            }
            if (isCustomerInProgressExcept(q.getCustomer(), q.getId())) {
                throw new IllegalStateException("Customer '" + q.getCustomer().getName() + "' masih memiliki layanan lain yang sedang In Progress dan tidak bisa In Progress lagi.");
            }
            if (isBarberInProgressExcept(q.getBarber(), q.getId())) {
                throw new IllegalStateException("Barber '" + q.getBarber().getName() + "' sedang melakukan service lain (In Progress) dan tidak bisa service lagi sebelum selesai.");
            }
        }
        LocalDateTime now = LocalDateTime.now().withNano(0);
        if (dbMode) sql(c -> {
            exec(c, "UPDATE queues SET status=?,started_at=?,finished_at=? WHERE id=?", newStatus, ts(started), ts(finished), q.getId());
            exec(c, "INSERT INTO queue_history(queue_id,old_status,new_status,changed_at) VALUES(?,?,?,?)", q.getId(), oldStatus, newStatus, ts(now));
            return null;
        });
        q.setStatus(newStatus); q.setStartedAt(started); q.setFinishedAt(finished);
        history.add(new QueueHistory(q.getNumber(), oldStatus, newStatus, now));
    }

    public void assignBarber(QueueEntry q, Barber b) {
        if (q != null && "In Progress".equalsIgnoreCase(q.getStatus()) && b != null && isBarberInProgressExcept(b, q.getId())) {
            throw new IllegalStateException("Barber '" + b.getName() + "' sedang melakukan service lain (In Progress).");
        }
        if (dbMode) sql(c -> exec(c, "UPDATE queues SET barber_id=? WHERE id=?", b == null ? null : b.getId(), q.getId()));
        q.setBarber(b);
    }
}
