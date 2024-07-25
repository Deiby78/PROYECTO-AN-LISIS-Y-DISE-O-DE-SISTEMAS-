package MODEL;

public interface parametrizable {
	
	
	public final String ip = "localhost", 
			port="5432",
			bbdd="transporte",
			user= "postgres",
			psw="1234";
	
	public default String getURL() {
		return "jdbc:postgresql://"+ip+":"+port+"/"+bbdd+"";
	}

}
