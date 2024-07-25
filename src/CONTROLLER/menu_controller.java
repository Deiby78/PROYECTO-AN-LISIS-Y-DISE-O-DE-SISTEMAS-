package CONTROLLER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import MODEL.Empleado;
import MODEL.empleDAO;
import MODEL.reportes;
import VIEW.VIEW_MENU;

public class menu_controller implements ActionListener,ItemListener,MouseListener,KeyListener{
	
	
	private VIEW_MENU vm;
	private empleDAO emp;
	private reportes rep;
	
	public menu_controller(VIEW_MENU vm) {
		// TODO Auto-generated constructor stub
		this.vm = vm;
		vm.btn_horarios.addActionListener(this);
		vm.btn_registro.addActionListener(this);
		vm.btnReportes.addActionListener(this);
		vm.re.btn_registrar.addActionListener(this);
		vm.rep.select_empleado.addItemListener(this);
		vm.rep.btn_imprimir.addActionListener(this);
		vm.hor.choice.addItemListener(this);
		vm.hor.tbl_horarios.addMouseListener(this);
		vm.re.sp_id.addKeyListener(this);
		vm.re.btn_eliminar.addActionListener(this);
		vm.re.btn_actualizar.addActionListener(this);
		vm.re.btn_eliminar.setEnabled(false);
		vm.re.btn_actualizar.setEnabled(false);
		emp = new empleDAO();
		if(emp.verificarSiHayDatos()) {
			cargarEmpleados();
			cargarEmpleadosEnChoice();
			
			Empleado empleado = emp.getDatosEmpleadoSelect(emp.getPrimerId());		        
	        rep = new reportes(empleado);
	        
	        vm.rep.txt_resumen.setText("");
	        vm.rep.txt_resumen.setText(rep.getReporte());
	        cargarHorarioempleado(emp.getPrimerId());
		}

	}
	
	public void cargarHorarioempleado(String id) {
		ArrayList<int[]> posciciones = emp.obtenerHorario(id);
		
		for(int[] row: posciciones) {
			vm.hor.tbl_horarios.setValueAt("x", row[0], row[1]);
		}
		
	}
	
	public void cargarEmpleados() {
		
		vm.re.model.setRowCount(0);
		
		ArrayList<String[]> datos = emp.obtenerFarmaceuticos();
		
		for(String[] row:datos) {
			vm.re.model.addRow(row);
		}
		
	}
	
	public void cargarEmpleadosEnChoice() {
		
		vm.rep.select_empleado.removeAll();
		vm.hor.choice.removeAll();
		
		ArrayList<String[]> datos = emp.obtenerFarmaceuticos();
		
		for(String[] row:datos) {
			vm.rep.select_empleado.add(row[0]+" "+row[1]);
			vm.hor.choice.add(row[0]+" "+row[1]);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == vm.btnReportes) {
			cargarEmpleadosEnChoice();
		}
		
		if(e.getSource() == vm.btn_horarios) {			
			cargarEmpleadosEnChoice();
			CargarHorarios();
		}
		
		if(e.getSource() == vm.rep.btn_imprimir) {
			rep.crearDocumento();
		}
		
		if(e.getSource() == vm.re.btn_eliminar) {
			emp.eliminarEmpleado(vm.re.sp_id.getText());
			resetearCampos();
			cargarEmpleados();
			CargarHorarios();
			vm.re.sp_id.setText("");
			vm.re.btn_eliminar.setEnabled(false);
			vm.re.btn_actualizar.setEnabled(false);
			vm.re.btn_registrar.setEnabled(true);
		}
		
		if(e.getSource() == vm.re.btn_actualizar) {
			
			if(vm.re.date_salida.getDate() == null){
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha de salida.");
			}else if(vm.re.date_llegada.getDate() == null){
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha de llegada.");
			}else {
				Empleado empl = new  Empleado(
						  Integer.valueOf(vm.re.sp_id.getText())
						 ,vm.re.txt_nombres.getText()
						 ,vm.re.txt_direccion.getText()
						 ,String.valueOf(vm.re.sp_edad.getValue())
						 ,vm.re.selec_genero.getSelectedItem()
						 ,vm.re.txt_email.getText()
						 ,vm.re.txt_telefono.getText()
						 ,getDate(vm.re.date_salida,vm.re.sp_hora_salida)
						 ,getDate(vm.re.date_llegada,vm.re.sp_hora_llegada));
				emp.ActualizarEmpleado(empl);
				
				resetearCampos();
				cargarEmpleados();
				CargarHorarios();
				vm.re.sp_id.setText("");
				vm.re.btn_eliminar.setEnabled(false);
				vm.re.btn_actualizar.setEnabled(false);
				vm.re.btn_registrar.setEnabled(true);
			}
			

		}
		
		if(e.getSource() == vm.re.btn_registrar) {
			if(vm.re.txt_nombres.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre.");
			}else if(vm.re.txt_direccion.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una direccion.");
			}else if(vm.re.date_salida.getDate() == null){
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha de salida.");
			}else if(vm.re.date_llegada.getDate() == null){
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha de llegada.");
			}else if(vm.re.txt_email.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
				JOptionPane.showMessageDialog(null, "Por favor, revise el correo electronico.");
			}		
			else {
				Empleado empl = new  Empleado(vm.re.txt_nombres.getText()
						 ,vm.re.txt_direccion.getText()
						 ,String.valueOf(vm.re.sp_edad.getValue())
						 ,vm.re.selec_genero.getSelectedItem()
						 ,vm.re.txt_email.getText()
						 ,vm.re.txt_telefono.getText()
						 ,getDate(vm.re.date_salida,vm.re.sp_hora_salida)
						 ,getDate(vm.re.date_llegada,vm.re.sp_hora_llegada));
				emp.registrarEmpleado(empl);
				
				resetearCampos();
			}
			
			cargarEmpleados();
		}
		
	}
	
