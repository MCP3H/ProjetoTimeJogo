package DAO;
import Entidade.Time;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOTime {

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

    public boolean create(Time time) {
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO TIMES (NOME, ANO, CIDADE, ESTADO) VALUES (?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, time.getNome());
            pstm.setInt(2, time.getAno());
            pstm.setString(3, time.getCidade());
            pstm.setString(4, time.getEstado());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            time.setId(id);
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Time time) {
        try {
            Connection conn = getConnection();
            String sql = "UPDATE TIMES SET NOME = ?, ANO = ?, CIDADE = ?, ESTADO = ? WHERE ID = ?";
            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, time.getNome());
            pstm.setInt(2, time.getAno());
            pstm.setString(3, time.getCidade());
            pstm.setString(4, time.getEstado());
            pstm.setInt(5, time.getId());
            pstm.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Time time) {
        try {
            Connection conn = getConnection();
            String sql = "DELETE FROM TIMES WHERE ID =?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, time.getId());
            pstm.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Time read(int id) {
        try {
            Time time = new Time();
            Connection conn = getConnection();
            String sql = "SELECT * FROM TIMES WHERE ID=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                return null;
            }
            time.setId(rs.getInt("ID"));
            time.setNome(rs.getString("NOME"));
            time.setAno(rs.getInt("ANO"));
            time.setCidade(rs.getString("CIDADE"));
            time.setEstado(rs.getString("ESTADO"));
            conn.close();
            return time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Time> readAll() {
        try {
            List<Time> times = new ArrayList<Time>();
            Connection conn = getConnection();
            String sql = "SELECT * FROM TIMES";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Time time = new Time();
                time.setId(rs.getInt("ID"));
                time.setNome(rs.getString("NOME"));
                time.setAno(rs.getInt("ANO"));
                time.setCidade(rs.getString("CIDADE"));
                time.setEstado(rs.getString("ESTADO"));
                times.add(time);
            }
            conn.close();
            return times;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
