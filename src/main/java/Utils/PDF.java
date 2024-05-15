package Utils;


import ENTITY.Empleado;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;

import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.*;

import java.awt.*;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;


public class PDF {

    public void generarPDFEmp(Empleado empleado) throws IOException {


            Path tempFile = Files.createTempFile(empleado.getNombre(), ".pdf");


            try {

                PdfWriter writer = new PdfWriter(tempFile.toAbsolutePath().toString());
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                PdfFont titleFont = PdfFontFactory.createFont();
                Paragraph title = new Paragraph("INFORMACION EMPLEADO").setFont(titleFont).setFontSize(24).setTextAlignment(TextAlignment.CENTER);
                document.add(title);

                Table table = new Table(new float[]{250, 300});
                table.setBackgroundColor(new DeviceRgb(255, 255, 255));
                Image image = transformar(empleado.getImagen());

                image.setWidth(170);
                image.setHeight(170);
                image.setMarginLeft(50);

                Cell imageCell = new Cell().add(image).setPaddingTop(50);
                imageCell.setBorder(Border.NO_BORDER);
                table.addCell(imageCell);


                PdfFont font = PdfFontFactory.createFont();
                Paragraph personalInfo = new Paragraph()
                        .setFont(font)
                        .setFontSize(12)
                        .add(new Text(empleado.getNombre()+" "+empleado.getApellido()).setBold()).add("\n\n")
                        .add(new Text(empleado.getDNI()).setBold()).add("\n\n")
                        .add(new Text(String.valueOf(empleado.getTelefono())).setBold()).add("\n\n")
                        .add(new Text(empleado.getEmail()).setBold()).add("\n\n")
                        .add(new Text(" "+empleado.getFecha_alta()).setBold()).add("\n\n")
                        .setMarginLeft(20);
                Cell personalInfoCell = new Cell().add(personalInfo)
                        .setPaddingTop(50)
                        .setPaddingBottom(5);
                personalInfoCell.setBorder(Border.NO_BORDER);
                table.addCell(personalInfoCell);

                document.add(table);

                document.close();

                File file = new File(String.valueOf(tempFile.toFile()));
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.err.println("No se pudo encontrar el archivo PDF.");
                }

                System.out.println("Documento PDF creado con éxito.");
            } catch (IOException e) {
                System.err.println("Error al crear el archivo PDF: " + e.getMessage());
            }
        }


    public Image transformar(byte[] imageData) {
        Image image = null;
        try {
            ImageData imageDataObj = ImageDataFactory.create(imageData);
            image = new Image(imageDataObj);
        } catch (Exception e) {
            System.err.println("Error al transformar la imagen: " + e.getMessage());
        }
        return image;
    }

    }


