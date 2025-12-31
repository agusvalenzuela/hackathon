import java.util.Scanner;

// Clase principal con el menú de consola
public class SistemaAgendaTelefonica {
    private static AgendaTelefonica agenda;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarAgenda();
        mostrarMenu();
    }

    // Método para inicializar la agenda
    private static void inicializarAgenda() {
        System.out.println("=== SISTEMA DE AGENDA TELEFÓNICA ===");
        System.out.println("¿Desea crear una agenda con tamaño personalizado?");
        System.out.println("1. Sí, especificar tamaño máximo");
        System.out.println("2. No, usar tamaño por defecto (10 contactos)");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el tamaño máximo de la agenda: ");
                int tamaño = leerEntero();
                if (tamaño > 0) {
                    agenda = new AgendaTelefonica(tamaño);
                    System.out.println("Agenda creada con capacidad para " + tamaño + " contactos.");
                } else {
                    System.out.println("Tamaño inválido. Se usará el tamaño por defecto (10).");
                    agenda = new AgendaTelefonica();
                }
                break;
            case 2:
            default:
                agenda = new AgendaTelefonica();
                System.out.println("Agenda creada con tamaño por defecto (10 contactos).");
                break;
        }

        System.out.println();
    }

    // Método para mostrar el menú principal
    private static void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Verificar si existe un contacto");
            System.out.println("3. Listar todos los contactos");
            System.out.println("4. Buscar contacto por nombre y apellido");
            System.out.println("5. Eliminar contacto");
            System.out.println("6. Modificar teléfono de un contacto");
            System.out.println("7. Verificar si la agenda está llena");
            System.out.println("8. Ver espacios libres");
            System.out.println("9. Mostrar información de la agenda");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    añadirContacto();
                    break;
                case 2:
                    verificarExistenciaContacto();
                    break;
                case 3:
                    agenda.listarContactos();
                    break;
                case 4:
                    buscarContacto();
                    break;
                case 5:
                    eliminarContacto();
                    break;
                case 6:
                    modificarTelefono();
                    break;
                case 7:
                    if (agenda.agendaLlena()) {
                        System.out.println("La agenda está llena.");
                    } else {
                        System.out.println("La agenda NO está llena.");
                    }
                    break;
                case 8:
                    agenda.espaciosLibres();
                    break;
                case 9:
                    mostrarInfoAgenda();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }

            if (opcion != 0) {
                System.out.print("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcion != 0);

        scanner.close();
    }

    // Método para añadir un contacto
    private static void añadirContacto() {
        System.out.println("\n=== AÑADIR CONTACTO ===");

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine().trim();

        System.out.print("Ingrese el teléfono: ");
        String telefono = scanner.nextLine().trim();

        Contacto nuevoContacto = new Contacto(nombre, apellido, telefono);
        agenda.añadirContacto(nuevoContacto);
    }

    // Método para verificar si un contacto existe
    private static void verificarExistenciaContacto() {
        System.out.println("\n=== VERIFICAR EXISTENCIA DE CONTACTO ===");

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine().trim();

        Contacto contactoBusqueda = new Contacto(nombre, apellido, "");

        if (agenda.existeContacto(contactoBusqueda)) {
            System.out.println("El contacto '" + nombre + " " + apellido + "' SÍ existe en la agenda.");
        } else {
            System.out.println("El contacto '" + nombre + " " + apellido + "' NO existe en la agenda.");
        }
    }

    // Método para buscar un contacto
    private static void buscarContacto() {
        System.out.println("\n=== BUSCAR CONTACTO ===");

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine().trim();

        agenda.buscaContacto(nombre, apellido);
    }

    // Método para eliminar un contacto
    private static void eliminarContacto() {
        System.out.println("\n=== ELIMINAR CONTACTO ===");

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine().trim();

        Contacto contactoEliminar = new Contacto(nombre, apellido, "");
        agenda.eliminarContacto(contactoEliminar);
    }

    // Método para modificar el teléfono de un contacto
    private static void modificarTelefono() {
        System.out.println("\n=== MODIFICAR TELÉFONO ===");

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine().trim();

        System.out.print("Ingrese el nuevo teléfono: ");
        String nuevoTelefono = scanner.nextLine().trim();

        agenda.modificarTelefono(nombre, apellido, nuevoTelefono);
    }

    // Método para mostrar información de la agenda
    private static void mostrarInfoAgenda() {
        System.out.println("\n=== INFORMACIÓN DE LA AGENDA ===");
        System.out.println("Capacidad máxima: " + agenda.getCapacidadMaxima() + " contactos");
        System.out.println("Contactos actuales: " + agenda.getNumeroContactos());

        if (agenda.agendaLlena()) {
            System.out.println("Estado: LLENA");
        } else {
            System.out.println("Estado: CON ESPACIO DISPONIBLE");
        }

        agenda.espaciosLibres();
    }

    // Método auxiliar para leer un número entero
    private static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }
}