package arboles;

public class Arbol {

    private Nodo Raiz;

    public Arbol() {
        Raiz = null;
    }

    public Nodo getRaiz() {
        return Raiz;
    }

    public void setRaiz(Nodo Raiz) {
        this.Raiz = Raiz;
    }

    public void ConstruirArbol(char vc) {
        Nodo p = Raiz;
        boolean letra = true;
        if (p == null) {
            Raiz = new Nodo(vc);
        } else {
            while (letra) {
                //si menor va a la izquierda
                if (vc < p.getDato()) {
                    //inserta si en la izquierda no hay nada
                    if (p.getLI() == null) {
                        Nodo x = new Nodo(vc);
                        p.setLI(x);
                        //una vez que inserte se sale del while
                        letra = false;
                    } else {
                        //quiere decir que hay nodo, entonces simplemente se avanza por izquierda
                        p = p.getLI();
                    }
                    //si no es menor, entonces es mayuor y va a la derecha
                } else if (vc > p.getDato()) {
                    if (p.getLD() == null) {
                        //si no hay nada en la derecha insertar
                        Nodo x = new Nodo(vc);
                        p.setLD(x);
                        letra = false;
                    } else {
                        //hay nodo en la derecha, entonces se avanza
                        p = p.getLD();
                    }
                    //si no es mayor ni menor entonces es igual, ya hay nodo simplemente se avanza
                } else {
                    letra = false;
                }
            }
        }
    }

    //recorridos
    public void mostrar(Nodo p) {
        if (p != null) {
            System.out.println(p.getDato() + "");
        }
    }

    //hijo izquierdo, raiz, hijo derecho
    public void InOrden(Nodo p) {
        /*se situa en el hijo izquierdo de la raiz que siempre es el primer nodo que se recibe, luego va abajar hasta que no
        haya mas hijos izquierdos y mostrar este, luego desapilara y mostrara la raiz y luego sus hijos derechos*/
        if (p != null) {
            InOrden(p.getLI());
            mostrar(p);
            InOrden(p.getLD());
        }
    }

    public void PosOrden(Nodo p) {
        /*se situa en el hijo izquierdo de la raiz que siempre es el primer nodo que se recibe, luego va abajar hasta que no
        haya mas hijos izquierdos o derechos y mostrar primero los hijos izquierdos, luego los derechos y por ultimo la raiz*/
        if (p != null) {
            PosOrden(p.getLI());
            PosOrden(p.getLD());
            mostrar(p);
        }
    }

    public void PreOrden(Nodo p) {
        /*muestra primero la raiz luego baja por el izquierdos y los muestra, luego lo mismo con los derechos*/
        if (p != null) {
            mostrar(p);
            PreOrden(p.getLI());
            PreOrden(p.getLD());

        }
    }

    public void mostrarArbol(Nodo arbol, int cont) {
        if (arbol == null) {
            return;
        } else {
            // primero derecha
            mostrarArbol(arbol.getLD(), cont + 1);

            // imprimir espacios
            for (int i = 0; i < cont; i++) {
                System.out.print("   ");
            }

            // imprimir dato
            System.out.println(arbol.getDato());

            // luego izquierda
            mostrarArbol(arbol.getLI(), cont + 1);
        }
    }

}
