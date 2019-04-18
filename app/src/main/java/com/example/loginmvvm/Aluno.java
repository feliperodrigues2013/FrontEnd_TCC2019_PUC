package com.example.loginmvvm;

import com.google.gson.annotations.SerializedName;

//Classe POJO referente ao cadastro de aluno no banco de dados
public class Aluno {
    @SerializedName("CPF")
    private String CPF;
    @SerializedName("NOME")
    private String NOME;
    @SerializedName("ENDERECO")
    private String ENDERECO;
    @SerializedName("BAIRRO")
    private String BAIRRO;
    @SerializedName("MUNICIPIO")
    private String MUNICIPIO;
    @SerializedName("UF")
    private String UF;
    @SerializedName("TELEFONE")
    private String TELEFONE;
    @SerializedName("EMAIL")
    private String EMAIL;
    @SerializedName("SENHA")
    private String SENHA;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public String getENDERECO() {
        return ENDERECO;
    }

    public void setENDERECO(String ENDERECO) {
        this.ENDERECO = ENDERECO;
    }

    public String getBAIRRO() {
        return BAIRRO;
    }

    public void setBAIRRO(String BAIRRO) {
        this.BAIRRO = BAIRRO;
    }

    public String getMUNICIPIO() {
        return MUNICIPIO;
    }

    public void setMUNICIPIO(String MUNICIPIO) {
        this.MUNICIPIO = MUNICIPIO;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getTELEFONE() {
        return TELEFONE;
    }

    public void setTELEFONE(String TELEFONE) {
        this.TELEFONE = TELEFONE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getSENHA() {
        return SENHA;
    }

    public void setSENHA(String SENHA) {
        this.SENHA = SENHA;
    }
}
