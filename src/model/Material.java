package com.tokobangunan.model;

public class Material {
    private String kode;
    private String nama;
    private String satuan;
    private int    stok;
    private double harga;

    public Material() { }

    public Material(String kode, String nama, String satuan, int stok, double harga) {
        this.kode = kode; this.nama = nama; this.satuan = satuan;
        this.stok = stok; this.harga = harga;
    }

    public String getKode()   { return kode; }
    public void   setKode(String kode) { this.kode = kode; }
    public String getNama()   { return nama; }
    public void   setNama(String nama) { this.nama = nama; }
    public String getSatuan() { return satuan; }
    public void   setSatuan(String satuan) { this.satuan = satuan; }
    public int    getStok()   { return stok; }
    public void   setStok(int stok) { this.stok = stok; }
    public double getHarga()  { return harga; }
    public void   setHarga(double harga) { this.harga = harga; }
}