package com.tokobangunan.dao;

import com.tokobangunan.model.Material;
import com.tokobangunan.util.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    public void insert(Material m) throws SQLException {
        String sql = "INSERT INTO material (kode, nama, satuan, stok, harga) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Koneksi.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getKode());
            ps.setString(2, m.getNama());
            ps.setString(3, m.getSatuan());
            ps.setInt   (4, m.getStok());
            ps.setDouble(5, m.getHarga());
            ps.executeUpdate();
        }
    }

    public void update(Material m) throws SQLException {
        String sql = "UPDATE material SET nama=?, satuan=?, stok=?, harga=? WHERE kode=?";
        try (Connection con = Koneksi.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNama());
            ps.setString(2, m.getSatuan());
            ps.setInt   (3, m.getStok());
            ps.setDouble(4, m.getHarga());
            ps.setString(5, m.getKode());
            ps.executeUpdate();
        }
    }

    public void delete(String kode) throws SQLException {
        String sql = "DELETE FROM material WHERE kode=?";
        try (Connection con = Koneksi.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kode);
            ps.executeUpdate();
        }
    }

    public boolean kodeExists(String kode) throws SQLException {
        String sql = "SELECT 1 FROM material WHERE kode=?";
        try (Connection con = Koneksi.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kode);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public List<Material> findAll(String keyword, String orderBy, boolean asc) throws SQLException {
        String kolom;
        switch (orderBy) {
            case "nama":  kolom = "nama";  break;
            case "stok":  kolom = "stok";  break;
            case "harga": kolom = "harga"; break;
            default:      kolom = "kode";  break;
        }
        String arah = asc ? "ASC" : "DESC";
        String sql = "SELECT kode, nama, satuan, stok, harga FROM material "
                   + "WHERE kode LIKE ? OR nama LIKE ? ORDER BY " + kolom + " " + arah;

        List<Material> list = new ArrayList<>();
        try (Connection con = Koneksi.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            String like = "%" + keyword + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Material(
                        rs.getString("kode"), rs.getString("nama"),
                        rs.getString("satuan"), rs.getInt("stok"), rs.getDouble("harga")));
                }
            }
        }
        return list;
    }
}