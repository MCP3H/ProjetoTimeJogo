
import DAO.DAOJogo;
import DAO.DAOTime;
import Entidade.Jogo;
import Entidade.Time;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static DAOTime btime = new DAOTime();
    public static DAOJogo bjogo = new DAOJogo();

    public static void main(String[] args) {
        Time t = new Time();
        Scanner en = new Scanner(System.in);

        int x;

        String p;
        int v;

        do {
            do {
                System.out.print("MENU - TIME\n" + "1 - Criar Time\n" + "2 - Ler Time\n" + "3 - Alterar Time\n" + "4 - Deletar Time\n" + "5 - Times\n" + "6 - Sair\nDigite a opção: ");
                x = en.nextInt();
            } while (x < 0 || x > 6);
            if (x == 1) {
                linha();
                System.out.print("Nome: ");
                p = en.next();
                t.setNome(p);
                System.out.print("Ano: ");
                v = en.nextInt();
                t.setAno(v);
                System.out.print("Cidade: ");
                p = en.next();
                t.setCidade(p);
                System.out.print("Estado: ");
                p = en.next();
                t.setEstado(p);
                btime.create(t);
                linha();
            }
            if (x == 2) {
                linha();
                System.out.print("Digite o id do time: ");
                v = en.nextInt();
                t = btime.read(v);
                System.out.println("ID: " + t.getId());
                System.out.println("Nome: " + t.getNome());
                System.out.println("Ano: " + t.getAno());
                System.out.println("Cidade: " + t.getCidade());
                System.out.println("Estado: " + t.getEstado());
                linha();
            }
            if (x == 3) {
                linha();
                System.out.print("Digite o id do time: ");
                v = en.nextInt();
                t = btime.read(v);
                System.out.print("Nome: ");
                p = en.next();
                t.setNome(p);
                System.out.print("Ano: ");
                v = en.nextInt();
                t.setAno(v);
                System.out.print("Cidade: ");
                p = en.next();
                t.setCidade(p);
                System.out.print("Estado: ");
                p = en.next();
                t.setEstado(p);
                btime.update(t);
                linha();
            }
            if (x == 4) {
                linha();
                System.out.print("Digite o id do time: ");
                v = en.nextInt();
                t = btime.read(v);
                btime.delete(t);
                linha();
            }
            if (x == 5) {
                linha();
                lerTimes(btime);
            }
        } while (x != 6);
    }

    public static void lerTimes(DAOTime time) {
        List<Time> times = time.readAll();
        System.out.println("Lista de Times");
        linha();
        for (Time x : times) {
            System.out.println("ID: " + x.getId());
            System.out.println("Nome: " + x.getNome());
            System.out.println("Ano: " + x.getAno());
            System.out.println("Cidade: " + x.getCidade());
            System.out.println("Estado: " + x.getEstado());
            linha();
        }
    }

    public static void lerJogos(DAOJogo jogo) {
        List<Jogo> jogos = jogo.readAll();
        System.out.println("Lista de Jogos");
        linha();
        for (Jogo x : jogos) {
            System.out.println("ID: " + x.getId());
            Time y = btime.read(x.getTimeA());
            System.out.println("Time A: " + y.getNome());
            y = btime.read(x.getTimeB());
            System.out.println("Time B: " + y.getNome());
            System.out.println("Gols A: " + x.getGolsA());
            System.out.println("Gols B: " + x.getGolsB());
            linha();
        }
    }

    public static void linha() {
        System.out.println("--------------------");
    }
}
