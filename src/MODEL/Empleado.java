package MODEL;

public class Empleado {
	
	private int id;
	private String nombre;
	private String direccion;
	private String edad;
	private String genero;
	private String email;
	private String telefono;
	private String fecha_salida;
	private String fecha_llegada;
	
	public Empleado() {
		// TODO Auto-generated constructor stub
	}
	
	public Empleado(String nombre,
			String direccion,
			String edad,
			String genero,
			String email,
			String telefono,
			String fecha_salida,
			String fecha_llegada) {
		// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.direccion = direccion;
		this.edad= edad;
		this.genero= genero;
		this.email= email;
		this.telefono= telefono;
		this.fecha_salida = fecha_salida;
		this.fecha_llegada =fecha_llegada;
	}
	
	public Empleado(int id,String nombre,
			String direccion,
			String edad,
			String genero,
			String email,
			String telefono,
			String fecha_salida,
			String fecha_llegada) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.edad= edad;
		this.genero= genero;
		this.email= email;
		this.telefono= telefono;
		this.fecha_salida = fecha_salida;
		this.fecha_llegada =fecha_llegada;
	}
	
	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(String fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public String getFecha_llegada() {
		return fecha_llegada;
	}

	public void setFecha_llegada(String fecha_llegada) {
		this.fecha_llegada = fecha_llegada;
	}

}
