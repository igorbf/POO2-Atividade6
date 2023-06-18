package model.entities;

import java.util.Date;
import java.util.Objects;

public class Aluno {

    private Integer id;
    private String nome;
    private String sexo;
    private Date dtNasc;
    private float nota;

    public Aluno() {

    }

    public Aluno(Integer id, String nome, String sexo, Date dtNasc, float nota) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dtNasc = dtNasc;
        this.nota = nota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", dtNasc=" + dtNasc + ", nota=" + nota + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Aluno other = (Aluno) obj;
        return Objects.equals(nome, other.nome);
    }

}
