package creatingclass;

import java.util.ArrayList;

public class Usuario {
    private String email;
    private String senha;
    private ArrayList<Task> taskList;


    public Usuario (String email, String senha){ // pegar o email e senha para salvar na váriavel do usuário logado
        this.email = email;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    }

