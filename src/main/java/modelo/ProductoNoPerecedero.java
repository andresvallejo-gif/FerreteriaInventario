package modelo;

public class ProductoNoPerecedero extends Producto {
    private int garantiaMeses;

    public ProductoNoPerecedero(String codigo, String nombre, int cantidad, double precio,
                                int stockMinimo, int garantiaMeses) {
        super(codigo, nombre, cantidad, precio, stockMinimo);
        this.garantiaMeses = garantiaMeses;
    }

    public int getGarantiaMeses() { return garantiaMeses; }

    @Override
    public String mostrarInformacion() {
        return String.format("Código: %s | Nombre: %s | Tipo: No perecedero | Cantidad: %d | Precio: $%.2f | Stock mínimo: %d | Garantía: %d meses | Valor: $%.2f",
                getCodigo(), getNombre(), getCantidad(), getPrecio(), getStockMinimo(), garantiaMeses, calcularValor());
    }
}
