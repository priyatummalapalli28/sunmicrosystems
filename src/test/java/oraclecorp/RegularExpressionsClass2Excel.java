package oraclecorp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class RegularExpressionsClass2Excel
{
	public static void main(String[] args) throws Exception
	{
		File f=new File("patternmatching.xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fi);
		Sheet sh=wb.getSheet("Sheet1");
		int nour=sh.getPhysicalNumberOfRows();
		int nouc=sh.getRow(0).getLastCellNum();
		//Give headings to results in excel file
		sh.getRow(0).createCell(nouc).setCellValue("count of matches");
		//Data Driven from 2nd row(index=1)
		for(int i=1;i<nour;i++)
		{
			//Read data from excel
			DataFormatter df=new DataFormatter();
			String a=df.formatCellValue(sh.getRow(i).getCell(0));
			
			String x="jgrSfl JG igG IGI gi /&%#&* gH35H46 GI gig isfl5sf 5rg 6 gd4 GHT7 64g d6gdgdgd";
			Pattern p=Pattern.compile(a);
			Matcher m=p.matcher(x);
			int c=0;
			while(m.find())
			{
				c=c+1;
			}
			//Write result back to excel
			sh.getRow(i).createCell(nouc).setCellValue("No of matches are "+c);
		}
		
		sh.autoSizeColumn(nouc);
		
		//Save data back to excel
		FileOutputStream fo=new FileOutputStream(f);
		wb.write(fo);
		fi.close();
		fo.close();
		wb.close();
	}
}
