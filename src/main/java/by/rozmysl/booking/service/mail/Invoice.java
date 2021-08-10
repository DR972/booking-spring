package by.rozmysl.booking.service.mail;

import by.rozmysl.booking.entity.user.Reservation;

import java.io.File;
import java.io.IOException;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.Document;
import org.springframework.stereotype.Service;
/**
 * The class is responsible for working with the "Invoice" file
 */
@Service
public class Invoice {
    /**
     * The method creates the "Invoice" file
     *
     * @param reservation reservation
     * @param path        file path
     * @param amount      room price
     * @throws IOException if the invoice cannot be created
     */
    public void createInvoice(Reservation reservation, String path, double amount) throws IOException {
        Document doc = new Document(new PdfDocument(new PdfWriter(path)));
        doc.setFont(PdfFontFactory.createFont("src/main/resources/static/myFont.ttf", "Cp1251"));
        doc.add(new Paragraph("Резвизиты отеля\n\n\n"));
        float[] pointColumnWidths = {10F, 100F, 100F, 100F, 80F, 80F};
        Table table = new Table(pointColumnWidths);

        table.addCell(new Cell().add("№"));
        table.addCell(new Cell().add("Услуга"));
        table.addCell(new Cell().add("Характеристика"));
        table.addCell(new Cell().add("Количество"));
        table.addCell(new Cell().add("Цена"));
        table.addCell(new Cell().add("Сумма"));

        table.addCell(new Cell().add("1"));
        table.addCell(new Cell().add("Проживание"));
        table.addCell(new Cell().add(String.valueOf(reservation.getRoom())));
        table.addCell(new Cell().add(String.valueOf(reservation.getDays())));
        table.addCell(new Cell().add(String.valueOf(amount)));
        table.addCell(new Cell().add(String.valueOf(amount * reservation.getDays())));

        table.addCell(new Cell().add("2"));
        table.addCell(new Cell().add("Питание"));
        table.addCell(new Cell().add(String.valueOf(reservation.getFood().getType())));
        table.addCell(new Cell().add(String.valueOf(reservation.getDays() * reservation.getPersons())));
        table.addCell(new Cell().add(String.valueOf(reservation.getFood().getPrice())));
        table.addCell(new Cell().add(String.valueOf(reservation.getDays() * reservation.getPersons() * reservation.getFood().getPrice())));

        table.addCell(new Cell().add("3"));
        table.addCell(new Cell().add("Дополнительные услуги"));
        table.addCell(new Cell().add(reservation.getServices().getType()));
        int quantity = 0;
        if (reservation.getServices().getType().equals("стоянка")) quantity = reservation.getDays();
        if (reservation.getServices().getType().equals("трансфер")) quantity = 1;
        table.addCell(new Cell().add(String.valueOf(quantity)));
        table.addCell(new Cell().add(String.valueOf(reservation.getServices().getPrice())));
        table.addCell(new Cell().add(String.valueOf(quantity * reservation.getServices().getPrice())));
        doc.add(table);

        doc.add(new Paragraph("Итого: " + reservation.getAmount() + "\n\n\n"));
        doc.add(new Paragraph("Дата заезда: " + reservation.getArrival() + "\n" +
                "Дата отъезда: " + reservation.getDeparture() + "\n" +
                "Количество дней: " + reservation.getDays() + "\n" +
                "Тип номера: " + reservation.getRoom() + "\n" +
                "Количество гостей: " + reservation.getPersons() + "\n" +
                "Тип питания: " + reservation.getFood().getType() + "\n" +
                "Дополнительные услуги: " + reservation.getServices().getType() + "\n" +
                "Стоимость: " + reservation.getAmount() + "\n"));
        doc.close();
    }

    /**
     * The method deletes the "Invoice" file
     * @param path  file path
     */
    public void deleteInvoice(String path) {
        File file = new File(path);
        if (file.isFile()) file.delete();
    }
}