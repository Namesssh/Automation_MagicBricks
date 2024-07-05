package xl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Reader {

	public Reader(String filename, String sheetname) {
		read(filename, sheetname);
	}

	public Reader(String filename) {
		this(filename, null);
	}

	private Sheet sheet;

	private void read(String filename, String sheetname) {
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\" + filename + ".xlsx";
		File f = new File(path);
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(f);

			Workbook wb;
			try {
				wb = WorkbookFactory.create(fstream);

				if (sheetname != null) {
					this.sheet = wb.getSheet(sheetname);
				} else {
					this.sheet = wb.getSheetAt(0);
				}

			} catch (EncryptedDocumentException | IOException e) {
				System.out.println("[Error] " + filename + ".xlsx is either encrypted or not readable");
				e.printStackTrace();
			}

		} catch (FileNotFoundException e1) {
			System.out.println("[Error] " + filename + ".xlsx not found on the specified location");
			e1.printStackTrace();
		}

	}

	public int maxRows() {
		return sheet.getPhysicalNumberOfRows();
	}

	public int maxCols() {
		int count = 0;
		Iterator<Row> iterator = sheet.rowIterator();
		while (iterator.hasNext()) {
			Row row = iterator.next();
			if (count < row.getLastCellNum()) {
				count = row.getLastCellNum();
			}
		}
		return count;
	}

	public String find(int row, int column) {
		DataFormatter formatter = new DataFormatter();
		String value = formatter.formatCellValue(sheet.getRow(row).getCell(column));
		return value.isEmpty() ? null : value.trim();
	}

	public Manager columns() {
		ArrayList<Chunk> cols = new ArrayList<Chunk>();
		for (int i = 0; i < maxCols(); i++) {
			Chunk col = new Chunk();
			for (int j = 0; j < maxRows(); j++) {
				col.add(find(j, i));
			}
			col.trimEnd();
			col.removeArgs();
			if (i == 0)
				col.removeArgsInvert();
			cols.add(col);
		}
		Manager manager = new Manager(cols);

		return manager;
	}

	public Manager rows() {
		ArrayList<Chunk> rows = new ArrayList<Chunk>();
		for (int i = 0; i < maxRows(); i++) {
			Chunk row = new Chunk();
			for (int j = 0; j < maxCols(); j++) {
				row.add(find(i, j));
			}
			row.trimEnd();
			row.removeArgs();
			if (i == 0)
				row.removeArgsInvert();
			rows.add(row);
		}
		Manager manager = new Manager(rows);

		return manager;
	}

	public String header(int index) {
		return rows().at(0, index);
	}

	public String[] headers() {
		return rows().at(0).toArray();
	}

//	public static void main(String args[]) {
//		Reader r = new Reader("propworth", "valid");
//	}
}
