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
                    Arbol1.mostrarArbol();
                    break;
                case 5:
                    Arbol1.mostrarUnHijoPorPreOrden(Arbol1.getRaiz());
                    break;
                case 6:
                    System.out.println(Arbol1.ContarDatosSoloHijoDerechoPreOrden(Arbol1.getRaiz()));
                    break;
                case 7:
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
                case 8:
                    String input = JOptionPane.showInputDialog("Ingrese el carácter para buscar su hermano:");
                    char padre = input.charAt(0);
                    Arbol1.buscarHermano(padre);
                    break;
                case 9:
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
                case 10:
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
                case 11: // Eliminar Termino
                    String eliminarInput = JOptionPane.showInputDialog("Ingrese el carácter a eliminar:");
                    if (eliminarInput != null && !eliminarInput.isEmpty()) {
                        char letraAEliminar = eliminarInput.charAt(0);
                        Arbol1.eliminar(letraAEliminar);
                        JOptionPane.showMessageDialog(null, "Operación realizada.");
                    }
                    break;
                case 12:
                    letra = JOptionPane
                            .showInputDialog("Ingrese dato")
                            .toUpperCase()
                            .charAt(0);

                    Arbol1.insertar(letra);
                    break;
                case 13:
                    System.out.println("El numero de hojas en el arbol es: " + Arbol1.contarHojas(Arbol1.getRaiz()));
                    break;
                case 14:
                    System.out.println("El numero de padres en el arbol es: " + Arbol1.contarPadres(Arbol1.getRaiz()));
                    break;
                case 15:
                    Arbol1.verificarSiEsPerfecto();
                    break;
                case 16:

                    char dato = JOptionPane
                            .showInputDialog("Ingrese el dato")
                            .toUpperCase()
                            .charAt(0);

                    System.out.println("Cantidad de descendientes: " + Arbol1.contarDescendientes(dato));

                    Arbol1.mostrarDescendientes(dato);

                    break;
                case 17:
                    String datoP = JOptionPane.showInputDialog("¿De quién quieres ver los primos hermanos?");
                    if (datoP != null && !datoP.isEmpty()) {
                        Arbol1.mostrarPrimosHermanos(datoP.charAt(0));
                    }
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
                + "4. Mostrar arbol completo.\n"
                + "5. Mostrar los datos con un solo hijo por PreOrden. \n"
                + "6. Contar los datos con un solo hijo derecho. \n"
                + "7. Mostrar el nivel de un dato.\n"
                + "8. Mostrar el hermano de un dato ingresado por el usuario\n"
                + "9. Mostrar la altura de un dato.\n"
                + "10. Mostrar los ancestros de un dato. \n"
                + "11. Eliminar un dato. \n"
                + "12. Insertar un dato.\n"
                + "13. Contar las hojas del arbol.\n"
                + "14. Contar los padres del arbol. \n"
                + "15. Ver si el arbol es perfecto. \n"
                + "16. Mostrar y contar descendientes de un dato.\n"
                + "17. Mostrar los primos hermanos.\n"
                + "0. Salir.\n"
                + "Ingrese una opcion\n"));

        return opc;
    }

    public static void IngresarArbol(Arbol Arbol1) {
        String cadena = JOptionPane.showInputDialog("Ingrese la cadena del arbol");
        for (int i = 0; i < cadena.length(); i++) {
            char vc = cadena.charAt(i);
            Arbol1.ConstruirArbol(vc);
            Arbol1.setRaiz(Arbol1.aplicarBalanceo(Arbol1.getRaiz()));//aqui se aplica el balanceo papu :V
        }
    }
}
