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
        Nodo p = Arbol1.getRaiz();
        
        
        
        do{
            opc = Menu();
            switch(opc){
                case 1: 
                    Arbol1.InOrden(p);
                    break;
                case 2:
                    Arbol1.PreOrden(p);
                    break;
                case 3: 
                    Arbol1.PosOrden(p);
                    break;
                case 4: 
                    
                    break;
                case 6:
                    Arbol1.mostrarArbol(Arbol1.getRaiz(), 0);
                    break;
                case 0:
                    System.out.println("Salir");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
            
        } while( opc!=0 );
    }
    
    public static int Menu(){
        int opc = Integer.parseInt(JOptionPane.showInputDialog("****** Menu Principal ******\n" 
                + "1. Recorrido InOrden.\n"
                + "2. Recorrido PreOrden\n"
                + "3. Recorrido PosOrden\n"
                + "4. Eliminar Termino\n"
                + "5. Mostrar Arbol\n"
                + "6. Mostrar arbol completo.\n"
                + "7. Mostrar los datos con un solo hijo. \n"
                + "0. Salir.\n"
                + "Ingrese una opcion\n"));
        
    return opc;
    }
    
    public static void IngresarArbol(Arbol Arbol1){
        String cadena = JOptionPane.showInputDialog("Ingrese la cadena del arbol");
        for(int i = 0;i<cadena.length();i++){
          char vc = cadena.charAt(i);
          Arbol1.ConstruirArbol(vc);
      }
    }
}
