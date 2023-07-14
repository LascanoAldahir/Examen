import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Estudiantes {
    JPanel rootPanel;
    private JLabel Estudiantes;
    private JLabel Codigo;
    private JTextField CodigoR;
    private JLabel FechaDeNacimiento;
    private JComboBox Anio;
    private JComboBox Mes;
    private JComboBox Dia;
    private JLabel Cedula;
    private JTextField CedulaR;
    private JLabel ColorFavorito;
    private JCheckBox rojoCheckBox;
    private JCheckBox verdeCheckBox;
    private JCheckBox ningunoCheckBox;
    private JLabel Nombre;
    private JTextField NombreR;
    private JLabel Casado;
    private JRadioButton SiRadioButton;
    private JRadioButton NoRadioButton;
    private JButton CargarButton;
    private JButton GuardarButton;
    private JButton BackButton;
    private JButton NextButton;

    public Estudiantes() {
        GuardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Codigo = CodigoR.getText();
                String Cedula=CedulaR.getText();
                String Nombre = NombreR.getText();
                String anio = String.valueOf(Anio.getSelectedItem());
                String mes = String.valueOf(Mes.getSelectedItem());
                String dia = String.valueOf(Dia.getSelectedItem());
                boolean rojoSeleccionado = rojoCheckBox.isSelected();
                boolean verdeSeleccionado = verdeCheckBox.isSelected();
                boolean ningunoSeleccionado = ningunoCheckBox.isSelected();
                boolean siSeleccionado = SiRadioButton.isSelected();
                boolean noSeleccionado = NoRadioButton.isSelected();

                guardarDatos(Cedula,Codigo,Nombre,anio,mes,dia,rojoSeleccionado,verdeSeleccionado,ningunoSeleccionado,siSeleccionado,noSeleccionado);
            }
        });//fin de GuardarButton

        CargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        }); // fin de cargar button

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = (JFrame) SwingUtilities.getWindowAncestor(rootPanel);
                frame2.setVisible(false);

                JFrame frame1 = new JFrame("Ventana 2");
                Estudiantes2 segventana = new Estudiantes2();
                frame1.setContentPane(segventana.rootPanel);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setVisible(true);
            }
        }); // fin de back button
}//fin del Estudiantes(){}

    private void guardarDatos(String Codigo, String Cedula, String Nombre, String anio, String mes, String dia, boolean rojoSeleccionado, boolean verdeSeleccionado, boolean ningunoSeleccionado, boolean siSeleccionado, boolean noSeleccionadoverdeSeleccionado ) {
        String filePath = "datos.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String datos = Codigo + "," + Cedula + "," + Nombre + ","+  "\n";
            writer.write(datos);
            System.out.println("Datos guardados correctamente en el archivo.");
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar los datos en el archivo.", e);
        }
    } //fin de guardar datos

    private void cargarDatos() {
        String filePath = "datos.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linea;
            if ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    String marca = datos[0];
                    int año = Integer.parseInt(datos[1]);
                    int cilindraje = Integer.parseInt(datos[2]);

                    CodigoR.setText(marca);
                    CedulaR.setText(String.valueOf(año));
                    NombreR.setText(String.valueOf(cilindraje));

                    Anio.setSelectedItem("tu_anio_aqui");
                    Mes.setSelectedItem("tu_mes_aqui");
                    Dia.setSelectedItem("tu_dia_aqui");
                    rojoCheckBox.setSelected(true);
                    verdeCheckBox.setSelected(true);
                    ningunoCheckBox.setSelected(true);
                    SiRadioButton.setSelected(true);
                    NoRadioButton.setSelected(true);

                    System.out.println("Datos cargados correctamente desde el archivo.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar los datos desde el archivo.", e);
        }
    } // fin cargardatos

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana1");
        frame.setContentPane(new Estudiantes().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
