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

    public void mostrarUnHijoPorPreOrden(Nodo r) {
        if (r != null) {
            //condicion para que tenga un solo hijo es que el padre o tenga el hijo derecho y no el izquierdo o que tenga el izquierdo y no el derecho
            if (r.getLI() == null && r.getLD() != null || r.getLD() == null && r.getLI() != null) {
                System.out.println(r.getDato() + " ");
            }
            mostrarUnHijoPorPreOrden(r.getLI());
            mostrarUnHijoPorPreOrden(r.getLD());
        }
    }

    //retornara un entero que seran los datos contados
    public int ContarDatosSoloHijoDerechoPreOrden(Nodo r) {
        if (r == null) {
            //esto hace que si un hijo es null o no es derecho suma +0
            return 0;
        }
        int contador = 0;
        //este nunca vuelve a hacer 0, porque lo que se retorna son copias de el en memoria
        //condicion para tener un solo hijo derecho es que el izquierdo sea null
        if (r.getLI() == null && r.getLD() != null) {
            //si esto ocurre quiere decir que hay +1 hijo derecho
            contador = 1;
        }
        //recorrido PreOrden en este se pone += porque lo que retornaran en los otros metodos seran numero ya sea 1 o 0
        contador += ContarDatosSoloHijoDerechoPreOrden(r.getLI());
        contador += ContarDatosSoloHijoDerechoPreOrden(r.getLD());
        return contador;
    }

    public void buscarHermano(char datoABuscar) {
        //en este no hay Pre,In o PosOrden porque no vale la pena recorrer todo el arbol, sabiendo que ya tengo el nodo
        //por lo que simplemente buscar el padre del dato que decidira cual es el hermano
        if (this.Raiz == null) {
            System.out.println("Arbol vacio");
            return;
        }
        //valido si el dato es = a la raiz, esta no tiene hermanos :VVVVVVVVV
        if (this.Raiz.getDato() == datoABuscar) {
            System.out.println("La raiz no tiene hermanos");
            return;
        }
        //crear Nodo padre que viene de un metodo para encontrar al padre
        Nodo padre = encontrarPadre(Raiz, datoABuscar);
        //verifico si al final del metodo no se encontro padre
        if (padre == null) {
            System.out.println("El dato no existe en este arbol (no tiene padre).");
        } else {
            //verifico si la liga izq es !=null y es =al dato, porque si es asi el hermano es el derecho
            if (padre.getLI() != null && padre.getLI().getDato() == datoABuscar) {
                if (padre.getLD() != null) {
                    System.out.println("El hermano es: " + padre.getLD().getDato());
                } else {
                    System.out.println("El dato no tiene hermano.");
                }
            } else {
                //probar si es el hijo deerecho
                if (padre.getLD() != null && padre.getLD().getDato() == datoABuscar) {
                    if (padre.getLI() != null) {
                        System.out.println("El hermano es: " + padre.getLI().getDato());
                    } //verificar si siendo el hijo derecho no hay hijo izquierdo
                    else {
                        System.out.println("El dato no tiene hermano.");
                    }
                }
            }
        }
    }

    //este metodo como se supone que el arbol esta ordenado, basta con bajar por izquierda o derecha sin necesidad de un recorrida en especifico
    private Nodo encontrarPadre(Nodo r, char datoABuscar) {
        if (r == null) {
            return null; //confirma que el dato este en el arbol, de lo contaria retornara null
        }
        //misma condicion, ver si hijo izq o dere es != null y = al dato a buscar
        if (r.getLD() != null && r.getLD().getDato() == datoABuscar) {
            return r;//retornara r como padre
        }
        if (r.getLI() != null && r.getLI().getDato() == datoABuscar) {
            return r; //rotarnara r como padre
        }
        //pero si en los hijos de la raiz no esta el dato? se mira sie el dato es > 0 < al nodo actual y se baja entonces por izq o dere
        if (datoABuscar < r.getDato()) {
            return encontrarPadre(r.getLI(), datoABuscar);
        } else {
            return encontrarPadre(r.getLD(), datoABuscar);
        }

    }

    public int nivelDeNodo(Nodo raiz, char dato, int nivel) {
        // Si el nodo actual es null
        if (raiz == null) {
            return -1;
        }

        // Si encuentra el dato ingresado
        if (raiz.getDato() == dato) {
            return nivel;
        }

        //Empieza a buscar por la izquierda
        int izq = nivelDeNodo(raiz.getLI(), dato, nivel + 1);
        //Si no lo encontró a la izquierda (izq = -1)
        if (izq != -1) {
            //Si se cumple r.getdato == dato, devuelve nivel aquí
            return izq;
        }

        //Empieza a buscarlo por la derecha 
        return nivelDeNodo(raiz.getLD(), dato, nivel + 1);
    }

    public int alturaDato(Nodo r, char dato) {

        // buscar el nodo
        if (r == null) {
            return -1; //Si no existe el nodo
        }

        if (r.getDato() == dato) {
            return altura(r);
        }

        int izq = alturaDato(r.getLI(), dato);

        if (izq != -1) {
            return izq;
        }

        return alturaDato(r.getLD(), dato);
    }

    private int altura(Nodo r) {

        if (r == null) {
            return -1;
        }

        int izq = altura(r.getLI());
        int der = altura(r.getLD());

        if (izq > der) {
            return izq + 1;
        } else {
            return der + 1;
        }
    }

    public boolean mostrarAncestros(Nodo r, char dato) {

        if (r == null) {
            return false;
        }

        // si encontró el dato
        if (r.getDato() == dato) {
            return true;
        }

        // buscar izquierda o derecha
        if (mostrarAncestros(r.getLI(), dato)
                || mostrarAncestros(r.getLD(), dato)) {
            // este nodo es ancestro
            System.out.println(r.getDato());
            return true;
        }
        return false;
    }

    public void insertar(char dato) {
        Nodo p = Raiz;
        boolean continuar = true;
        // si el árbol está vacío
        if (Raiz == null) {
            Raiz = new Nodo(dato);
        } else {
            while (continuar) {
                // insertar izquierda
                if (dato < p.getDato()) {
                    if (p.getLI() == null) {
                        Nodo x = new Nodo(dato);
                        p.setLI(x);
                        continuar = false;
                    } else {
                        p = p.getLI();
                    }
                } // insertar derecha
                else if (dato > p.getDato()) {
                    if (p.getLD() == null) {
                        Nodo x = new Nodo(dato);
                        p.setLD(x);
                        continuar = false;
                    } else {
                        p = p.getLD();
                    }
                } // dato repetido
                else {

                    continuar = false;
                }
            }
        }
    }

    public void eliminar(char dato) {
        //metodo que sirve para cuando s elemine la raiz y no tener que redimensionar
        Raiz = eliminarRecursivo(Raiz, dato);
    }

    private Nodo eliminarRecursivo(Nodo r, char dato) {
        if (r == null) {
            return null;
        }
        //como es un arbol ORDENADO simplemente busco el dato en la izquierda o derecha
        if (dato < r.getDato()) {
            /*esto sirve para no redimensionar y simplemente lo que se elimine en sus hijos queda ahi y no pasa nada con lo anterior a este*/
            r.setLI(eliminarRecursivo(r.getLI(), dato));
        }
        else if(dato > r.getDato()){
            /*para lo mismo el hijo derecho retiene todo lo anterior y lo que se modifique despues de este solo afectera al hijo derecho que sera 
            siendo el hijo derecho*/
            r.setLD(eliminarRecursivo(r.getLD(), dato));
        }
        else//no es ni < ni > entonces es =, esto implica que lo encontramos y necesitamos saber si es hoja o padre
            //si es hoja
            if(r.getLI()==null && r.getLD()== null){
                return null; //esto indica al padre que sigue siendo padre o raiz
            }
        //si no se cumple se sabe entonces que uno de sus hijos es !=null ver cual es
        if(r.getLI()==null){
            //como el izq==null significa que lo reemplazara su hijo derecho (UNICA ALTERNATIVA)
            return r.getLD();
        }
        else if(r.getLD()==null){
            //como el dere==null significa que lo reemplzara su hijo izquierdo
            return r.getLI();
        }
        //si no cumple ninguna significa que tiene los dos hijos != null y toco ver cual lo va reemplazar utilizando el protocolo de bajar primero por la derecha
        r.setDato(enconrarMinimohijo(r.getLD()));
        //baja por derecha y luego por izquierda dentor del metodo y el que encuentre mas abajo lo vuelve el padre o raiz en el espacio que deja el nodo que eliminamos
        r.setLD(eliminarRecursivo(r.getLD(), r.getDato()));
        //esto se hace para "borrar de memoria" al nodo que reemplazar al nodo que borramos, por si este tiene mas hijos y toca reemplazarlo a el
        return r;
    }

    private char enconrarMinimohijo(Nodo p) {
    /*el protocolo indica que debo bajar en sentido contrario (baje una vez por derecha cuando llame al metedo
    entonces debo bajar por izquierda hasta encontrar el minimo hijo izquierdo*/
    char min= p.getDato();
    //recorre hasta que no pueda mas, luego retornara este hijo mas al fondo
    while(p.getLI()!=null){
        min=p.getLI().getDato();
        p=p.getLI();
    }
    return min;
}
}
