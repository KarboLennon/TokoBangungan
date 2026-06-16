package com.tokobangunan.dao;

import com.tokobangunan.util.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean cekLogin(String username, String password) throws SQLException {
        String sql = "SELECT 1 FROM user WHERE username=? AND password=?";
        try (Connection con = Koneksi.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}