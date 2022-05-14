package creatingclass;

import java.util.ArrayList;

public class Task extends ArrayList<Task> {

    private String titulo;
    private boolean finished;

    public String getTitulo() {
        return titulo;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isFinished() {
        return finished;
    }

    }

