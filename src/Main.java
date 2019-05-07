//
//import Jogo.DAOJogo;
//import Time.DAOTime;
//import Jogo.Jogo;
//import Time.Time;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//
//    public static DAOTime btime = new DAOTime();
//    public static DAOJogo bjogo = new DAOJogo();
//
//    public static void main(String[] args) {
//        Time t = new Time();
//        Jogo j = new Jogo();
//        Scanner en = new Scanner(System.in);
//
//        int x;
//
//        String p;
//        int v;
//
//        do {
//            System.out.print("MENU\n" + "1 - Time\n" + "2 - Jogo\n" + "3 - Sair\nDigite o meu que deseja acessar: ");
//            x = en.nextInt();
//            linha();
//            if (x == 1) {
//                do {
//                    do {
//                        System.out.print("MENU - TIME\n" + "1 - Criar Time\n" + "2 - Ler Time\n" + "3 - Alterar Time\n" + "4 - Deletar Time\n" + "5 - Times\n" + "6 - Sair\nDigite a opção: ");
//                        x = en.nextInt();
//                    } while (x < 0 || x > 6);
//                    if (x == 1) {
//                        linha();
//                        System.out.print("Nome: ");
//                        p = en.next();
//                        t.setNome(p);
//                        System.out.print("Ano: ");
//                        v = en.nextInt();
//                        t.setAno(v);
//                        System.out.print("Cidade: ");
//                        p = en.next();
//                        t.setCidade(p);
//                        System.out.print("Estado: ");
//                        p = en.next();
//                        t.setEstado(p);
//                        btime.create(t);
//                    }
//                    if (x == 2) {
//                        linha();
//                        System.out.print("Digite o id do time: ");
//                        v = en.nextInt();
//                        t = btime.read(v);
//                        System.out.println("ID: " + t.getId());
//                        System.out.println("Nome: " + t.getNome());
//                        System.out.println("Ano: " + t.getAno());
//                        System.out.println("Cidade: " + t.getCidade());
//                        System.out.println("Estado: " + t.getEstado());
//                    }
//                    if (x == 3) {
//                        linha();
//                        System.out.print("Digite o id do time: ");
//                        v = en.nextInt();
//                        t = btime.read(v);
//                        System.out.print("Nome: ");
//                        p = en.next();
//                        t.setNome(p);
//                        System.out.print("Ano: ");
//                        v = en.nextInt();
//                        t.setAno(v);
//                        System.out.print("Cidade: ");
//                        p = en.next();
//                        t.setCidade(p);
//                        System.out.print("Estado: ");
//                        p = en.next();
//                        t.setEstado(p);
//                        btime.update(t);
//                    }
//                    if (x == 4) {
//                        linha();
//                        System.out.print("Digite o id do time: ");
//                        v = en.nextInt();
//                        t = btime.read(v);
//                        btime.delete(t);
//                    }
//                    if (x == 5) {
//                        linha();
//                        lerTimes(btime);
//                    }
//                    linha();
//                } while (x != 6);
//            }
//            if (x == 2) {
//                do {
//                    do {
//                        System.out.print("MENU - Jogo\n" + "1 - Criar Jogo\n" + "2 - Ler Jogo\n" + "3 - Alterar Jogo\n" + "4 - Deletar Jogo\n" + "5 - Jogos\n" + "6 - Sair\nDigite a opção: ");
//                        x = en.nextInt();
//                    } while (x < 0 || x > 6);
//                    if (x == 1) {
//                        linha();
//                        List<Time> times = btime.readAll();
//                        lerTimes(btime);
//                        do {
//                            System.out.print("Time A (ID): ");
//                            v = en.nextInt();
//                            for (Time ti : times) {
//                                if (v == ti.getId()) {
//                                    j.setTimeA(v);
//                                    v = -1;
//                                    break;
//                                }
//                            }
//                        } while (v != -1);
//                        do {
//                            System.out.print("Time B (ID): ");
//                            v = en.nextInt();
//                            for (Time ti : times) {
//                                if (v == ti.getId()) {
//                                    j.setTimeB(v);
//                                    v = -1;
//                                    break;
//                                }
//                            }
//                        } while (v != -1);
//                        System.out.print("Gols A: ");
//                        v = en.nextInt();
//                        j.setGolsA(v);
//                        System.out.print("Gols B: ");
//                        v = en.nextInt();
//                        j.setGolsB(v);
//                        bjogo.create(j);
//                    }
//                    if (x == 2) {
//                        linha();
//                        System.out.print("Digite o id do jogo: ");
//                        v = en.nextInt();
//                        j = bjogo.read(v);
//                        System.out.println("ID: " + j.getId());
//                        System.out.println("Time A (ID): " + j.getTimeA());
//                        System.out.println("Time B (ID): " + j.getTimeB());
//                        System.out.println("Gols A: " + j.getGolsA());
//                        System.out.println("Gols B: " + j.getGolsB());
//                    }
//                    if (x == 3) {
//                        linha();
//                        System.out.print("Digite o id do jogo: ");
//                        v = en.nextInt();
//                        t = btime.read(v);
//                        System.out.print("Time A (ID): ");
//                        v = en.nextInt();
//                        j.setTimeA(v);
//                        System.out.print("Time B (ID): ");
//                        v = en.nextInt();
//                        j.setTimeB(v);
//                        System.out.print("Gols A: ");
//                        v = en.nextInt();
//                        j.setGolsA(v);
//                        System.out.print("Gols B: ");
//                        v = en.nextInt();
//                        j.setGolsB(v);
//                        bjogo.update(j);
//                    }
//                    if (x == 4) {
//                        linha();
//                        System.out.print("Digite o id do jogo: ");
//                        v = en.nextInt();
//                        j = bjogo.read(v);
//                        bjogo.delete(j);
//                    }
//                    if (x == 5) {
//                        linha();
//                        lerJogos(bjogo);
//                    }
//                    linha();
//                } while (x != 6);
//            }
//
//        } while (x < 0 || x > 3);
//
//    }
//
//    public static void lerTimes(DAOTime time) {
//        List<Time> times = time.readAll();
//        System.out.println("Lista de Times");
//        linha();
//        for (Time x : times) {
//            System.out.println("ID: " + x.getId());
//            System.out.println("Nome: " + x.getNome());
//            System.out.println("Ano: " + x.getAno());
//            System.out.println("Cidade: " + x.getCidade());
//            System.out.println("Estado: " + x.getEstado());
//            linha();
//        }
//    }
//
//    public static void lerJogos(DAOJogo jogo) {
//        List<Jogo> jogos = jogo.readAll();
//        System.out.println("Lista de Jogos");
//        linha();
//        for (Jogo x : jogos) {
//            System.out.println("ID: " + x.getId());
//            Time y = btime.read(x.getTimeA());
//            System.out.println("Time A: " + y.getNome());
//            y = btime.read(x.getTimeB());
//            System.out.println("Time B: " + y.getNome());
//            System.out.println("Gols A: " + x.getGolsA());
//            System.out.println("Gols B: " + x.getGolsB());
//            linha();
//        }
//    }
//
//    public static void linha() {
//        System.out.println("--------------------");
//    }
//}
