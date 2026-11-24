# Brocode Queue Intelligence System

Starter project Java Swing untuk NetBeans (Java 17+, Maven). Dibangun mengikuti rancangan Progress PDF.

## Menjalankan di NetBeans
1. Extract ZIP.
2. NetBeans → File → Open Project → pilih folder `BrocodeQueueIntelligenceSystem`.
3. Pastikan JDK 17+ terpasang.
4. Klik kanan project → Clean and Build, lalu Run.
5. Login demo: **admin** / **admin123**.

## Edit GUI di tab Design NetBeans
Semua layar sekarang berupa **form NetBeans** (`.java` + `.form` berpasangan) di `src/main/java/com/brocode/view/`.
Buka file `.java`-nya lalu klik tab **Design**:

| Form | Isi |
|---|---|
| `LoginFrame` | layar login |
| `MainFrame` | sidebar + header + area halaman (CardLayout) |
| `DashboardPanel`, `CustomersPanel`, `ServicesPanel`, `BarbersPanel`, `QueuePanel`, `AnalyticsPanel`, `HistoryPanel` | tiap halaman |
| `CustomerDialog`, `ServiceDialog`, `BarberDialog`, `QueueDialog` | dialog input (modal) |

Tips:
- Kode di dalam blok biru **Generated Code** jangan diedit manual; ubah lewat Design.
- Klik dua kali tombol di Design untuk lompat ke handler-nya (`btnXxxActionPerformed`).
- Warna gelap/copper diset lewat properti komponen di Design, lalu `Theme` (di constructor) menambahkan border, UI tombol, tabel, dan scrollbar gelap saat aplikasi jalan. Jadi border/gaya tabel baru terlihat saat Run, bukan di Design.
- Isi tabel dan kartu angka diisi dari kode di method `refresh()` tiap panel. Teks statis (judul, label) bisa langsung diganti di Design.
- Batang jam ramai di `AnalyticsPanel` dibuat dinamis (di `refresh()`), jadi di Design area itu kosong.
- Jika Design menampilkan komponen aneh setelah baru extract, lakukan Clean and Build dulu.

## Visual theme
- Palette charcoal / graphite dengan aksen copper dan warm ivory.
- Warna komponen Swing distandarkan secara global: sidebar, navigasi, card, tabel, form, combo box, checkbox, progress bar, scroll pane, dan dialog.
- Tidak lagi mengandalkan warna default Windows untuk komponen utama.

## Isi saat ini
- Satu `JFrame` utama dengan sidebar dan halaman berbasis `JPanel` (masing-masing form tersendiri).
- 8 modul: Login, Dashboard, Customers, Services, Barbers, Queue, Analytics, History.
- Data demo in-memory agar UI dapat langsung dicoba tanpa konfigurasi MySQL.
- CRUD dasar untuk customer, service, barber; pembuatan antrean; assign barber; Start, Complete, Cancel.
- ETA/rekomendasi barber dan ringkasan jam ramai dihitung dari data antrean demo.
- Skema MySQL enam tabel tersedia di `database/schema.sql`.

## Koneksi MySQL
UI starter ini masih memakai `DemoDataStore` (in-memory), jadi perubahan akan hilang saat aplikasi ditutup. File `database/schema.sql` adalah skema awal untuk tahap integrasi JDBC/DAO. Dependensi MySQL Connector/J sudah disiapkan di `pom.xml`, dan konfigurasi koneksi contoh ada di `config/DatabaseConfig.java`.
Jangan simpan password database produksi di source code.

## Struktur package
- `model`: entitas User, Customer, ServiceItem, Barber, QueueEntry, QueueHistory
- `dao`: kontrak DAO
- `service`: aturan antrean, ETA, rekomendasi barber
- `config`: konfigurasi koneksi
- `view`: form NetBeans (JFrame/JPanel/JDialog) tiap modul + `Theme` + `Refreshable`

## Catatan
Ini adalah fondasi UI + alur demo, bukan aplikasi produksi. Belum ada autentikasi aman, transaksi database, atau implementasi DAO JDBC. Nomor antrean demo dibuat per tanggal dan urut dalam sesi aplikasi.


## Perbaikan dependensi NetBeans

Project ini menggunakan `org.jdesktop.layout.GroupLayout` pada kode GUI hasil NetBeans GUI Builder. Dependensi `org.swinglabs:swing-layout:1.0.3` sudah ditambahkan ke `pom.xml` agar package tersebut tersedia saat Maven build. Target kompilasi menggunakan Java 17 (`maven.compiler.release`). Saat project dibuka, lakukan **Reload Project** lalu **Clean and Build** agar Maven mengunduh dependensi.
