// Clase base Articulo con encapsulamiento
public class Articulo {
    // Atributos privados para aplicar el principio de encapsulamiento
    private int id;
    private String nombre;
    private double precio;

    // Constructor para inicializar el objeto Articulo
    public Articulo(int id, String nombre, double precio) {
        // Validación simple para el ID en el constructor
        if (id <= 0) {
            System.out.println("ADVERTENCIA: El ID del artículo debe ser un número positivo. Se asignará 1.");
            this.id = 1; // Asignamos un valor por defecto si es inválido
        } else {
            this.id = id;
        }
        this.nombre = nombre;
        // Validación simple para el precio en el constructor
        if (precio <= 0) {
            System.out.println("ADVERTENCIA: El precio del artículo debe ser mayor a cero. Se asignará 0.01.");
            this.precio = 0.01; // Asignamos un valor por defecto si es inválido
        } else {
            this.precio = precio;
        }
    }

    // Getter para el atributo id (solo lectura)
    public int getId() {
        return id;
    }
    // No hay setter para el id, ya que en este ejemplo no queremos que cambie después de la creación.

    // Getter para el atributo nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para modificar el nombre
    public void setNombre(String nombreParametro) {
        // Validación simple: asegurarse de que el nombre no esté vacío
        if (nombreParametro != null && !nombreParametro.trim().isEmpty()) {
            this.nombre = nombreParametro;
        } else {
            System.out.println("ADVERTENCIA: El nombre no puede estar vacío. No se ha modificado el nombre.");
        }
    }

    // Getter para el atributo precio
    public double getPrecio() {
        return precio;
    }

    // Setter para modificar el precio
    public void setPrecio(double precio) {
        // Validación simple: asegurarse de que el precio sea positivo
        if (precio > 0) {
            this.precio = precio;
        } else {
            System.out.println("ADVERTENCIA: El precio debe ser mayor a cero. No se ha modificado el precio.");
        }
    }

    // Método para mostrar la información del artículo
    public void mostrar() {
        System.out.println("ID: " + id + " | Nombre: " + nombre + " | Precio: $" + precio);
    }
}