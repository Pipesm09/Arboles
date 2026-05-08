package arboles;

import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Arboles {

    public static void main(String[] args) {
        int opc = 0;

        Arbol Arbol1 = new Arbol();
        IngresarArbol(Arbol1);

        do {
            opc = Menu();
            switch (opc) {
                case 1:
                    Arbol1.InOrden(Arbol1.getRaiz());
                    break;
                case 2:
                    Arbol1.PreOrden(Arbol1.getRaiz());
                    break;
                case 3:
                    Arbol1.PosOrden(Arbol1.getRaiz());
                    break;
                case 4:

                    break;
                case 6:
                    Arbol1.mostrarArbol(Arbol1.getRaiz(), 0);
                    break;
                case 7:
                    Arbol1.mostrarUnHijoPorPreOrden(Arbol1.getRaiz());
                    break;
                case 8:
                    System.out.println(Arbol1.ContarDatosSoloHijoDerechoPreOrden(Arbol1.getRaiz()));
                    break;
                case 9:
                    char letra = JOptionPane
                            .showInputDialog("Ingrese el dato")
                            .toUpperCase()
                            .charAt(0);

                    int nivel = Arbol1.nivelDeNodo(Arbol1.getRaiz(), letra, 0);

                    if (nivel != -1) {
                        System.out.println("La letra " + letra + " esta en el nivel: " + nivel);
                    } else {
                        System.out.println("El dato no existe en el arbol");
                    }
                    break;
                case 10:
                    String input = JOptionPane.showInputDialog("Ingrese el carácter para buscar su hermano:");
                    char padre = input.charAt(0);
                    Arbol1.buscarHermano(padre);
                    break;
                case 11:
                    letra = JOptionPane
                            .showInputDialog("Ingrese el dato")
                            .toUpperCase()
                            .charAt(0);

                    int altura = Arbol1.alturaDato(Arbol1.getRaiz(), letra);

                    if (altura != -1) {
                        System.out.println("La altura de la letra " + letra + " es de: " + altura);
                    } else {
                        System.out.println("El dato no existe");
                    }
                    break;
                case 12:
                    letra = JOptionPane
                            .showInputDialog("Ingrese el dato")
                            .toUpperCase()
                            .charAt(0);

                    boolean existe = Arbol1.mostrarAncestros(
                            Arbol1.getRaiz(),
                            letra);

                    if (!existe) {
                        System.out.println("El dato no existe");
                    }
                    break;
                case 13:
                    letra = JOptionPane
                            .showInputDialog("Ingrese dato")
                            .toUpperCase()
                            .charAt(0);

                    Arbol1.insertar(letra);
                    break;
                case 0:
                    System.out.println("Salir");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }

        } while (opc != 0);
    }

    public static int Menu() {
        int opc = Integer.parseInt(JOptionPane.showInputDialog("****** Menu Principal ******\n"
                + "1. Recorrido InOrden.\n"
                + "2. Recorrido PreOrden\n"
                + "3. Recorrido PosOrden\n"
                + "4. Eliminar Termino\n"
                + "5. Mostrar Arbol\n"
                + "6. Mostrar arbol completo.\n"
                + "7. Mostrar los datos con un solo hijo por PreOrden. \n"
                + "8. Contar los datos con un solo hijo derecho. \n"
                + "9. Mostrar el nivel de un dato.\n"
                + "10. Mostrar el hermano de un dato ingresado por el usuario\n"
                + "11. Mostrar la altura de un dato.\n"
                + "12. Mostrar los ancestros de un dato. \n"
                + "13. Insertar un dato. \n"
                + "0. Salir.\n"
                + "Ingrese una opcion\n"));

        return opc;
    }

    public static void IngresarArbol(Arbol Arbol1) {
        String cadena = JOptionPane.showInputDialog("Ingrese la cadena del arbol");
        for (int i = 0; i < cadena.length(); i++) {
            char vc = cadena.charAt(i);
            Arbol1.ConstruirArbol(vc);
        }
    }
}
