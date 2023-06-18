package view;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import model.dao.DisciplinaDAO;
import model.dao.FactoryDAO;
import model.entities.Disciplina;

public class TelaDisciplina {

    static DisciplinaDAO disciplinaDao = FactoryDAO.createDisciplinaDAO();

    @SuppressWarnings("resource")
    public static Scanner menuDisciplina(Scanner console) throws InterruptedException, ParseException {

        int opcao = 0;
        do {
            System.out.println("\n\n");
            System.out.println("    ###   Tela: Disciplina     ###");
            System.out.println("    =========================");
            System.out.println("    |     1 - Cadastrar       |");
            System.out.println("    |     2 - Listar          |");
            System.out.println("    |     3 - Alterar         |");
            System.out.println("    |     4 - Excluir         |");
            System.out.println("    |     0 - Retornar        |");
            System.out.println("    =========================");
            System.out.print("    Opção -> ");
            opcao = console.nextInt();
            console.nextLine();

            switch (opcao) {
                case 1:
                    console = cadastrar(console);
                    break;
                case 2:
                    console = listar(console);
                    break;
                case 3:
                    console = alterar(console);
                    break;
                case 4:
                    console = excluir(console);
                    break;
                case 0:
                    console = TelaPrincipal.menuPrincipal(console);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    TimeUnit.SECONDS.sleep(1);
            }
        } while (opcao != 0);
        return console;
    }

    private static Scanner cadastrar(Scanner console) throws ParseException {

        Disciplina disciplina = new Disciplina();

        System.out.println("\n\n");
        System.out.println("    ###   Disciplina-Cadastrar ###");
        System.out.println("    =========================");

        System.out.print("    |     Nome: ");
        disciplina.setNome(console.nextLine());

        System.out.print("    |     Carga Horária: ");
        disciplina.setCargaHoraria(console.nextInt());

        System.out.println("    =========================");

        disciplinaDao.insert(disciplina);

        console.nextLine();
        return console;
    }

    private static Scanner listar(Scanner console) {

        List<Disciplina> disciplinas = disciplinaDao.findAll();

        System.out.println("\n\n");
        System.out.println("    ###   Disciplina-Listar    ###");
        System.out.println("    =========================");
        System.out.println("    |     Id\tNome\tCarga Horária");
        for (Disciplina disciplina : disciplinas) {
            System.out.println("    |     " + disciplina.getId()
                    + "\t" + disciplina.getNome()
                    + "\t" + disciplina.getCargaHoraria());
        }
        System.out.println("    =========================");
        console.nextLine();
        return console;
    }

    private static Scanner alterar(Scanner console) throws ParseException {

        Disciplina disciplina = new Disciplina();

        System.out.println("\n\n");
        System.out.println("    ###   Disciplina-Alterar   ###");
        System.out.println("    =========================");
        System.out.print("    |     Id: ");
        disciplina.setId(console.nextInt());
        console.nextLine();

        System.out.print("    |     Nome: ");
        disciplina.setNome(console.nextLine());

        System.out.print("    |     Carga Horária: ");
        disciplina.setCargaHoraria(console.nextInt());

        System.out.println("    =========================");
        disciplinaDao.update(disciplina);

        console.nextLine();
        return console;
    }

    private static Scanner excluir(Scanner console) throws ParseException {

        System.out.println("\n\n");
        System.out.println("    ###   Disciplina-Excluir   ###");
        System.out.println("    =========================");
        System.out.print("    |     Digite o Id: ");
        int id = console.nextInt();
        console.nextLine();
        System.out.println("    =========================");

        disciplinaDao.deleteById(id);

        console.nextLine();
        return console;
    }
}
