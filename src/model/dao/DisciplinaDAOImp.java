package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.Disciplina;

public class DisciplinaDAOImp implements DisciplinaDAO {

    private Connection conexao;

    public DisciplinaDAOImp(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void insert(Disciplina obj) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String sql = "INSERT INTO disciplina (nomedisciplina, cargahoraria) VALUES (?, ?)";
            pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, obj.getNome());
            pst.setInt(2, obj.getCargaHoraria());
            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                rs = pst.getGeneratedKeys();
                rs.next();
                obj.setId(rs.getInt(1));
                System.out.println(obj.toString() + " foi criada com sucesso!");
            } else {
                System.out.println("Não foi possível cadastrar a disciplina!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Disciplina obj) {
        PreparedStatement pst = null;

        try {
            String sql = "UPDATE disciplina SET nomedisciplina = ?, cargahoraria = ? WHERE iddisciplina = ?";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setInt(2, obj.getCargaHoraria());
            pst.setInt(3, obj.getId());
            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                System.out.println(obj.toString() + " foi atualizada com sucesso!");
            } else {
                System.out.println("Não foi possível atualizar a disciplina!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement pst = null;

        try {
            String sql = "DELETE FROM disciplina WHERE iddisciplina = ?";
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                System.out.println("Disciplina de id " + id + " foi excluída com sucesso!");
            } else {
                System.out.println("Não foi possível excluir a disciplina!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Disciplina findById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Disciplina disciplina = null;

        try {
            String sql = "SELECT * FROM disciplina WHERE iddisciplina = ?";
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                disciplina = new Disciplina();
                disciplina.setId(rs.getInt("id"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCargaHoraria(rs.getInt("cargahoraria"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disciplina;
    }

    @Override
    public List<Disciplina> findAll() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Disciplina> disciplinas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM disciplina";
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("id"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCargaHoraria(rs.getInt("cargahoraria"));
                disciplinas.add(disciplina);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disciplinas;
    }
}
