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

    public void mostrarArbol() {
        if (Raiz == null) {
            System.out.println("            >> El árbol está vacío <<");
        } else {
            // La raíz no tiene "lado" ni "prefijo", empezamos desde ahí
            imprimirNodoMelo(Raiz, "", true, "RAIZ");
        }
        System.out.println("\n      =========================================\n");
    }

    private void imprimirNodoMelo(Nodo n, String prefijo, boolean esUltimo, String lado) {
        if (n == null) {
            return;
        }

        // 1. Identificamos si es Padre [P] o Hoja (L)
        boolean tieneHijos = (n.getLI() != null || n.getLD() != null);
        String decorador = tieneHijos ? "[Padre:] " : "(Termina rama) ";

        // 2. Imprimimos la línea del nodo
        System.out.print(prefijo);

        // Dibujamos el conector de rama (excepto para la raíz principal)
        if (!lado.equals("RAIZ")) {
            System.out.print(esUltimo ? "└── " : "├── ");
            System.out.print(lado + " ──► "); // Flecha indicadora de dirección
        }

        // Imprimimos el dato con su decorador
        System.out.println(decorador + n.getDato());

        // 3. Preparamos el prefijo para los hijos (mantiene las líneas verticales)
        String nuevoPrefijo = prefijo + (lado.equals("RAIZ") ? "" : (esUltimo ? "    " : "│   "));

        // 4. Lógica de recursividad para los hijos
        // Procesamos primero el DERECHO (aparece arriba) y luego el IZQUIERDO (abajo)
        // El derecho es el "último" de su nivel SOLO si no hay izquierdo después de él.
        boolean tieneDerecho = (n.getLD() != null);
        boolean tieneIzquierdo = (n.getLI() != null);

        if (tieneDerecho) {
            // Es último solo si no hay hermano izquierdo
            imprimirNodoMelo(n.getLD(), nuevoPrefijo, !tieneIzquierdo, "D");
        }

        if (tieneIzquierdo) {
            // El izquierdo siempre es el último en mostrarse de su nivel
            imprimirNodoMelo(n.getLI(), nuevoPrefijo, true, "I");
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
        } else if (dato > r.getDato()) {
            /*para lo mismo el hijo derecho retiene todo lo anterior y lo que se modifique despues de este solo afectera al hijo derecho que sera 
            siendo el hijo derecho*/
            r.setLD(eliminarRecursivo(r.getLD(), dato));
        } else {//no es ni < ni > entonces es =, esto implica que lo encontramos y necesitamos saber si es hoja o padre
            //si es hoja
            if (r.getLI() == null && r.getLD() == null) {
                return null; //esto indica al padre que sigue siendo padre o raiz
            }
            //si no se cumple se sabe entonces que uno de sus hijos es !=null ver cual es
            if (r.getLI() == null) {
                //como el izq==null significa que lo reemplazara su hijo derecho (UNICA ALTERNATIVA)
                return r.getLD();
            } else if (r.getLD() == null) {
                //como el dere==null significa que lo reemplzara su hijo izquierdo
                return r.getLI();
            }
            //si no cumple ninguna significa que tiene los dos hijos != null y toco ver cual lo va reemplazar utilizando el protocolo de bajar primero por la derecha
            r.setDato(encontrarMinimohijo(r.getLD()));
            //baja por derecha y luego por izquierda dentor del metodo y el que encuentre mas abajo lo vuelve el padre o raiz en el espacio que deja el nodo que eliminamos
            r.setLD(eliminarRecursivo(r.getLD(), r.getDato()));
            //esto se hace para "borrar de memoria" al nodo que reemplazar al nodo que borramos, por si este tiene mas hijos y toca reemplazarlo a el
        }
        return r;

    }

    private char encontrarMinimohijo(Nodo p) {
        /*el protocolo indica que debo bajar en sentido contrario (baje una vez por derecha cuando llame al metedo
    entonces debo bajar por izquierda hasta encontrar el minimo hijo izquierdo*/
        char min = p.getDato();
        //recorre hasta que no pueda mas, luego retornara este hijo mas al fondo
        while (p.getLI() != null) {
            min = p.getLI().getDato();
            p = p.getLI();
        }
        return min;
    }

    public int contarHojas(Nodo r) {

        if (r == null) {
            return 0;
        }

        // si no tiene hijos es hoja
        if (r.getLI() == null && r.getLD() == null) {
            return 1;
        }

        return contarHojas(r.getLI())
                + contarHojas(r.getLD());
    }

    public int contarPadres(Nodo r) {

        if (r == null) {
            return 0;
        }

        int contador = 0;

        // si tiene al menos un hijo
        if (r.getLI() != null || r.getLD() != null) {
            contador = 1;
        }

        contador += contarPadres(r.getLI());
        contador += contarPadres(r.getLD());

        return contador;
    }
