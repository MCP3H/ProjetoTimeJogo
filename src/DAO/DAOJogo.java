package DAO;

import Entidade.Jogo;
import Entidade.Time;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOJogo {

    private Connection getConnection() {
        String url = "jdbc:derby://localhost:1527/bancotimejogo";
        String user = "app";
        String password = "app";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean create(Jogo jogo) {
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO JOGOS (TIMEA, TIMEB, GOLSA, GOLSB) VALUES (?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, jogo.getTimeA());
            pstm.setInt(2, jogo.getTimeB());
            pstm.setInt(3, jogo.getGolsA());
            pstm.setInt(4, jogo.getGolsB());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            jogo.setId(id);
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Jogo jogo) {
        try {
            Connection conn = getConnection();
            String sql = "UPDATE JOGOS SET TIMEA = ?, TIMEB = ?, GOLSA = ?, GOLSB = ? WHERE ID = ?";
            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, jogo.getTimeA());
            pstm.setInt(2, jogo.getTimeB());
            pstm.setInt(3, jogo.getGolsA());
            pstm.setInt(4, jogo.getGolsB());
            pstm.setInt(5, jogo.getId());
            pstm.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Jogo jogo) {
        try {
            Connection conn = getConnection();
            String sql = "DELETE FROM JOGOS WHERE ID =?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, jogo.getId());
            pstm.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Jogo read(int id) {
        try {
            Jogo jogo = new Jogo();
            Connection conn = getConnection();
            String sql = "SELECT * FROM JOGOS WHERE ID=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                return null;
            }
            jogo.setId(rs.getInt("ID"));
            jogo.setTimeA(rs.getInt("TIMEA"));
            jogo.setTimeB(rs.getInt("TIMEB"));
            jogo.setGolsA(rs.getInt("GOLSA"));
            jogo.setGolsB(rs.getInt("GOLSB"));
            conn.close();
            return jogo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Jogo> readAll() {
        try {
            List<Jogo> jogos = new ArrayList<Jogo>();
            Connection conn = getConnection();
            String sql = "SELECT * FROM JOGOS";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Jogo jogo = new Jogo();
                jogo.setId(rs.getInt("ID"));
                jogo.setTimeA(rs.getInt("TIMEA"));
                jogo.setTimeB(rs.getInt("TIMEB"));
                jogo.setGolsA(rs.getInt("GOLSA"));
                jogo.setGolsB(rs.getInt("GOLSB"));
                jogos.add(jogo);
            }
            conn.close();
            return jogos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
