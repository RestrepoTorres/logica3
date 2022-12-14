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

    public int getNumeroFilas() {
        return V[0].getFila();
    }

    public int getNumeroColumnas() {
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

    /*
     * todo lo que tenga A es referente a la matriz sobre la cual se esta llamando
     * este metodo, todo lo que tenga B es referente la matriz a sumar, todo lo que
     * tenga C es referente a la matriz resultado
     */
    matrizEnTripletas suma(matrizEnTripletas b) {
        matrizEnTripletas a = this;
        int filaA, filaB, columnaA, columnaB, valorA, valorB;
        Tripleta tripletaA, tripletaB;
        if (!mismasDimensiones(a, b)) {
            System.out.println("matrices con diferentes dimensiones, no se pueden sumar");
            return null;
        }
        matrizEnTripletas c = new matrizEnTripletas(new Tripleta(a.getNumeroFilas(), a.getNumeroColumnas(), 0));
        int indiceA = 1;
        int indiceB = 1;
        int elementosEnC = 0;
        while ((indiceA <= a.getNumeroTripletas()) & (indiceB <= b.getNumeroTripletas())) {
            tripletaA = a.getTripleta(indiceA);
            tripletaB = b.getTripleta(indiceB);
            filaA = tripletaA.getFila();
            filaB = tripletaB.getFila();
            elementosEnC++;
            switch (comparar(filaA, filaB)) {
                case -1:
                    c.asignaTripleta(tripletaA, elementosEnC);
                    indiceA++;
                    break;
                case 1:
                    c.asignaTripleta(tripletaB, elementosEnC);
                    indiceB++;
                    break;

                case 0:
                    columnaA = tripletaA.getColumna();
                    columnaB = tripletaB.getColumna();
                    switch (comparar(columnaA, columnaB)) {
                        case -1:
                            c.asignaTripleta(tripletaA, elementosEnC);
                            indiceA++;
                            break;
                        case 1:
                            c.asignaTripleta(tripletaB, elementosEnC);
                            indiceB++;
                            break;
                        case 0:
                            valorA = (int) tripletaA.getValor();
                            valorB = (int) tripletaB.getValor();
                            int suma = valorA + valorB;
                            if (suma != 0) {
                                c.asignaTripleta(new Tripleta(filaA, columnaA, suma), elementosEnC);
                            } else {
                                elementosEnC--;
                            }
                            indiceA++;
                            indiceB++;
                            break;
                    }
            }
        }
        while (indiceA <= a.getNumeroTripletas()) {
            c.asignaTripleta(a.getTripleta(indiceA), elementosEnC);
            elementosEnC++;
            indiceA++;
        }
        while (indiceB <= b.getNumeroTripletas()) {
            c.asignaTripleta(b.getTripleta(indiceB), elementosEnC);
            elementosEnC++;
            indiceB++;
        }
        c.setNumeroTripletas(elementosEnC);
        return c;
    }

    public int comparar(int d1, int d2) {
        if (d1 > d2) {
            return -1;
        }
        if (d1 == d2) {
            return 0;
        }
        return 1;
    }

    public matrizEnTripletas transpuesta() {
        Tripleta tPorInsertar = new Tripleta(this.getNumeroColumnas(), this.getNumeroFilas(), 0);
        matrizEnTripletas b = new matrizEnTripletas(tPorInsertar);
        for (int i = 1; i <= this.getNumeroTripletas(); i++) {
            tPorInsertar = this.getTripleta(i);
            tPorInsertar = new Tripleta(tPorInsertar.getColumna(), tPorInsertar.getFila(), tPorInsertar.getValor());
            b.insertarTripleta(tPorInsertar);
        }
        return b;
    }

    public matrizEnTripletas transpuestaMedia() {
        Tripleta tTemp = new Tripleta(this.getNumeroColumnas(), this.getNumeroFilas(), this.getNumeroTripletas());
        matrizEnTripletas b = new matrizEnTripletas(tTemp);
        int k = 0;
        for (int i = 1; i <= this.getNumeroColumnas(); i++) {
            for (int j = 1; j <= this.getNumeroTripletas(); j++) {
                tTemp = getTripleta(i);
                if (tTemp.getColumna() == i) {
                    k = k + 1;
                    b.asignaTripleta(new Tripleta(tTemp.getColumna(), tTemp.getFila(), tTemp.getValor()), k);
                }
            }
        }
        return b;
    }

    public matrizEnTripletas transpuestaRapida() {
        Tripleta ti;
        ti = new Tripleta(this.getNumeroColumnas(), this.getNumeroFilas(), this.getNumeroTripletas());
        matrizEnTripletas b = new matrizEnTripletas(ti);
        int[] s = new int[this.getNumeroColumnas() + 1]; // elementos en cada columna
        int[] t = new int[this.getNumeroColumnas() + 1]; //
        for (int i = 1; i <= this.getNumeroTripletas(); i++) {
            ti = this.getTripleta(i);
            s[ti.getColumna()] = s[ti.getColumna()] + 1;
        }
        t[1] = 1;
        for (int i = 2; i <= this.getNumeroColumnas(); i++) {
            t[i] = t[i - 1] + s[i - 1];
        }
        for (int i = 1; i <= this.getNumeroTripletas(); i++) {
            ti = this.getTripleta(i);
            int j = ti.getColumna();
            b.asignaTripleta(new Tripleta(ti.getColumna(), ti.getFila(), ti.getValor()), t[j]);
            t[j] = t[j] + 1;
        }
        return b;
    }

    Boolean mismasDimensiones(matrizEnTripletas a, matrizEnTripletas b) {
        if ((a.getNumeroFilas() == b.getNumeroFilas()) & (a.getNumeroColumnas() == b.getNumeroColumnas())) {
            return true;
        }
        return false;
    }

    matrizEnTripletas multiplicacionPorEscalar(int a) {
        int numElementos = this.getNumeroTripletas();
        matrizEnTripletas c = new matrizEnTripletas(this.V[0]);
        for (int i = 1; i <= numElementos; i++) {
            Tripleta temp = new Tripleta(this.getTripleta(i).getFila(), this.getTripleta(i).getColumna(),
                    this.getTripleta(i).getValor() * a);
            c.asignaTripleta(temp, i);
        }
        return c;
    }

    matrizEnTripletas resta(matrizEnTripletas b) {
        matrizEnTripletas c = b.multiplicacionPorEscalar(-1);
        return this.suma(c);
    }

    public matrizEnTripletas intercambioDeColumnas(int columnaA, int columnaB) {
        matrizEnTripletas b = new matrizEnTripletas(
                new Tripleta(this.getNumeroFilas(), this.getNumeroColumnas(), this.getNumeroTripletas()));
        for (int i = 1; i <= this.getNumeroTripletas(); i++) {
            Tripleta d = this.getTripleta(i);
            if (d.getColumna() == columnaA) {
                b.insertarTripleta(new Tripleta(d.getFila(), columnaB, d.getValor()));
                break;
            }
            if (d.getColumna() == columnaB) {
                b.insertarTripleta(new Tripleta(d.getFila(), columnaA, d.getValor()));
                break;
            } else {
                b.insertarTripleta(this.getTripleta(i));
            }
        }
        return b;
    }
    public matrizEnTripletas intercambioDeFilas(int filaA, int filaB) {
        matrizEnTripletas b = new matrizEnTripletas(
                new Tripleta(this.getNumeroFilas(), this.getNumeroColumnas(), this.getNumeroTripletas()));
        for (int i = 1; i <= this.getNumeroTripletas(); i++) {
            Tripleta d = this.getTripleta(i);
            if (d.getFila() == filaA) {
                b.insertarTripleta(new Tripleta(filaB, d.getColumna(), d.getValor()));
                break;
            }
            if (d.getFila() == filaB) {
                b.insertarTripleta(new Tripleta(filaA, d.getColumna(), d.getValor()));
                break;
            } else {
                b.insertarTripleta(this.getTripleta(i));
            }
        }
        return b;
    }
}
