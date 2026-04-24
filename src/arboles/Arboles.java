package arboles;

import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Arboles {

    public static void main(String[] args) {
        int opc = 0;
        
        IngresarArbol();
        
        do{
            opc = Menu();
            switch(opc){
                case 1: 
                    break;
                case 2:
                    break;
                case 3: 
                    break;
                case 4: 
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
        int opc = Integer.parseInt(JOptionPane.showInputDialog("****** Menu Principal ******" 
                + "1. Recprrodp InOrden."
                + "2. Recorrido PreOrden"
                + "3. Recorrido PosOrden"
                + "4. Eliminar Termino"
                + "5. Mostrar Arbol"
                + "0. Salir."
                + "Ingrese una opcion"));
        
    return opc;
    }
    
    public static void IngresarArbol(){
        JOptionPane.showInputDialog("Ingrese la cadena del arbol");
        char Vc[];
    }
}
