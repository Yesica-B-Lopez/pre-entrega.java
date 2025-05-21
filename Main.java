import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Articulo> lista = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- Menú de Artículos ---");
            System.out.println("1. Crear artículo");
            System.out.println("2. Listar artículos");
            System.out.println("3. Modificar artículo");
            System.out.println("4. Eliminar artículo");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            String opcionStr = sc.nextLine();
            opcion = parsearEntero(opcionStr);

            if (opcion == -1) {
                System.out.println("ERROR: Ingrese un número válido para la opción.");
                opcion = 0;
            }

            switch (opcion) {
                case 1 -> crearArticulo();
                case 2 -> listarArticulos();
                case 3 -> modificarArticulo();
                case 4 -> eliminarArticulo();
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);
        sc.close();
    }

    public static void crearArticulo() {
        int id = 0;
        String nombre;
        double precio = 0.0;
        boolean entradaValida;

        do {
            entradaValida = true;
            System.out.print("ID (debe ser un número positivo y único): ");
            String idStr = sc.nextLine();
            id = parsearEntero(idStr);

            if (id <= 0) {
                System.out.println("ERROR: El ID debe ser un número positivo.");
                entradaValida = false;
            } else if (buscarArticuloPorId(id) != null) {
                System.out.println("ERROR: Ya existe un artículo con ese ID. Ingrese uno diferente.");
                entradaValida = false;
            } else if (id == -1) {
                System.out.println("ERROR: Ingrese un número válido para el ID.");
                entradaValida = false;
            }
        } while (!entradaValida);

        do {
            entradaValida = true;
            System.out.print("Nombre: ");
            nombre = sc.nextLine();
            if (nombre.trim().isEmpty()) {
                System.out.println("ERROR: El nombre no puede estar vacío.");
                entradaValida = false;
            }
        } while (!entradaValida);

        do {
            entradaValida = true;
            System.out.print("Precio (debe ser mayor a cero): ");
            String precioStr = sc.nextLine();
            precio = parsearDoble(precioStr);

            if (precio <= 0) {
                System.out.println("ERROR: El precio debe ser mayor a cero.");
                entradaValida = false;
            } else if (precio == -1.0) {
                System.out.println("ERROR: Ingrese un número válido para el precio.");
                entradaValida = false;
            }
        } while (!entradaValida);

        Articulo nuevo = new Articulo(id, nombre, precio);
        lista.add(nuevo);
        System.out.println("Artículo agregado correctamente.");
    }

    public static Articulo buscarArticuloPorId(int id) {
        for (Articulo articulo : lista) {
            if (articulo.getId() == id) {
                return articulo;
            }
        }
        return null;
    }

    public static void listarArticulos() {
        if (lista.isEmpty()) {
            System.out.println("No hay artículos cargados para listar.");
        } else {
            System.out.println("\n--- Lista de Artículos ---");
            for (Articulo a : lista) {
                a.mostrar();
            }
            System.out.println("--------------------------");
        }
    }

    public static void modificarArticulo() {
        if (lista.isEmpty()) {
            System.out.println("No hay artículos para modificar.");
            return;
        }

        System.out.print("ID del artículo a modificar: ");
        int idModificar;
        Articulo articuloAModificar = null;
        boolean idEncontrado = false;

        String idModificarStr = sc.nextLine();
        idModificar = parsearEntero(idModificarStr);

        if (idModificar == -1) {
            System.out.println("ERROR: Ingrese un número válido para el ID.");
            return;
        }

        articuloAModificar = buscarArticuloPorId(idModificar);
        if (articuloAModificar != null) {
            idEncontrado = true;
        }

        if (!idEncontrado) {
            System.out.println("Artículo no encontrado con el ID: " + idModificar);
            return;
        }

        System.out.println("Artículo encontrado. Ingrese los nuevos datos:");

        boolean nombreValido;
        do {
            nombreValido = true;
            System.out.print("Nuevo nombre: ");
            String nuevoNombre = sc.nextLine();
            if (nuevoNombre.trim().isEmpty()) {
                System.out.println("ERROR: El nombre no puede estar vacío. Intente de nuevo.");
                nombreValido = false;
            } else {
                articuloAModificar.setNombre(nuevoNombre);
            }
        } while (!nombreValido);

        boolean precioValido;
        do {
            precioValido = true;
            System.out.print("Nuevo precio (debe ser mayor a cero): ");
            String nuevoPrecioStr = sc.nextLine();
            double nuevoPrecio = parsearDoble(nuevoPrecioStr);

            if (nuevoPrecio <= 0) {
                System.out.println("ERROR: El precio debe ser mayor a cero.");
                precioValido = false;
            } else if (nuevoPrecio == -1.0) {
                System.out.println("ERROR: Ingrese un número válido para el precio.");
                precioValido = false;
            } else {
                articuloAModificar.setPrecio(nuevoPrecio);
            }
        } while (!precioValido);

        System.out.println("Artículo actualizado correctamente.");
    }

    public static void eliminarArticulo() {
        if (lista.isEmpty()) {
            System.out.println("No hay artículos para eliminar.");
            return;
        }

        System.out.print("ID del artículo a eliminar: ");
        int idEliminar;
        boolean eliminado = false;

        String idEliminarStr = sc.nextLine();
        idEliminar = parsearEntero(idEliminarStr);

        if (idEliminar == -1) {
            System.out.println("ERROR: Ingrese un número válido para el ID.");
            return;
        }

        eliminado = lista.removeIf(a -> a.getId() == idEliminar);

        if (eliminado) {
            System.out.println("Artículo eliminado con éxito.");
        } else {
            System.out.println("Artículo no encontrado con el ID: " + idEliminar);
        }
    }

    
}