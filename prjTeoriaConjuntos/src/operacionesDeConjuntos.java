import java.util.Arrays;
import java.util.Scanner;

public class operacionesDeConjuntos {
    public static void inputData() {
        try (Scanner read = new Scanner(System.in)) {
            int[] conjuntoUniverso = new int[100];
            System.out.println("Por favor ingresa el tamano del primer conjunto");
            int tamanoConjunto = read.nextInt();
            int[] conjuntoUno = new int[tamanoConjunto];
            System.out.println("Por favor ingresa el tamano del primer conjunto");
            tamanoConjunto = read.nextInt();
            int[] conjuntoDos = new int[tamanoConjunto];
            conjuntoUno = llenarConjuntos(conjuntoUno.length);
            conjuntoDos = llenarConjuntos(conjuntoDos.length);
            System.out.println(Arrays.toString(conjuntoUno));
            System.out.println(Arrays.toString(conjuntoDos));
            int[] conjuntoRepetidos;
            int[] conjuntoUnido = unirConjuntos(conjuntoUno, conjuntoDos);
            unionCojuntos(conjuntoUnido);
            conjuntoRepetidos = interseccionConjuntos(conjuntoUno, conjuntoDos);
            diferenciaConjuntos(conjuntoUno, conjuntoDos, conjuntoRepetidos);
            conjuntoUniverso = conjuntoUniverso();
            complementoConjuntos(conjuntoUniverso, conjuntoUno, conjuntoDos);
        }
    }

    public static int[] compararEliminarRepetidos(int[] conjuntoMayor, int [] conjuntoMenor){
        int contRepetidos = 0;
        int espacios = 0;
        for (int i = 0; i < conjuntoMayor.length; i++) {
            for (int j = 0; j < conjuntoMenor.length; j++) {
                if (conjuntoMayor[i] == conjuntoMenor[j]) {
                    conjuntoMayor[i] = -1;
                    contRepetidos++;
                }
            }
        }

        int[] conjuntoResultado = new int[conjuntoMayor.length - contRepetidos];
        for (int i = 0; i < conjuntoMayor.length; i++) {
            if (conjuntoMayor[i] >= 0) {
                conjuntoResultado[espacios] = conjuntoMayor[i];
                espacios++;
            }
        }
        return conjuntoResultado;
    }

    public static int[] conjuntoUniverso() {
        int[] conjuntoUniverso = new int[100];
        for (int i = 0; i < 100; i++) {
            conjuntoUniverso[i] = i;
        }
        return conjuntoUniverso;
    }

    public static int[] eliminarRepetidos(int[] conjuntoUnido) {
        int contRepetidos = 0;
        int espacios = 0;
        for (int i = 0; i < conjuntoUnido.length; i++) {
            for (int j = i + 1; j < conjuntoUnido.length; j++) {
                if (conjuntoUnido[i] == conjuntoUnido[j]) {
                    conjuntoUnido[j] = -contRepetidos-1;
                    contRepetidos++;
                }
            }
        }
        int[] conjuntoResultado = new int[conjuntoUnido.length - contRepetidos];
        for (int i = 0; i < conjuntoUnido.length; i++) {
            if (conjuntoUnido[i] >= 0) {
                conjuntoResultado[espacios] = conjuntoUnido[i];
                espacios++;
            }
        }
        return conjuntoResultado;
    }

    public static void unionCojuntos(int[] conjuntoUnido) {
        int[] conjuntoResultado;
        conjuntoResultado = eliminarRepetidos(conjuntoUnido);
        System.out.println("La union del conjuto A y B es ");
        System.out.println(Arrays.toString(conjuntoResultado));
    }

    public static int[] interseccionConjuntos(int[] conjuntoUno, int[] conjuntoDos) {
        System.out.println("La interseccion de A-B es ");
        int[] conjuntoTraspaso = new int[conjuntoUno.length];
        int espacios = 0;
        int contRepetidos = 0;
        boolean repetidoEncontrado = false;
        for (int i = 0; i < conjuntoUno.length; i++) {
            for (int j = 0; j < conjuntoDos.length; j++) {
                if (conjuntoUno[i] == conjuntoDos[j]) {
                    repetidoEncontrado = true;
                }
            }
            if (repetidoEncontrado == true) {
                conjuntoTraspaso[i] = conjuntoUno[i];
                repetidoEncontrado = false;
                contRepetidos++;
            } else {
                conjuntoTraspaso[i] = -1;
            }
        }
        int[] conjuntoResultado = new int[contRepetidos];
        for (int i = 0; i < conjuntoUno.length; i++) {
            if (conjuntoTraspaso[i] >= 0) {
                conjuntoResultado[espacios] = conjuntoTraspaso[i];
                espacios++;
            }
        }
        conjuntoResultado = eliminarRepetidos(conjuntoResultado);
        System.out.println(Arrays.toString(conjuntoResultado));
        return conjuntoResultado;
    }

    public static void diferenciaConjuntos(int[] conjuntoUno, int[] conjuntoDos, int[] conjuntoRepetidos) {
        System.out.println("La diferencia de A-B es ");
        int [] conjuntoAB = compararEliminarRepetidos(conjuntoUno, conjuntoRepetidos);
        conjuntoAB = eliminarRepetidos(conjuntoAB);
        System.out.println(Arrays.toString(conjuntoAB));
        System.out.println("La diferencia de B-A es ");
        int [] conjuntoBA = compararEliminarRepetidos(conjuntoDos, conjuntoRepetidos);
        conjuntoBA = eliminarRepetidos(conjuntoBA);
        System.out.println(Arrays.toString(conjuntoBA));
        int[] diferenciaSimetrica;
        diferenciaSimetrica = unirConjuntos(conjuntoAB, conjuntoBA);
        System.out.println("La diferencia simetrica entre A y B es ");
        System.out.println(Arrays.toString(diferenciaSimetrica));
    }

    public static void complementoConjuntos(int[] conjuntoUniverso, int[] conjuntoUno, int[] conjuntoDos) {
        System.out.println("El complemento de A");
        conjuntoUno = eliminarRepetidos(conjuntoUno);
        int [] conjuntoComA = compararEliminarRepetidos(conjuntoUniverso, conjuntoUno);
        System.out.println(Arrays.toString(conjuntoComA));
        System.out.println("El complemento de B");
        conjuntoDos = eliminarRepetidos(conjuntoDos);
        conjuntoUniverso = conjuntoUniverso();
        int [] conjuntoComB = compararEliminarRepetidos(conjuntoUniverso, conjuntoDos);
        System.out.println(Arrays.toString(conjuntoComB));

    }

    public static int[] llenarConjuntos(int tamanoConjunto) {
        int[] conjuntoLleno = new int[tamanoConjunto];
        for (int i = 0; i < conjuntoLleno.length; i++) {
            conjuntoLleno[i] = (int) (Math.random() * 100); // Genera valores aleatorios entre 0 y 100
        }
        return conjuntoLleno;
    }

    public static int[] unirConjuntos(int[] conjuntoUno, int[] conjuntoDos) {
        int tamano = conjuntoUno.length + conjuntoDos.length;
        int[] conjuntoUnido = new int[tamano];

        for (int i = 0; i < conjuntoUno.length; i++) {
            conjuntoUnido[i] = conjuntoUno[i];
        }
        int contNuevo = 0;
        for (int i = conjuntoUno.length; i < tamano; i++) {
            conjuntoUnido[i] = conjuntoDos[contNuevo];
            contNuevo++;
        }
        return conjuntoUnido;
    }
}