//FB  xdddxd, para esto necesito priumero el hijo con mas altura

    private int obtenerAltura(Nodo r) {
        if (r == null) {
            return 0;//es una hoja
        }
        return Math.max(obtenerAltura(r.getLI()), obtenerAltura(r.getLD())) + 1;
        //baja tanto por izquierda como por derecha que si no es una hoja !=null el +1 es el que le suma la altura con respecto a la raiz
        //luego los compara y manda el maximo
    }

    //metodo FB
    public int obtenerFactorBalance(Nodo r) {
        if (r == null) {
            return 0; //balanceado el nodo
        }
        //mando al hijo izquierdo -hijo derecho y este me dara si lo tengo que balancear o no 
        return obtenerAltura(r.getLI()) - obtenerAltura(r.getLD());
    }

    //rotacion simple a la derecha, cuando el padre es +2 y si hijo mas alto  es +1 con nodo r=desbalanceado
    private Nodo R_D(Nodo r) {
        Nodo nuevaRaiz = r.getLI(); //conecto por el hijo izquierdo al padre para que no se pierda
        //ademas se liga por la izquierda porque tiene el mayor peso siempre por eso es +2
        r.setLI(nuevaRaiz.getLD());//mando lo que este a la derecha del nuevo padre a que sea ahora lo que este como hijo izquierdo de el anterior padre para que no se desbalancee 
        nuevaRaiz.setLD(r);
        return nuevaRaiz; //para mirar si lo que se modifico es la raiz principal
    }
    //rotacion simple a la izquierda, cuando el padre es -2 y su hijo mas alto es -1 con nodo r=desbalanceado

    private Nodo R_I(Nodo r) {
        Nodo nuevaRaiz = r.getLD();//lo mismo
        r.setLD(nuevaRaiz.getLI());//aqui se liga lo que este a la izquierda de la nuevaRaiz ya que la raiz o padre anterior se setteara en ese lugar
        nuevaRaiz.setLI(r);
        return nuevaRaiz;
    }

    //rotacion doble a la derecha, consta de una raiz=2 e hijo mas alto=-1, entonces primero se endereza su hijo izquierdo (el que tiene mas peso)
    //para luego hacer otro movimiento ahora con el nieto que sera la nueva raiz
    private Nodo R_D_D(Nodo r) {
        r.setLI(R_I(r.getLI()));//aqui es donde se endereza la primera parte
        return R_D(r);
    }

    //rotacion doble a la izquierda, consta de una raiz=-2 e hijo mas alto=+1, entonces primero se enderezara el hijo derecho, luego el nieto pasara  a hacer la nueva raiz
    private Nodo R_D_I(Nodo r) {
        r.setLD(R_D(r.getLD()));//aqui se le manda solo para que enderece al hijo mas alto (el derecho donde esta todo el peso)
        return R_I(r);
    }

    //falta determinar cual caso corresponde
    private int determinarCaso(Nodo r) {

        int FB = obtenerFactorBalance(r);

        // Desbalanceado hacia izquierda
        if (FB == 2) {

            int FBHijo = obtenerFactorBalance(r.getLI());

            // Rotación simple derecha
            if (FBHijo >= 0) {
                return 1;
            } // Rotación doble derecha
            else {
                return 2;
            }
        } // Desbalanceado hacia derecha
        else if (FB == -2) {

            int FBHijo = obtenerFactorBalance(r.getLD());

            // Rotación simple izquierda
            if (FBHijo <= 0) {
                return 3;
            } // Rotación doble izquierda
            else {
                return 4;
            }
        }

        return 0;
    }

    //switch cases, recorriendo en PosOrden, porque el insertar no es iterativo y el metodo FB se debe hacer en cada momento y como no se sabe la altura de un padre o una raiz
    //sin conocer los hijos, se comienza con los Hijos (PosOrden)
    public Nodo aplicarBalanceo(Nodo r) {
        if (r == null) {
            return null;
        }
        //recorrido PosOrden
        r.setLI(aplicarBalanceo(r.getLI()));
        r.setLD(aplicarBalanceo(r.getLD()));
        //una vez que desapile s eencontrar con el padre que es este el que decide si hay o no balanceo
        int caso = determinarCaso(r);//r=padre
        switch (caso) {
            case 1:
                return R_D(r);
            case 2:
                return R_D_D(r);
            case 3:
                return R_I(r);
            case 4:
                return R_D_I(r);
            default:
                return r;
        }
    }

    public void mostrarPrimos(char datoBuscado) {
        if (Raiz == null || Raiz.getDato() == datoBuscado) {
            System.out.println("No tiene primos.");
            return;
        }

        // Paso 1: Obtener el nivel del dato (ya tenemos este método)
        int nivelCousin = obtenerNivel(Raiz, datoBuscado, 0);

        // Paso 2: Obtener el padre del dato (ya tenemos este método)
        Nodo padreDelDato = encontrarPadre(Raiz, datoBuscado);

        if (nivelCousin <= 1 || padreDelDato == null) {
            System.out.println("El dato no tiene primos (es la raíz o un hijo directo de la raíz).");
        } else {
            System.out.print("Los primos de '" + datoBuscado + "' son: ");
            // Paso 3: Buscar en el árbol nodos en ese nivel que no sean hijos de ese padre
            buscarCousinsRecursivo(Raiz, padreDelDato, nivelCousin, 0);
            System.out.println();
        }
    }

