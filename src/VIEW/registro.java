package VIEW;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import com.toedter.calendar.JDateChooser;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Choice;
import java.awt.Font;
import java.text.SimpleDateFormat;
import javax.swing.SpinnerModel;

public class registro extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField txt_nombres;
	public JTextField txt_direccion;
	public DefaultTableModel model;
	public JTable table_registrados;
	public JDateChooser date_salida;
	public JDateChooser date_llegada;
	public JButton btn_registrar;
	public JSpinner sp_hora_salida;
	public JSpinner sp_hora_llegada;
	public JSpinner sp_edad;
	public Choice selec_genero;
	public JTextField txt_email;
	public JTextField txt_telefono;
	public JTextField sp_id;
	public JButton btn_eliminar;
	public JButton btn_actualizar;

	public registro() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 28, 430, 609);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(38, 107, 74, 32);
		panel.add(lblNewLabel);
		
		txt_nombres = new JTextField();
		txt_nombres.setBounds(121, 110, 158, 26);
		panel.add(txt_nombres);
		txt_nombres.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(38, 163, 74, 32);
		panel.add(lblDireccion);
		
		txt_direccion = new JTextField();
		txt_direccion.setBounds(121, 166, 158, 26);
		panel.add(txt_direccion);
		txt_direccion.setColumns(10);
		
		JLabel lblSalida = new JLabel("Salida");
		lblSalida.setBounds(38, 420, 74, 32);
		panel.add(lblSalida);
		
		date_salida = new JDateChooser();
		date_salida.setDateFormatString("yyyy-MM-dd");
		date_salida.setBounds(121, 420, 74, 26);
		panel.add(date_salida);
		
		// Crear el JSpinner para seleccionar la hora
        SpinnerDateModel modelSpinner = new SpinnerDateModel();
        sp_hora_salida = new JSpinner(modelSpinner);
        JSpinner.DateEditor de_sp_hora_salida = new JSpinner.DateEditor(sp_hora_salida, "HH:mm:ss");
        sp_hora_salida.setEditor(de_sp_hora_salida);
        sp_hora_salida.setBounds(205,420,74,26);
        panel.add(sp_hora_salida);
        
        SpinnerDateModel modelSpinner2 = new SpinnerDateModel();
        sp_hora_llegada = new JSpinner(modelSpinner2);
        JSpinner.DateEditor de_sp_hora_llegada = new JSpinner.DateEditor(sp_hora_llegada, "HH:mm:ss");
        sp_hora_llegada.setEditor(de_sp_hora_llegada);
        sp_hora_llegada.setBounds(205,476,74,26);
        panel.add(sp_hora_llegada);

        // Botón para obtener la fecha y hora seleccionadas
        JButton button = new JButton("Obtener Fecha y Hora");
        button.addActionListener(e -> {
            Date date = date_salida.getDate();
            Date time = (Date) sp_hora_salida.getValue();
            if (date != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY, time.getHours());
                calendar.set(Calendar.MINUTE, time.getMinutes());
                calendar.set(Calendar.SECOND, time.getSeconds());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = sdf.format(calendar.getTime());
                JOptionPane.showMessageDialog(this, "Fecha y Hora: " + formattedDate);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha.");
            }
        });
        
        panel.add(button);
		
		JLabel lblLlegada = new JLabel("Llegada");
		lblLlegada.setBounds(38, 476, 74, 32);
		panel.add(lblLlegada);
		
		date_llegada = new JDateChooser();
		date_llegada.setDateFormatString("yyyy-MM-dd");
		date_llegada.setBounds(121, 476, 74, 26);
		panel.add(date_llegada);
		
		btn_registrar = new JButton("Registrar");
		btn_registrar.setBounds(61, 544, 173, 37);
		panel.add(btn_registrar);
		
		JLabel lblNewLabel_1 = new JLabel("Registro");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(61, 30, 173, 42);
		panel.add(lblNewLabel_1);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(38, 225, 74, 32);
		panel.add(lblEdad);
		
		sp_edad = new JSpinner();
		sp_edad.setBounds(121, 228, 158, 26);
		panel.add(sp_edad);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(38, 279, 74, 32);
		panel.add(lblGenero);
		
		selec_genero = new Choice();
		selec_genero.setBounds(121, 282, 158, 26);
		panel.add(selec_genero);
		
		selec_genero.add("HOMBRE");
		selec_genero.add("MUJER");
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(38, 322, 74, 32);
		panel.add(lblEmail);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(121, 325, 158, 26);
		panel.add(txt_email);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(38, 365, 74, 32);
		panel.add(lblTelefono);
		
		txt_telefono = new JTextField();
		txt_telefono.setColumns(10);
		txt_telefono.setBounds(121, 368, 158, 26);
		panel.add(txt_telefono);
		
		sp_id = new JTextField();
		sp_id.setBounds(300, 110, 88, 26);
		panel.add(sp_id);
		
		JLabel lblBuscarPorId = new JLabel("Buscar por id");
		lblBuscarPorId.setBounds(300, 67, 88, 32);
		panel.add(lblBuscarPorId);
		
		btn_eliminar = new JButton("Eliminar");
		btn_eliminar.setBounds(299, 149, 89, 23);
		panel.add(btn_eliminar);
		
		btn_actualizar = new JButton("Actualizar");
		btn_actualizar.setBounds(300, 183, 89, 23);
		panel.add(btn_actualizar);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(463, 28, 467, 609);
		add(panel_1);
		panel_1.setLayout(null);
		
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(26, 72, 405, 451);
		panel_1.add(scroll);	
		
		String[] columnNames = {"ID","NOMBRES", "DIRECCION","EDAD","GENERO","EMAIL","TELEFONO", "SALIDA", "LLEGADA"};
		model = new DefaultTableModel(columnNames,0);
		
		table_registrados = new JTable(model);
		scroll.setViewportView(table_registrados);
		

	}
}
