package matrices;

public class matrizEnTripletas {
    private Tripleta V[];

    // t tiene el tamaño de la matriz, y su campo de valor es 0.
    // p tiene valores extras que son utiles para otros métodos
    // en la posición 0 de V se almacena el tamaño de la matriz.
    public matrizEnTripletas(Tripleta t) {
        int m = t.getFila();
        int n = t.getColumna();
        int p = m * n + 2;
        V = new Tripleta[p];
        V[0] = t;

        for (int i = 1; i <= p; i++) {
            V[i] = null;
        }

    }

    public void asignaTripleta(Tripleta t, int i) {
        V[i] = t;
    }

    public void setNumeroTripletas(int n) {
        V[0].setValor(n);
    }

    public int getFilas() {
        return V[0].getFila();
    }

    public int getColumnas() {
        return V[0].getColumna();
    }

    public int getNumeroTripletas() {
        return V[0].getValor();
    }

    public Tripleta getTripleta(int i) {
        return V[i];
    }

    public void muestraMatrizEnTripletas() {
        // el parametro de detención del ciclo es la cantidad de datos en la tripleta
        // (incluyendo los que son 0)
        for (int i = 1; i <= V[0].getValor(); i++) {
            System.out.println(V[i].getFila() + "-" + V[i].getColumna() + "-" + V[i].getValor());
        }
    }

    public void insertarTripleta(Tripleta TripletaPorInsertar) {

        int datos = getTripleta(0).getValor();
        int i = 1;
        
        Tripleta temp = getTripleta(i);

        while (i <= datos & temp.getFila() < TripletaPorInsertar.getFila()) {
            i = i + 1;
            temp = getTripleta(i);
        }

        while (i <= datos & temp.getFila() == TripletaPorInsertar.getFila()
                & temp.getColumna() < TripletaPorInsertar.getColumna()) {
           
            i = i + 1;
            temp = getTripleta(i);
        }
        
        int j = datos;
        while (j >= i) {
            V[j + 1] = V[j];
            j = j--;
        }
        
        V[i] = TripletaPorInsertar;
        datos = datos + 1;
        setNumeroTripletas(datos);
    }

    matrizEnTripletas suma(matrizEnTripletas b){
        

        return b;
    }

}
