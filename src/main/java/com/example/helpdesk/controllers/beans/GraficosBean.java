package com.example.helpdesk.controllers.beans;

public class GraficosBean {

    private Long asignadoa;
    private Long  count;

    public GraficosBean(Long asignadoa, Long count) {
        this.asignadoa = asignadoa;
        this.count = count;
    }
    public GraficosBean() {
    }

    public Long getAsignadoa() {
        return asignadoa;
    }

    public void setAsignadoa(Long asignadoa) {
        this.asignadoa = asignadoa;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
