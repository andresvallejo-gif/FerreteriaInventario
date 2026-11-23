package modelo;

import java.time.LocalDate;

public abstract class Producto {
    private String codigo;
    private String nombre;
    private int cantidad;
    private double precio;
    private int stockMinimo;
    private LocalDate fechaIngreso;

    public Producto(String codigo, String nombre, int cantidad, double precio, int stockMinimo) {
        this.codigo = codigo.trim();
        this.nombre = nombre.trim();
        this.cantidad = cantidad;
        this.precio = precio;
        this.stockMinimo = stockMinimo;
        this.fechaIngreso = LocalDate.now();
    }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public double getPrecio() { return precio; }
    public int getStockMinimo() { return stockMinimo; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }

    public boolean registrarEntrada(int unidades) {
        if (unidades <= 0 || cantidad > Integer.MAX_VALUE - unidades) return false;
        cantidad += unidades;
        return true;
    }
    public boolean registrarSalida(int unidades) {
        if (unidades <= 0 || unidades > cantidad) return false;
        cantidad -= unidades;
        return true;
    }
    public double calcularValor() { return cantidad * precio; }
    public boolean tieneStockBajo() { return cantidad <= stockMinimo; }
    public abstract String mostrarInformacion();
}
