package com.tests.uss36;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tools.data.KpiReportModel;
import com.tools.data.KpiReportModelInfo;

public class US36001GrabbDataFromXlsxFile {

	@SuppressWarnings("deprecation")
	public static List<KpiReportModel> readExcelData(String fileName) {
		List<KpiReportModel> countriesList = new ArrayList<KpiReportModel>();
		
		try {
			//Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(fileName);
			
			//Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if(fileName.toLowerCase().endsWith("xlsx")){
				workbook = new XSSFWorkbook(fis);
			}else if(fileName.toLowerCase().endsWith("xls")){
				workbook = new HSSFWorkbook(fis);
			}
			
			//Get the number of sheets in the xlsx file
			int numberOfSheets = workbook.getNumberOfSheets();
			
			//loop through each of the sheets
			for(int i=0; i < numberOfSheets; i++){
				
				//Get the nth sheet from the workbook
				Sheet sheet = workbook.getSheetAt(i);
				
				//every sheet has rows, iterate over them
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) 
		        {
					String name = "";
					String shortCode = "";
					
					//Get the row object
					Row row = rowIterator.next();
					
					//Every row has columns, get the column iterator and iterate over them
					Iterator<Cell> cellIterator = row.cellIterator();
		             
		            while (cellIterator.hasNext()) 
		            {
		            	//Get the Cell object
		            	Cell cell = cellIterator.next();
		            	
		            	//check the cell type and process accordingly
		            	switch(cell.getCellType()){
		            	case Cell.CELL_TYPE_STRING:
		            		if(shortCode.equalsIgnoreCase("")){
		            			shortCode = cell.getStringCellValue().trim();
		            		}else if(name.equalsIgnoreCase("")){
		            			//2nd column
		            			name = cell.getStringCellValue().trim();
		            		}else{
		            			//random data, leave it
		            			System.out.println("Random data::"+cell.getStringCellValue());
		            		}
		            		break;
		            	case Cell.CELL_TYPE_NUMERIC:
		            		System.out.println("Random data::"+cell.getNumericCellValue());
		            	}
		            } //end of cell iterator
		            KpiReportModel c = new KpiReportModel(name, shortCode);
		            countriesList.add(c);
		        } //end of rows iterator
				
				
			} //end of sheets for loop
			
