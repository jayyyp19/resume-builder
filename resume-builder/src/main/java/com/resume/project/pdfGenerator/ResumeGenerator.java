package com.resume.project.pdfGenerator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class ResumeGenerator {

    public static void main(String[] args) {
        generateResume();
    }

    public static void generateResume() {
        try {
            // Create a Document object
            Document document = new Document();

            // Provide the path where you want to save the PDF file
            String filePath = "D:\\resume.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Open the document for writing
            document.open();

            // Add content to the document
            document.add(new Paragraph("Your Name"));
            document.add(new Paragraph("Contact Information"));
            document.add(new Paragraph("Education:"));
            document.add(new Paragraph("   - Degree:"));
            document.add(new Paragraph("   - University:"));
            document.add(new Paragraph("Work Experience:"));
            document.add(new Paragraph("   - Position:"));
            document.add(new Paragraph("   - Company:"));
            document.add(new Paragraph("Skills:"));
            document.add(new Paragraph("   - Skill 1"));
            document.add(new Paragraph("   - Skill 2"));

            // Close the document
            document.close();

            System.out.println("Resume created successfully.");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadResume(HttpServletResponse response) {
        try {
            // Generate the resume
            generateResume();

            // Read the PDF file into a byte array
            byte[] pdfBytes = getPDFBytes("resume.pdf");

            // Set response headers
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=resume.pdf");
            response.setContentLength(pdfBytes.length);

            // Write PDF content to the response stream
            response.getOutputStream().write(pdfBytes);

            // Flush the buffer and close the stream
            response.getOutputStream().flush();
            response.getOutputStream().close();

            System.out.println("Resume downloaded successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] getPDFBytes(String filePath) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        try (FileInputStream fis = new FileInputStream(filePath)) {
            int length;
            while ((length = fis.read(buffer)) > 0) {
                baos.write(buffer, 0, length);
            }
        }

        return baos.toByteArray();
    }
}
