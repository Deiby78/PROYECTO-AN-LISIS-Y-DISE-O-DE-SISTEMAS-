package VIEW;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Choice;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class horarios extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTable tbl_horarios;
	public Choice choice;
	public DefaultTableModel model;

	public horarios() {
		setLayout(null);

		choice = new Choice();
		choice.setBounds(273, 75, 243, 40);
		add(choice);

		JLabel lblNewLabel = new JLabel("Seleccionar empleado");
		lblNewLabel.setBounds(59, 75, 180, 18);
		add(lblNewLabel);

		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(69, 135, 649, 375);
		add(scroll);

		String[] columns = { "HORARIO", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES","SABADO","DOMINGO" };
		model = new DefaultTableModel(columns, 0);

		tbl_horarios = new JTable(model);
		scroll.setViewportView(tbl_horarios);

		for (int i = 0; i <= 24; i++) {
			if (i >= 0 && i < 10) {

				String[] st = { "0" + i + ":00" };
				model.addRow(st);
			} else {

				String[] st = { i + ":00" };
				model.addRow(st);
			}
		}

	}
}