	public String getDate(JDateChooser dateChooser,JSpinner spinner) {
		Date date = dateChooser.getDate();
        Date time = (Date) spinner.getValue();
        String fecha = "";
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, time.getHours());
            calendar.set(Calendar.MINUTE, time.getMinutes());
            calendar.set(Calendar.SECOND, time.getSeconds());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = sdf.format(calendar.getTime());
            fecha = formattedDate;
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fecha.");
        }
        System.out.println(fecha);
        return fecha;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
	    if (e.getItemSelectable() == vm.rep.select_empleado) {
	        if (e.getStateChange() == ItemEvent.SELECTED) {
	        	String[] seleccionado = vm.rep.select_empleado.getSelectedItem().split(" ");
	        	String firstNumber = seleccionado[0];
	        	
		        Empleado empleado = emp.getDatosEmpleadoSelect(firstNumber);		        
		        rep = new reportes(empleado);
		        
		        vm.rep.txt_resumen.setText("");
		        vm.rep.txt_resumen.setText(rep.getReporte());	        
	        }
	    }
	    
	    if (e.getItemSelectable() == vm.hor.choice) {
	        if (e.getStateChange() == ItemEvent.SELECTED) {
	        	CargarHorarios();
	        }
	    }
	}
	
	public void CargarHorarios() {
    	vm.hor.model.setRowCount(0);
    	for (int i = 0; i <= 24; i++) {
			if (i >= 0 && i < 10) {

				String[] st = { "0" + i + ":00" };
				vm.hor.model.addRow(st);
			} else {

				String[] st = { i + ":00" };
				vm.hor.model.addRow(st);
			}
		}
    	
    	String[] seleccionado = vm.hor.choice.getSelectedItem().split(" ");
    	String firstNumber = seleccionado[0];
    	cargarHorarioempleado(firstNumber);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == vm.hor.tbl_horarios) {
			int fila = vm.hor.tbl_horarios.getSelectedRow();
			int columna = vm.hor.tbl_horarios.getSelectedColumn();			
			String getValor =(String)vm.hor.tbl_horarios.getValueAt(fila, columna);
			String horaSeleccionada=String.valueOf(vm.hor.tbl_horarios.getModel().getValueAt(fila, 0));
			String DiaSeleccionada=String.valueOf(vm.hor.tbl_horarios.getModel().getColumnName(columna));
			String[] seleccionado = vm.hor.choice.getSelectedItem().split(" ");
        	String firstNumber = seleccionado[0];
			
			
			if(getValor!=null) {
				if(getValor.equals("x")) {
					System.out.println("columna ya marcada");
					vm.hor.tbl_horarios.setValueAt(null, fila, columna);
					emp.eliminarDelHorario(firstNumber, DiaSeleccionada, horaSeleccionada);
				}
			}else {				
				vm.hor.tbl_horarios.setValueAt("x", fila, columna);
				emp.marcarHorario(firstNumber, DiaSeleccionada, horaSeleccionada);
			}
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==vm.re.sp_id) {
			String text = vm.re.sp_id.getText();
			
			if(text.isEmpty()){
				resetearCampos();
                vm.re.btn_eliminar.setEnabled(false);
                vm.re.btn_actualizar.setEnabled(false);
                vm.re.btn_registrar.setEnabled(true);
			}else
            if (text.matches("\\d*") ) {
                Empleado emple = emp.getDatosEmpleadoSelect(text);
                if(emple!=null) {
                	vm.re.btn_eliminar.setEnabled(true);
                	vm.re.btn_actualizar.setEnabled(true);
                	vm.re.btn_registrar.setEnabled(false);
                	vm.re.txt_nombres.setText(emple.getNombre());
                    vm.re.txt_direccion.setText(emple.getDireccion());
                    vm.re.txt_email.setText(emple.getEmail());
                    vm.re.sp_edad.setValue(Integer.valueOf(emple.getEdad()));
                    if(emple.getGenero().equals("HOMBRE")) {
                    	vm.re.selec_genero.select(0);
                    }else {
                    	vm.re.selec_genero.select(1);
                    }
                    vm.re.txt_telefono.setText(emple.getTelefono());
                }else {
                	JOptionPane.showMessageDialog(null, "No se ha encontrado el empleado");
                }
                
            } 
		}
	}
	
	public void resetearCampos() {
		vm.re.txt_nombres.setText("");
        vm.re.txt_direccion.setText("");
        vm.re.txt_email.setText("");
        vm.re.sp_edad.setValue(0);
        vm.re.selec_genero.select(0);                
        vm.re.txt_telefono.setText("");
        vm.re.date_salida.setDate(null);
        vm.re.date_llegada.setDate(null);
	}

}
