package arboles;


public class Arbol {
    private Nodo Raiz;
    
    public Arbol(){
        Raiz = null;
    }

    public Nodo getRaiz() {
        return Raiz;
    }

    public void setRaiz(Nodo Raiz) {
        this.Raiz = Raiz;
    }
    
    public  void ConstruirArbol(char vc){
        boolean letra = true;
      if(this.Raiz==null){
          Raiz = new Nodo(vc);
      }
      else{
          Nodo p = Raiz;
          Nodo padre = null;
          while(letra){
              padre = p;
              if(p.getDato() < vc){
                 Nodo x = new Nodo(vc);
                 p.setLI(x);
                 p = p.getLI();
                 letra = false;
              }
              else{
                  if(p.getDato() > vc){
                      Nodo x = new Nodo(vc);
                      p.setLD(x);
                      p = p.getLD();
                      letra = false; 
                  }
              }
          }
      }
      
      
    }
}
