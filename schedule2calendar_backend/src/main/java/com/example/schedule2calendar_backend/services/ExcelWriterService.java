package com.example.schedule2calendar_backend.services;

import com.aspose.cells.Cell;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ExcelWriterService {
    public byte[] createExcelFile(String schedule) throws IOException {
        Workbook workbook = new Workbook();
        Worksheet sheet = workbook.getWorksheets().get(0);

        String[] rows = schedule.split("\n");
        int rowNum = 0;

        for (String rowText : rows) {
            Cell cell = sheet.getCells().get(rowNum, 0);
            cell.putValue(rowText);
            rowNum++;
        }

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.save(bos, SaveFormat.XLSX);
            workbook.save("Output.xlsx");
            return bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
