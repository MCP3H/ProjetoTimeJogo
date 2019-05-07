package Jogo;

import Time.Time;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOJogo {

    private PreparedStatement stmC;
    private PreparedStatement stmRALL;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;

    private Connection conn;

    public DAOJogo() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.conn = DriverManager.getConnection("jdbc:derby://localhost:1527/bancotimejogo", "app", "app");
            this.stmC = this.conn.prepareStatement("INSERT INTO JOGOS (TIMEA, TIMEB, GOLSA, GOLSB) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            this.stmRALL = this.conn.prepareStatement("SELECT * FROM JOGOS");
            this.stmR = this.conn.prepareStatement("SELECT * FROM JOGOS WHERE ID=?");
            this.stmU = this.conn.prepareStatement("UPDATE JOGOS SET TIMEA = ?, TIMEB = ?, GOLSA = ?, GOLSB = ? WHERE ID = ?");
            this.stmD = this.conn.prepareStatement("DELETE FROM JOGOS WHERE ID=?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Jogo> readAll() {
        try {
            ResultSet rs = this.stmRALL.executeQuery();
            List<Jogo> jogos = new ArrayList<>();
            while (rs.next()) {
                Jogo j = new Jogo();
                j.setId(rs.getInt("ID"));
                j.setTimea(rs.getInt("TIMEA"));
                j.setTimeb(rs.getInt("TIMEB"));
                j.setGolsa(rs.getInt("GOLSA"));
                j.setGolsb(rs.getInt("GOLSB"));
                jogos.add(j);
            }
            return jogos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Jogo read(int id) {
        try {
            Jogo j = new Jogo();
            this.stmR.setInt(1, id);
            ResultSet rs = this.stmR.executeQuery();
            while (rs.next()) {
                j.setId(rs.getInt("ID"));
                j.setTimea(rs.getInt("TIMEA"));
                j.setTimeb(rs.getInt("TIMEB"));
                j.setGolsa(rs.getInt("GOLSA"));
                j.setGolsb(rs.getInt("GOLSB"));
            }
            return j;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int id) {
        try {
            this.stmD.setInt(1, id);
            int i = this.stmD.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Jogo create(Jogo jogo) {
        try {
            this.stmC.setInt(1, jogo.getTimea());
            this.stmC.setInt(2, jogo.getTimeb());
            this.stmC.setInt(3, jogo.getGolsa());
            this.stmC.setInt(4, jogo.getGolsb());
            this.stmC.executeUpdate();

            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            jogo.setId(id);

            return jogo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Jogo update(int id, Jogo jogo) {
        try {
            jogo.setId(id);
            this.stmU.setInt(1, jogo.getTimea());
            this.stmU.setInt(2, jogo.getTimeb());
            this.stmU.setInt(3, jogo.getGolsa());
            this.stmU.setInt(4, jogo.getGolsb());
            this.stmU.setInt(5, id);
            int i = this.stmU.executeUpdate();
            if (i > 0) {
                return jogo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
