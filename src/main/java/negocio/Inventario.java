package negocio;

import modelo.Producto;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private ArrayList<Producto> productos = new ArrayList<>();

    public boolean agregarProducto(Producto producto) {
        if (producto == null || buscarProducto(producto.getCodigo()) != null) return false;
        productos.add(producto);
        return true;
    }

    public Producto buscarProducto(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) return null;
        for (Producto producto : productos) {
            if (producto.getCodigo().equalsIgnoreCase(codigo.trim())) return producto;
        }
        return null;
    }

    public List<Producto> listarProductos() { return new ArrayList<>(productos); }

    public List<Producto> mostrarProductosStockBajo() {
        ArrayList<Producto> resultado = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.tieneStockBajo()) resultado.add(producto);
        }
        return resultado;
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Producto producto : productos) total += producto.calcularValor();
        return total;
    }
}
