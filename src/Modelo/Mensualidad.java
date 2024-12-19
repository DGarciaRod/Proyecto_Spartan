package Modelo;

import java.time.LocalDate;
import java.util.Date;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 *
 *
 */
public class Mensualidad {

    private String dni;
    private String mes_ano;
    private Boolean pagado;
    private Date fecha;
    private double cuota;

    public Mensualidad() {
    }

    public Mensualidad(String dni, String mes_ano, Date fecha, double cuota) {
        this.dni = dni;
        this.mes_ano = mes_ano;
        this.fecha = fecha;
        this.cuota = cuota;
    }

    

    public Mensualidad(String dni) {
        Month mes = LocalDate.now().getMonth();
        int ano = LocalDate.now().getYear();
        String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        this.mes_ano=nombre+"-"+ano;
        this.dni = dni;
        this.pagado = false;
        this.fecha = null;
        this.cuota=30;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getMes_ano() {
        return mes_ano;
    }

    public void setMes_ano(String mes_ano) {
        this.mes_ano = mes_ano;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        if (pagado) {
            return "(" + dni + ") " + mes_ano + " (pagado)";
        } else {
            return "(" + dni + ") " + mes_ano + " (no pagado)";
        }
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

}