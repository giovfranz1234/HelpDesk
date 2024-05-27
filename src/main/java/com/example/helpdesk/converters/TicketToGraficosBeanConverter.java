package com.example.helpdesk.converters;

import com.example.helpdesk.controllers.beans.GraficosBean;
import com.example.helpdesk.models.Ticket;
import org.springframework.core.convert.converter.Converter;

public class TicketToGraficosBeanConverter implements Converter<Ticket, GraficosBean> {

    @Override
    public GraficosBean convert(Ticket ticket) {
        GraficosBean graficosBean = new GraficosBean();
        graficosBean.setAsignadoa(ticket.getAsignadoa());

        return graficosBean;
    }
}
