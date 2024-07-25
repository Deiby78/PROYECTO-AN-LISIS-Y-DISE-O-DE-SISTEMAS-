package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CONTROLLER.menu_controller;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VIEW_MENU extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private menu_controller mc;
	public JButton btn_registro;
	public JButton btn_horarios;
	public JButton btnReportes;
	public registro re;
	public reportes rep;
	public horarios hor;
	private JButton btn_observaciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VIEW_MENU frame = new VIEW_MENU();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VIEW_MENU() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1180, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1250, 616);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(0, 0, 200, 616);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		btn_registro = new JButton("Registro de empleados");
		btn_registro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				re.setVisible(true);
				rep.setVisible(false);
				hor.setVisible(false);
			}
		});
		btn_registro.setBounds(10, 79, 180, 52);
		panel_1.add(btn_registro);
		
		btn_horarios = new JButton("Horarios por semana");
		btn_horarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				re.setVisible(false);
				rep.setVisible(false);
				hor.setVisible(true);
			}
		});
		btn_horarios.setBounds(10, 148, 180, 52);
		panel_1.add(btn_horarios);
		
		btnReportes = new JButton("Reportes");
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				re.setVisible(false);
				rep.setVisible(true);
				hor.setVisible(false);
			}
		});
		btnReportes.setBounds(10, 211, 180, 52);
		panel_1.add(btnReportes);
		
		JLabel lblNewLabel = new JLabel("Sistema de transporte");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 22, 180, 32);
		panel_1.add(lblNewLabel);
		
		btn_observaciones = new JButton("Observaciones");
		btn_observaciones.setBounds(10, 274, 180, 52);
		panel_1.add(btn_observaciones);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(197, 0, 1000, 616);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		re = new registro();
		re.setBounds(0, 0, 1000, 616);
		panel_2.add(re);
		
		rep = new reportes();
		rep.setBounds(0, 0, 1000, 616);
		rep.setVisible(false);
		panel_2.add(rep);
		
		hor = new horarios();
		hor.setBounds(0, 0, 1000, 616);
		hor.setVisible(false);
		panel_2.add(hor);
		
		mc = new  menu_controller(this);
	}
}