package com.example.helpdesk.controllers.beans;

public class GraficoCBean {
    private String tecnico;
    private Long  count;

    public GraficoCBean() {
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
