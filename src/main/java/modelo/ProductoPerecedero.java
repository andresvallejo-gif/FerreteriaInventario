package modelo;

import java.time.LocalDate;

public class ProductoPerecedero extends Producto {
    private LocalDate fechaCaducidad;

    public ProductoPerecedero(String codigo, String nombre, int cantidad, double precio,
                              int stockMinimo, LocalDate fechaCaducidad) {
        super(codigo, nombre, cantidad, precio, stockMinimo);
        this.fechaCaducidad = fechaCaducidad;
    }

    public LocalDate getFechaCaducidad() { return fechaCaducidad; }

    @Override
    public String mostrarInformacion() {
        return String.format("Código: %s | Nombre: %s | Tipo: Perecedero | Cantidad: %d | Precio: $%.2f | Stock mínimo: %d | Caducidad: %s | Valor: $%.2f",
                getCodigo(), getNombre(), getCantidad(), getPrecio(), getStockMinimo(), fechaCaducidad, calcularValor());
    }
}
