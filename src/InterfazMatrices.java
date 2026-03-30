import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.CallableStatement;
import java.sql.Connection;

public class InterfazMatrices extends JFrame {

    private JTextArea txtOriginal = new JTextArea(5, 20);
    private JTextArea txtInversa = new JTextArea(5, 20);
    private JTextArea txtRecuperada = new JTextArea(5, 20);

    public InterfazMatrices() {
        setTitle("Matrices - Inversa y Proceso Contrario");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Matriz Original"));
        panel.add(new JScrollPane(txtOriginal));

        panel.add(new JLabel("Matriz Inversa"));
        panel.add(new JScrollPane(txtInversa));

        panel.add(new JLabel("Matriz Recuperada"));
        panel.add(new JScrollPane(txtRecuperada));

        add(panel, BorderLayout.CENTER);

        JButton btnProcesar = new JButton("Procesar");
        add(btnProcesar, BorderLayout.SOUTH);

        btnProcesar.addActionListener((ActionEvent e) -> procesar());

        setVisible(true);
    }

    private void procesar() {
        try {
            // Matriz de ejemplo
            double[][] matriz = {
                    {4, 7},
                    {2, 6}
            };

            double[][] inversa = MatrizUtil.invertir(matriz);
            double[][] recuperada = MatrizUtil.invertir(inversa);

            String originalStr = MatrizUtil.matrizToString(matriz);
            String inversaStr = MatrizUtil.matrizToString(inversa);
            String recuperadaStr = MatrizUtil.matrizToString(recuperada);

            txtOriginal.setText(originalStr);
            txtInversa.setText(inversaStr);
            txtRecuperada.setText(recuperadaStr);

            guardarBD(originalStr, inversaStr, recuperadaStr);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void guardarBD(String original, String inversa, String recuperada) {
        try (Connection conn = ConexionOracle.getConnection()) {

            CallableStatement cs = conn.prepareCall("{call INSERTAR_MATRIZ(?, ?, ?)}");

            cs.setString(1, original);
            cs.setString(2, inversa);
            cs.setString(3, recuperada);

            cs.execute();

            JOptionPane.showMessageDialog(this, "Datos guardados en Oracle");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new InterfazMatrices();
    }
}   