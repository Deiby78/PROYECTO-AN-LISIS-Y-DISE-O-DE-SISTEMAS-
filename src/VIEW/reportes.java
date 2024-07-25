package VIEW;

import javax.swing.JPanel;
import java.awt.Choice;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class reportes extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextArea txt_resumen;
	public JButton btn_imprimir;
	public Choice select_empleado;

	public reportes() {
		setLayout(null);
		
		select_empleado = new Choice();
		select_empleado.setBounds(288, 102, 246, 109);
		add(select_empleado);
		
		JLabel lblNewLabel = new JLabel("Seleccionar Empleado");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(36, 97, 246, 23);
		add(lblNewLabel);
		
		btn_imprimir = new JButton("Imprimir");
		btn_imprimir.setBounds(234, 282, 89, 23);
		add(btn_imprimir);
		
		txt_resumen = new JTextArea();
		txt_resumen.setBounds(567, 79, 351, 411);
		add(txt_resumen);
		
		

	}
}
