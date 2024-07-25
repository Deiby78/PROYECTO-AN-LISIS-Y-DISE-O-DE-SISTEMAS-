package MODEL;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public class reportes {

	private Empleado emp;

	public reportes(Empleado emp) {
		// TODO Auto-generated constructor stub
		this.emp = emp;
	}

	public String getReporte() {

		return String.format(
				"\tREPORTE DE TRANSPORTE\n\n" + "Nombres: %s\n" + "Direccion: %s\n" + "Edad: %s\n" + "Genero: %s\n"
						+ "Email: %s\n" + "Telefono: %s\n\n\n",
				emp.getNombre(), emp.getDireccion(), emp.getEdad(), emp.getGenero(), emp.getEmail(), emp.getTelefono());

	}

	public void crearDocumento() {
		try {
			String str = getReporte();
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("reporte.pdf"));
			document.open();
			document.add(new Paragraph(str));
			
			PdfPTable table = new PdfPTable(2);
			
			table.addCell("Fecha Salida");
			table.addCell("Fecha Llegada");
			
			table.addCell(emp.getFecha_salida());
			table.addCell(emp.getFecha_llegada());
			
			document.add(table);
			
			document.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		int result = JOptionPane.showConfirmDialog(null, "¿Desea abrir el pdf?", "Confirmación",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			try {
				Desktop.getDesktop().open(new File("reporte.pdf"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