// Método auxiliar para imprimir los primos
    private void buscarCousinsRecursivo(Nodo actual, Nodo padreProhibido, int nivelDestino, int nivelActual) {
        if (actual == null) {
            return;
        }

        // Nos detenemos un nivel ANTES de los primos para revisar los padres
        if (nivelActual == nivelDestino - 1) {
            // Si este nodo NO es el padre del dato buscado, sus hijos son primos
            if (actual != padreProhibido) {
                if (actual.getLI() != null) {
                    System.out.print(actual.getLI().getDato() + " ");
                }
                if (actual.getLD() != null) {
                    System.out.print(actual.getLD().getDato() + " ");
                }
            }
            return;
        }

        // Seguimos bajando por el árbol
        buscarCousinsRecursivo(actual.getLI(), padreProhibido, nivelDestino, nivelActual + 1);
        buscarCousinsRecursivo(actual.getLD(), padreProhibido, nivelDestino, nivelActual + 1);
    }
    // Este es el método que busca el nivel de un dato específico
// Retorna el número del nivel (0, 1, 2...) o -1 si el dato no existe.

    public int obtenerNivel(Nodo actual, char datoBuscado, int nivelActual) {
        // 1. Si llegamos a un espacio vacío, el dato no está
        if (actual == null) {
            return -1;
        }

        // 2. Si lo encontramos, devolvemos el contador que traemos
        if (actual.getDato() == datoBuscado) {
            return nivelActual;
        }

        // 3. Si no es este, buscamos en los hijos aumentando el nivel en 1
        if (datoBuscado < actual.getDato()) {
            // Buscamos por la izquierda
            return obtenerNivel(actual.getLI(), datoBuscado, nivelActual + 1);
        } else {
            // Buscamos por la derecha
            return obtenerNivel(actual.getLD(), datoBuscado, nivelActual + 1);
        }
    }

    public void verificarSiEsPerfecto() {
        // 1. Calculamos la profundidad de la hoja más a la izquierda
        int d = profundidadIzquierda(Raiz);

        // 2. Llamamos al método recursivo
        if (esPerfecto(Raiz, d, 0)) {
            System.out.println("El árbol es PERFECTO.");
        } else {
            System.out.println("El árbol NO es perfecto.");
        }
    }

    private int profundidadIzquierda(Nodo nodo) {
        int d = 0;
        while (nodo != null) {
            d++;
            nodo = nodo.getLI();
        }
        return d;
    }

    private boolean esPerfecto(Nodo r, int profundidad, int nivelActual) {
        if (r == null) {
            return true;
        }

        if (r.getLI() == null && r.getLD() == null) {
            return (profundidad == nivelActual + 1);
        }

        if (r.getLI() == null || r.getLD() == null) {
            return false;
        }

        return esPerfecto(r.getLI(), profundidad, nivelActual + 1)
                && esPerfecto(r.getLD(), profundidad, nivelActual + 1);
    }

    public void mostrarDescendientes(char dato) {

        // Buscamos el nodo que contiene el dato ingresado
        Nodo nodo = buscarNodo(Raiz, dato);

        // Si no existe el nodo, se informa y se termina el método
        if (nodo == null) {
            System.out.println("El dato no existe en el árbol");
            return;
        }

        // Mensaje inicial
        System.out.println("Descendientes de " + dato + ":");

        // Se recorren primero los hijos del nodo encontrado (izquierdo y derecho), ya que los descendientes son todos sus subárboles
        mostrarDescendientesRecursivo(nodo.getLI());
        mostrarDescendientesRecursivo(nodo.getLD());
    }

