import java.util.*;

// Clase que representa la agenda telefónica
public class AgendaTelefonica {
    private Set<Contacto> contactos;
    private int capacidadMaxima;

    // Constructor con tamaño por defecto (10 contactos)
    public AgendaTelefonica() {
        this(10);
    }

    // Constructor con tamaño máximo especificado
    public AgendaTelefonica(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.contactos = new HashSet<>();
    }

    // Método para añadir un contacto
    public boolean añadirContacto(Contacto c) {
        // Validar que nombre y apellido no estén vacíos
        if (c.getNombre() == null || c.getNombre().trim().isEmpty() ||
                c.getApellido() == null || c.getApellido().trim().isEmpty()) {
            System.out.println("Error: El nombre y apellido no pueden estar vacíos.");
            return false;
        }

        // Verificar si la agenda está llena
        if (agendaLlena()) {
            System.out.println("Error: La agenda está llena. No se pueden añadir más contactos.");
            return false;
        }

        // Verificar si el contacto ya existe
        if (existeContacto(c)) {
            System.out.println("Error: El contacto '" + c.getNombre() + " " + c.getApellido() +
                    "' ya existe en la agenda.");
            return false;
        }

        // Añadir el contacto
        contactos.add(c);
        System.out.println("Contacto '" + c.getNombre() + " " + c.getApellido() +
                "' añadido correctamente.");
        return true;
    }

    // Método para verificar si un contacto existe
    public boolean existeContacto(Contacto c) {
        return contactos.contains(c);
    }

    // Método para listar todos los contactos ordenados alfabéticamente
    public void listarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }

        System.out.println("\n=== LISTA DE CONTACTOS ===");

        // Convertir a lista para ordenar
        List<Contacto> listaContactos = new ArrayList<>(contactos);

        // Ordenar alfabéticamente por nombre y apellido
        listaContactos.sort(Comparator
                .comparing((Contacto contacto) -> contacto.getNombre().toLowerCase())
                .thenComparing(contacto -> contacto.getApellido().toLowerCase()));

        // Mostrar los contactos
        for (Contacto contacto : listaContactos) {
            System.out.println(contacto);
        }

        System.out.println("Total: " + contactos.size() + " contacto(s)\n");
    }

    // Método para buscar un contacto por nombre y apellido
    public void buscaContacto(String nombre, String apellido) {
        Contacto contactoBusqueda = new Contacto(nombre, apellido, "");

        for (Contacto contacto : contactos) {
            if (contacto.equals(contactoBusqueda)) {
                System.out.println("Teléfono de " + nombre + " " + apellido +
                        ": " + contacto.getTelefono());
                return;
            }
        }

        System.out.println("Contacto '" + nombre + " " + apellido +
                "' no encontrado en la agenda.");
    }

    // Método para eliminar un contacto
    public boolean eliminarContacto(Contacto c) {
        if (contactos.remove(c)) {
            System.out.println("Contacto '" + c.getNombre() + " " + c.getApellido() +
                    "' eliminado correctamente.");
            return true;
        } else {
            System.out.println("Error: El contacto '" + c.getNombre() + " " + c.getApellido() +
                    "' no existe en la agenda.");
            return false;
        }
    }

    // Método para modificar el teléfono de un contacto
    public boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        Contacto contactoBusqueda = new Contacto(nombre, apellido, "");

        for (Contacto contacto : contactos) {
            if (contacto.equals(contactoBusqueda)) {
                contacto.setTelefono(nuevoTelefono);
                System.out.println("Teléfono de '" + nombre + " " + apellido +
                        "' actualizado correctamente.");
                return true;
            }
        }

        System.out.println("Error: No se puede modificar. El contacto '" + nombre + " " + apellido +
                "' no existe en la agenda.");
        return false;
    }

    // Método para verificar si la agenda está llena
    public boolean agendaLlena() {
        return contactos.size() >= capacidadMaxima;
    }

    // Método para mostrar cuántos espacios libres hay
    public void espaciosLibres() {
        int espaciosDisponibles = capacidadMaxima - contactos.size();
        System.out.println("Espacios disponibles en la agenda: " + espaciosDisponibles +
                " de " + capacidadMaxima);
    }

    // Método para obtener el número de contactos actuales
    public int getNumeroContactos() {
        return contactos.size();
    }

    // Método para obtener la capacidad máxima
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
}