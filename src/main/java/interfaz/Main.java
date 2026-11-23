package interfaz;

import modelo.Producto;
import modelo.ProductoNoPerecedero;
import modelo.ProductoPerecedero;
import negocio.Inventario;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Inventario inventario = new Inventario();

    public static void main(String[] args) {
        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            if (opcion == 1) registrarProducto();
            else if (opcion == 2) listarProductos();
            else if (opcion == 3) buscarProducto();
            else if (opcion == 4) registrarEntrada();
            else if (opcion == 5) registrarSalida();
            else if (opcion == 6) mostrarStockBajo();
            else if (opcion == 7) System.out.printf("Valor total del inventario: $%.2f%n", inventario.calcularValorTotal());
            else if (opcion == 8) System.out.println("Gracias por utilizar el sistema.");
            else System.out.println("Opción no válida.");
            System.out.println();
        } while (opcion != 8);
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("===== FERRETERÍA EL TORNILLO =====");
        System.out.println("1. Registrar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar producto");
        System.out.println("4. Registrar entrada");
        System.out.println("5. Registrar salida");
        System.out.println("6. Mostrar productos con stock bajo");
        System.out.println("7. Calcular valor del inventario");
        System.out.println("8. Salir");
    }

    private static void registrarProducto() {
        System.out.println("--- Registro de producto ---");
        String codigo = leerTextoObligatorio("Código: ");
        if (inventario.buscarProducto(codigo) != null) {
            System.out.println("Ya existe un producto con ese código.");
            return;
        }
        String nombre = leerTextoObligatorio("Nombre: ");
        int cantidad = leerEnteroNoNegativo("Cantidad inicial: ");
        double precio = leerPrecioPositivo("Precio unitario: ");
        int stockMinimo = leerEnteroNoNegativo("Stock mínimo: ");
        System.out.println("Tipo de producto:");
        System.out.println("1. Perecedero");
        System.out.println("2. No perecedero");
        int tipo = leerEntero("Seleccione el tipo: ");
        Producto producto;
        if (tipo == 1) {
            LocalDate fecha = leerFecha("Fecha de caducidad (AAAA-MM-DD): ");
            producto = new ProductoPerecedero(codigo, nombre, cantidad, precio, stockMinimo, fecha);
        } else if (tipo == 2) {
            int garantia = leerEnteroNoNegativo("Garantía en meses: ");
            producto = new ProductoNoPerecedero(codigo, nombre, cantidad, precio, stockMinimo, garantia);
        } else {
            System.out.println("Tipo de producto no válido.");
            return;
        }
        if (inventario.agregarProducto(producto)) System.out.println("Producto registrado correctamente.");
        else System.out.println("No se pudo registrar el producto.");
    }

    private static void listarProductos() {
        List<Producto> productos = inventario.listarProductos();
        if (productos.isEmpty()) { System.out.println("No hay productos registrados."); return; }
        System.out.println("--- Productos registrados ---");
        for (Producto producto : productos) System.out.println(producto.mostrarInformacion());
    }

    private static void buscarProducto() {
        String codigo = leerTextoObligatorio("Ingrese el código del producto: ");
        Producto producto = inventario.buscarProducto(codigo);
        if (producto == null) System.out.println("No se encontró un producto con ese código.");
        else System.out.println(producto.mostrarInformacion());
    }

    private static void registrarEntrada() {
        Producto producto = obtenerProductoPorCodigo();
        if (producto == null) return;
        int unidades = leerEnteroPositivo("Cantidad que ingresa: ");
        if (producto.registrarEntrada(unidades)) System.out.println("Entrada registrada. Stock actual: " + producto.getCantidad());
        else System.out.println("No se pudo registrar la entrada.");
    }

    private static void registrarSalida() {
        Producto producto = obtenerProductoPorCodigo();
        if (producto == null) return;
        int unidades = leerEnteroPositivo("Cantidad que sale: ");
        if (producto.registrarSalida(unidades)) System.out.println("Salida registrada. Stock actual: " + producto.getCantidad());
        else System.out.println("No hay suficiente stock o la cantidad no es válida.");
    }

    private static Producto obtenerProductoPorCodigo() {
        String codigo = leerTextoObligatorio("Código del producto: ");
        Producto producto = inventario.buscarProducto(codigo);
        if (producto == null) System.out.println("No se encontró un producto con ese código.");
        return producto;
    }

    private static void mostrarStockBajo() {
        List<Producto> productos = inventario.mostrarProductosStockBajo();
        if (productos.isEmpty()) { System.out.println("No hay productos con stock bajo."); return; }
        System.out.println("--- Productos con stock bajo ---");
        for (Producto producto : productos) System.out.println(producto.mostrarInformacion());
    }

    private static String leerTextoObligatorio(String mensaje) {
        String valor;
        do {
            System.out.print(mensaje);
            valor = scanner.nextLine().trim();
            if (valor.isEmpty()) System.out.println("Este campo es obligatorio.");
        } while (valor.isEmpty());
        return valor;
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static int leerEnteroNoNegativo(String mensaje) {
        int valor;
        do {
            valor = leerEntero(mensaje);
            if (valor < 0) System.out.println("El valor no puede ser negativo.");
        } while (valor < 0);
        return valor;
    }

    private static int leerEnteroPositivo(String mensaje) {
        int valor;
        do {
            valor = leerEntero(mensaje);
            if (valor <= 0) System.out.println("El valor debe ser mayor que cero.");
        } while (valor <= 0);
        return valor;
    }

    private static double leerPrecioPositivo(String mensaje) {
        double valor;
        do {
            System.out.print(mensaje);
            valor = scanner.nextDouble();
            scanner.nextLine();
            if (valor <= 0) System.out.println("El precio debe ser mayor que cero.");
        } while (valor <= 0);
        return valor;
    }

    private static LocalDate leerFecha(String mensaje) {
        System.out.print(mensaje);
        String entrada = scanner.nextLine();
        return LocalDate.parse(entrada);
    }
}