// Recorrido para mostrar descendientes
    private void mostrarDescendientesRecursivo(Nodo r) {

        // Caso base: si el nodo es nulo, se detiene la recursión
        if (r != null) {

            // Se muestra el dato del nodo actual
            System.out.println(r.getDato());

            // Se sigue recorriendo todo el subárbol izquierdo
            mostrarDescendientesRecursivo(r.getLI());

            // Se sigue recorriendo todo el subárbol derecho
            mostrarDescendientesRecursivo(r.getLD());
        }
    }

// Buscar un nodo en el arbol
    private Nodo buscarNodo(Nodo r, char dato) {

        // Caso base: nodo nulo significa que no se encontró
        if (r == null) {
            return null;
        }

        // Si el nodo actual es el que buscamos, lo retornamos
        if (r.getDato() == dato) {
            return r;
        }

        // Como es un árbol binario de búsqueda: si el dato es menor, se busca en el subárbol izquierdo
        if (dato < r.getDato()) {
            return buscarNodo(r.getLI(), dato);
        } // si es mayor, se busca en el subárbol derecho
        else {
            return buscarNodo(r.getLD(), dato);
        }
    }

// Contar descendientes
    public int contarDescendientes(char dato) {

        // Buscamos el nodo objetivo
        Nodo nodo = buscarNodo(Raiz, dato);

        // Si no existe, retornamos -1 como indicador de error
        if (nodo == null) {
            return -1;
        }

        // Restamos 1 porque el método recursivo cuenta también el nodo raíz
        return contarDescendientesRecursivo(nodo) - 1;
    }

// Contar nodos en el sub arbol
    private int contarDescendientesRecursivo(Nodo r) {

        // Caso base: nodo nulo no cuenta
        if (r == null) {
            return 0;
        }

        // Se cuenta el nodo actual (1)
        // más todos los nodos del subárbol izquierdo
        // más todos los nodos del subárbol derecho
        return 1
                + contarDescendientesRecursivo(r.getLI())
                + contarDescendientesRecursivo(r.getLD());
    }
    //es solo mostrar los nodos del mismo nivel y qeu sus padres sean hermanos para eso deben de tener el abuelo...

    public void mostrarPrimosHermanos(char datoBuscado) {
        if (Raiz == null || Raiz.getDato() == datoBuscado) {
            System.out.println("La raíz no tiene primos hermanos.");
            return;
        }

        // 1. Encontrar el Padre
        Nodo padre = encontrarPadre(Raiz, datoBuscado);
        if (padre == null || padre == Raiz) {
            System.out.println("No tiene primos hermanos (es hijo de la raíz).");
            return;
        }

        // 2. Encontrar el Abuelo (el padre del padre)
        Nodo abuelo = encontrarPadre(Raiz, padre.getDato());
        if (abuelo == null) {
            System.out.println("No tiene abuelo, por lo tanto no hay primos hermanos.");
            return;
        }

        // 3. Identificar al Tío (el otro hijo del abuelo)
        Nodo tio = null;
        if (abuelo.getLI() == padre) {
            tio = abuelo.getLD(); // Si mi padre es el izquierdo, mi tío es el derecho
        } else {
            tio = abuelo.getLI(); // Si mi padre es el derecho, mi tío es el izquierdo
        }

        // 4. Mostrar los hijos del Tío (Estos son los verdaderos primos hermanos)
        if (tio == null || (tio.getLI() == null && tio.getLD() == null)) {
            System.out.println("No tiene primos hermanos (el tío no existe o no tiene hijos).");
        } else {
            System.out.print("Primos hermanos reales de '" + datoBuscado + "': ");
            if (tio.getLI() != null) {
                System.out.print(tio.getLI().getDato() + " ");
            }
            if (tio.getLD() != null) {
                System.out.print(tio.getLD().getDato() + " ");
            }
            System.out.println();
        }
    }
}
