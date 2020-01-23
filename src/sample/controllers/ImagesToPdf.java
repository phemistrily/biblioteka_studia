package sample.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import sample.entities.PageEntity;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImagesToPdf {

    public ImagesToPdf(Integer bookId) throws IOException, DocumentException, SQLException {
        File root = new File("D:/test");
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String outputFile = dateFormat.format(date)+"book.pdf";
        List<String> files = new ArrayList<String>();
        PageEntity pages = new PageEntity();
        ResultSet fileList = pages.getPageList(bookId);
        String tempFilename;
        System.out.println(fileList);
        while(fileList.next()) {
            tempFilename = fileList.getString("file_name");
            tempFilename = tempFilename.replace("\\", "\\\\");
            files.add(tempFilename);
            //files.add("C:\\\\Users\\\\Phemistrily\\\\Pictures\\\\ss.png");
            //files.add("C:\\\\Users\\\\Phemistrily\\\\Pictures\\\\ts.png");
        }
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(new File(root, outputFile)));
        document.open();
        for (String f : files) {
            document.newPage();
            Image image = Image.getInstance(new File(f).getAbsolutePath());
            image.setAbsolutePosition(0, 0);
            image.setBorderWidth(0);
            image.scaleAbsoluteHeight(PageSize.A4.getHeight());
            image.scaleAbsoluteWidth(PageSize.A4.getWidth());
            document.add(image);
        }
        document.close();
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(root, outputFile));
    }
}