			//close file input stream
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return countriesList;
	}

	
	
	public KpiReportModelInfo grabbedKpiInfoFromfile() {
		List<KpiReportModel> list = readExcelData("/home/emilianmelian/Downloads/kpireport/report1.xlsx");
		KpiReportModelInfo info = new KpiReportModelInfo();
		for (KpiReportModel kpiReportModel : list) {
			if(kpiReportModel.getKey().contains("Salesforce beginning")) {
				info.setSalesforceBeginning(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Recruits")) {
				info.setRecruits(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Deactivated")) {
				info.setDeactivated(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("SF ending")) {
				info.setSfEnding(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Performing SF excl. deactivated")) {
				info.setPerformingSf(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Total # Parties")) {
				info.setTotalParties(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Average # Parties per Active SF")) {
				info.setAvgPartiesPerActiveSf(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Total # Follow up Parties")) {
				info.setTotalFollowUpParties(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Average # Follow up Parties per Active SF")) {
				info.setAvgFollowUpPartiesPerActiveSf(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Average # Guest")) {
				info.setAvgGuest(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Average # Buying Guest")) {
				info.setAvgBuyingguest(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Buying Rate %")) {
				info.setBuyingRate(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Party Average (before returns, inc. VAT)")) {
				info.setPartyAvgBeforeReturnIncVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Party Average (before returns, excl. VAT)")) {
				info.setPartyAvgBeforeReturnExclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Online sales (incl. VAT)")) {
				info.setOnlineSalesInclVat(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Online Sales (excl. VAT)")) {
				info.setOnlineSalesExclVatl(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Shop for myself (incl. VAT)")) {
				info.setSfmInclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Shop for myself (excl. VAT)")) {
				info.setSfmExclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Total Turnover (before returns, incl. VAT)")) {
				info.setTotalTurnoverBeforeReturnsInclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Total Turnover (before returns, excl. VAT)")) {
				info.setTotalTurnoverBeforeReturnsExclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Returns € (incl. VAT)")) {
				info.setReturnsInclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Returns € (excl. VAT)")) {
				info.setReturnsExclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Return Rate %")) {
				info.setReturnRate(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Total Turnover (after returns, incl. VAT)")) {
				info.setTotalTurnoverAfterReturnsInclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Total Turnover (after returns, excl. VAT)")) {
				info.setTotalTurnoverAfterReturnsExclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Parties planned in month + 1")) {
				info.setPartiesPlannedInMonth1(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned in month + 2")) {
				info.setPartiesPlannedInMonth2(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned in month + 3")) {
				info.setPartiesPlannedInMonth3(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned in month + 4")) {
				info.setPartiesPlannedInMonth4(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Parties planned current week (since generating report until first Monday)")) {
				info.setPartiesPlannedCurrentWeek(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Parties planned week +1 (starting with Monday after report creation)")) {
				info.setPartiesPlannedWeek1(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +2")) {
				info.setPartiesPlannedWeek2(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +3")) {
				info.setPartiesPlannedWeek3(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +4")) {
				info.setPartiesPlannedWeek4(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +5")) {
				info.setPartiesPlannedWeek5(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +6")) {
				info.setPartiesPlannedWeek6(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +7")) {
				info.setPartiesPlannedWeek7(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +8")) {
				info.setPartiesPlannedWeek8(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +9")) {
				info.setPartiesPlannedWeek9(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +10")) {
				info.setPartiesPlannedWeek10(kpiReportModel.getValue());
			}
			
			
		//	System.out.println(kpiReportModel.getKey()+" : "+ kpiReportModel.getValue());
		}
		//System.out.println("Country List\n"+list);
		return info;
	}
	
	
	public static void main(String args[]){
		List<KpiReportModel> list = readExcelData("/home/emilianmelian/Downloads/kpireport/report1.xlsx");
		KpiReportModelInfo info = new KpiReportModelInfo();
		for (KpiReportModel kpiReportModel : list) {
			if(kpiReportModel.getKey().contains("Salesforce beginning")) {
				info.setSalesforceBeginning(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Recruits")) {
				info.setRecruits(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Deactivated")) {
				info.setDeactivated(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("SF ending")) {
				info.setSfEnding(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Performing SF excl. deactivated")) {
				info.setPerformingSf(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Total # Parties")) {
				info.setTotalParties(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Average # Parties per Active SF")) {
				info.setAvgPartiesPerActiveSf(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Total # Follow up Parties")) {
				info.setTotalFollowUpParties(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Average # Follow up Parties per Active SF")) {
				info.setAvgFollowUpPartiesPerActiveSf(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Average # Guest")) {
				info.setAvgGuest(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Average # Buying Guest")) {
				info.setAvgBuyingguest(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Buying Rate %")) {
				info.setBuyingRate(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Party Average (before returns, inc. VAT)")) {
				info.setPartyAvgBeforeReturnIncVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Party Average (before returns, excl. VAT)")) {
				info.setPartyAvgBeforeReturnExclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Online sales (incl. VAT)")) {
				info.setOnlineSalesInclVat(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Online Sales (excl. VAT)")) {
				info.setOnlineSalesExclVatl(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Shop for myself (incl. VAT)")) {
				info.setSfmInclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Shop for myself (excl. VAT)")) {
				info.setSfmExclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Total Turnover (before returns, incl. VAT)")) {
				info.setTotalTurnoverBeforeReturnsInclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Total Turnover (before returns, excl. VAT)")) {
				info.setTotalTurnoverBeforeReturnsExclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Returns € (incl. VAT)")) {
				info.setReturnsInclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Returns € (excl. VAT)")) {
				info.setReturnsExclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Return Rate %")) {
				info.setReturnRate(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Total Turnover (after returns, incl. VAT)")) {
				info.setTotalTurnoverAfterReturnsInclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Total Turnover (after returns, excl. VAT)")) {
				info.setTotalTurnoverAfterReturnsExclVat(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Parties planned in month + 1")) {
				info.setPartiesPlannedInMonth1(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned in month + 2")) {
				info.setPartiesPlannedInMonth2(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned in month + 3")) {
				info.setPartiesPlannedInMonth3(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned in month + 4")) {
				info.setPartiesPlannedInMonth4(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Parties planned current week (since generating report until first Monday)")) {
				info.setPartiesPlannedCurrentWeek(kpiReportModel.getValue());
			}
			
			if(kpiReportModel.getKey().contains("Parties planned week +1 (starting with Monday after report creation)")) {
				info.setPartiesPlannedWeek1(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +2")) {
				info.setPartiesPlannedWeek2(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +3")) {
				info.setPartiesPlannedWeek3(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +4")) {
				info.setPartiesPlannedWeek4(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +5")) {
				info.setPartiesPlannedWeek5(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +6")) {
				info.setPartiesPlannedWeek6(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +7")) {
				info.setPartiesPlannedWeek7(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +8")) {
				info.setPartiesPlannedWeek8(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +9")) {
				info.setPartiesPlannedWeek9(kpiReportModel.getValue());
			}
			if(kpiReportModel.getKey().contains("Parties planned week +10")) {
				info.setPartiesPlannedWeek10(kpiReportModel.getValue());
			}
			
			
			System.out.println(kpiReportModel.getKey()+" : "+ kpiReportModel.getValue());
		}
		//System.out.println("Country List\n"+list);
	}

}
