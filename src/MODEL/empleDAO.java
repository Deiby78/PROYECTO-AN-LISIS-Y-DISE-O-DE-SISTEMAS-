package MODEL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.itextpdf.text.log.SysoLogger;

public class empleDAO {

	private query sql;

	public empleDAO() {
		// TODO Auto-generated constructor stub
		sql = new query();
	}
	
	public void registrarEmpleado(Empleado emp) {
		
		/*String sent = "INSERT INTO public.\"Empleados\" (\r\n"
				+ "    pk_empleados, \"nombres_emp\", direccion, hora_de_salida, hora_de_llegada\r\n"
				+ ") VALUES (DEFAULT,'"+emp.getNombre()+"',"
														+ "'"+emp.getDireccion()+"','"
														+ emp.getFecha_salida()+"','"
														+ emp.getFecha_llegada()+"')";*/
		
		String sent =String.format("INSERT INTO public.empleados(\r\n"
				+ "	pk_empleados, nombres_emp, direccion, edad, genero, email, telefono, hora_de_salida, hora_de_llegada)\r\n"
				+ "	VALUES (DEFAULT, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');"
				,emp.getNombre(),emp.getDireccion(),emp.getEdad(),emp.getGenero(),emp.getEmail(),emp.getTelefono(),emp.getFecha_salida(), emp.getFecha_llegada());
		System.out.println(sent);
		sql.operIUD(sent);
		
	}
	
	public Boolean verificarSiHayDatos() {
		
		String sent = "select * from empleados";
		
		ResultSet res = sql.consultas(sent);
		
		int datos = 0;
		
		try {
			while(res.next()) {
				datos++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(datos>0) {
			return true;
		}else {
			return false;
		}		
	}
	
	public String getPrimerId() {
		String sent = "SELECT pk_empleados FROM empleados ORDER BY pk_empleados LIMIT 1";
		
		ResultSet res = sql.consultas(sent);
		
		String datos = "";
		
		try {
			while(res.next()) {
				datos = res.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return datos;		
	}
	
	public Empleado getDatosEmpleadoSelect(String id) {
		
		String sent = "select * from empleados where pk_empleados ="+id;
		
		ResultSet res = sql.consultas(sent);
		
		Empleado emp = null;
		
		try {
			while(res.next()) {
				emp = new Empleado(res.getInt(1), res.getString(2), 
						res.getString(3), res.getString(4), res.getString(5), 
						res.getString(6), res.getString(7), res.getString(8), res.getString(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emp;
	}

	public ArrayList<String[]> obtenerFarmaceuticos() {

		ArrayList<String[]> data = new ArrayList<String[]>();
		String sentence = "select * from empleados";
		ResultSet res = sql.consultas(sentence);

		try {

			while (res.next()) {
				String[] row = { res.getString(1), 
						res.getString(2), 
						res.getString(3), 
						res.getString(4),
						res.getString(5),
						res.getString(6),
						res.getString(7),
						res.getString(8),
						res.getString(9)};
				data.add(row);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
	
	public void marcarHorario(String idEmpleado,String dia,String hora) {
		
		String sent = String.format("INSERT INTO public.horarios(\r\n"
				+ "	pk_horarios, fk_empleado, dia_de_la_semana, hora)\r\n"
				+ "	VALUES (DEFAULT, '%s', '%s', '%s');",idEmpleado,dia,hora);
		
		sql.operIUD(sent);
		
	}
	
	public void eliminarDelHorario(String idEmpleado,String dia,String hora) {
		
		String del = String.format("delete from horarios where fk_empleado = %s\r\n"
				+ "and dia_de_la_semana = '%s' \r\n"
				+ "and hora ='%s'",idEmpleado,dia,hora);
		sql.operIUD(del);
	}
	
	public void eliminarEmpleado(String idEmple) {
		
		String con1 = "delete from horarios where fk_empleado ="+ idEmple;
		sql.operIUD(con1);
		
		String con = "delete from empleados where pk_empleados ="+ idEmple;
		sql.operIUD(con);
		
	}
	
	public ArrayList<int[]> obtenerHorario(String idEmpleado) {
		
		String con = "select dia_de_la_semana, hora from horarios where fk_empleado = "+ idEmpleado;
		
		ResultSet res = sql.consultas(con);
		
		ArrayList<int[]> posiciones =new  ArrayList <int[]>() ;
		
		try {
			while(res.next()) {
				int[] pos =	transformar(res.getString(1),res.getString(2));
				posiciones.add(pos);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posiciones;
	}
	
	private int[] transformar(String dia, String hora) {
		
		String [] dias = {"LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES","SABADO","DOMINGO"};
		
		int x = 1;
		int y = 0;
		
		int [] val = {y,x};
		
		boolean diaEncontrado = false;
		boolean horaEncontrada = false;
		
		for (int i = 0; i<6;i++) {
			if(!dia.equals(dias[i])&& !diaEncontrado) {
				x++;
			}else {
				diaEncontrado = true;
			}
		}
		
		String st;
		
		for (int i = 0; i <= 24; i++) {
			if (i >= 0 && i < 10) {
				 st = "0" + i + ":00";
			} else {
				 st =  i + ":00" ;
			}
			if(!hora.equals(st)&&!horaEncontrada) {
				y++;
			}else {
				horaEncontrada = true;
			}
		}
		val[0] = y;
		val[1] = x;
		
		return val;
	}

	public void ActualizarEmpleado(Empleado emp) {
		// TODO Auto-generated method stub
		String sent = String.format("UPDATE empleados\r\n"
				+ "SET nombres_emp='%s', direccion='%s', edad='%s', genero='%s', email='%s', telefono='%s', hora_de_salida='%s', hora_de_llegada='%s'\r\n"
				+ "WHERE pk_empleados = %d;",emp.getNombre(),emp.getDireccion(),emp.getEdad(),emp.getGenero(),emp.getEmail(),emp.getTelefono(),emp.getFecha_salida(), emp.getFecha_llegada(),emp.getId());
		
		System.out.println(sent);
		sql.operIUD(sent);
	}

}
