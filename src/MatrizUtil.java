public class MatrizUtil {

    public static double[][] invertir(double[][] matriz) {
        int n = matriz.length;
        double[][] identidad = new double[n][n];
        double[][] copia = new double[n][n];

        // inicializar identidad y copia:. / .
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copia[i][j] = matriz[i][j];
                identidad[i][j] = (i == j) ? 1 : 0;
            }
        }

        // metodo gauss - jordan .
        for (int i = 0; i < n; i++) {
            double pivote = copia[i][i];

            for (int j = 0; j < n; j++) {
                copia[i][j] /= pivote;
                identidad[i][j] /= pivote;
            }

            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = copia[k][i];
                    for (int j = 0; j < n; j++) {
                        copia[k][j] -= factor * copia[i][j];
                        identidad[k][j] -= factor * identidad[i][j];
                    }
                }
            }
        }

        return identidad;
    }

    public static String matrizToString(double[][] m) {
        StringBuilder sb = new StringBuilder();
        for (double[] fila : m) {
            for (double val : fila) {
                sb.append(String.format("%.2f", val)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}   
