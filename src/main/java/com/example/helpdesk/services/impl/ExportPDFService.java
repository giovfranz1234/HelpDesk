package com.example.helpdesk.services.impl;

import com.example.helpdesk.controllers.beans.TicketBean;
import com.example.helpdesk.models.Ticket;
import com.example.helpdesk.models.Usuario;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

@Service
public class ExportPDFService {

    public static String fechaFormato(Date fecha) {
        String cadenaFecha;

        Calendar cal= Calendar.getInstance();
        cal.setTime(fecha);
        int dia=cal.get(Calendar.DAY_OF_MONTH);
        int mes=cal.get(Calendar.MONTH)+1;
        int year=cal.get(Calendar.YEAR);

        String mesString;

        switch (mes) {
            case 1:  mesString = "Enero";
                break;
            case 2:  mesString  = "Febrero";
                break;
            case 3:  mesString = "Marzo";
                break;
            case 4:  mesString = "Abril";
                break;
            case 5:  mesString = "Mayo";
                break;
            case 6:  mesString = "Junio";
                break;
            case 7:  mesString = "Julio";
                break;
            case 8:  mesString = "Agosto";
                break;
            case 9:  mesString = "Septiembre";
                break;
            case 10: mesString = "Octubre";
                break;
            case 11: mesString = "Noviembre";
                break;
            case 12: mesString = "Diciembre";
                break;
            default: mesString = "Invalid month";
                break;
        }


        cadenaFecha=dia+" de "+mesString+" de "+year;


        return cadenaFecha;
    }
    public static String fechaFormatotabla(Date fecha ) {
        String cadenaFecha;

       /* Calendar cal= Calendar.getInstance();
        cal.setTime(fecha);
        System.out.println(fecha);

        int dia=cal.get(Calendar.DAY_OF_MONTH);
        System.out.println("dia"+dia);
        int mes=cal.get(Calendar.MONTH);
        System.out.println("mes"+mes);
        int year=cal.get(Calendar.YEAR);*/

        DateFormat formateadorFechaCorta = DateFormat.getDateInstance(DateFormat.SHORT);
        System.out.println(formateadorFechaCorta.format(fecha).toString());
        cadenaFecha =formateadorFechaCorta.format(fecha).toString();




        return cadenaFecha;
    }

    public static ByteArrayInputStream ticketReport(List<TicketBean> tickets) {

        Document document = new Document(PageSize.A4,36,36,54,36);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.addTitle("Resumen de Tickets ");
        document.getPageSize();

        try {

            PdfPTable table = new PdfPTable(10);
            table.setWidthPercentage(100);
            table.setWidths(new int[] { 3,4, 4,4,4,4,4,4,4,4 });

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Nro. Ticket", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Registrado por", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Descripcion", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Dispositivo", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Asignado a", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Prioridad", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Fecha Registro", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Fecha Inicio", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Fecha Fin", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Estado", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (TicketBean ticket : tickets) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(ticket.getId()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(ticket.getCreadoPor()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(ticket.getDescripcion()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(ticket.getDispositivo()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(ticket.getAsignadoa()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(ticket.getPrioridad()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(fechaFormatotabla(ticket.getFechaRegistro())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(fechaFormatotabla(ticket.getFechaInicio())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                if(ticket.getFechaFin()==null){
                   String fecha = "--";
                  cell = new PdfPCell(new Phrase(fecha));
                  cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                  cell.setPaddingRight(5);
                  table.addCell(cell);
                }else {
                    cell = new PdfPCell(new Phrase(fechaFormatotabla(ticket.getFechaFin())));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPaddingRight(5);
                    table.addCell(cell);
                }


                cell = new PdfPCell(new Phrase(ticket.getEstado()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }
            Date hoy = new Date();
            String fechaHoy = fechaFormato(hoy);
            PdfWriter.getInstance(document, out);
            document.open();
            Font fuente1 = new Font();
            document.add(new Paragraph("                                          GOBIERNO AUTONOMO MUNICIPAL DE LA PAZ \n",fuente1));
            document.add(new Paragraph("                                          UNIDAD DE INFRAESTRUCTURA TECNOLOGICA \n",fuente1));
            document.add(new Paragraph("    FECHA: "+fechaHoy+" \n",fuente1));
            document.add(new Paragraph("    -------------------------------------------------------------------------------------------------------------------------------\n",fuente1));

            document.add(new Paragraph("                                                            LISTADO DE TICKETS \n",fuente1));
            document.add(new Paragraph(" \n",fuente1));
            document.add(table);

            document.close();

        } catch (DocumentException ex) {

        }

        return new ByteArrayInputStream(out.toByteArray());
    }



}
