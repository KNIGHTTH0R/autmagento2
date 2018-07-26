package com.tests.uss36;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.KpiReportSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.KpiReportModelInfo;
import com.tools.generalCalculation.KpiReportCalculation;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US34.1 Order Import to Navision", type = "Scenarios")
@Story(Application.ValidateOrderImport.US34_1.class)
@RunWith(SerenityRunner.class)
public class US34001ValidateKpiReportTest extends BaseTest {

	@Steps
	public KpiReportSteps kpiReport;

	@Steps
	CustomVerification customVerification;
	
	
	US36001GrabbDataFromXlsxFile grabbedKpiData =new US36001GrabbDataFromXlsxFile();
	KpiReportCalculation  calculationKpiData =new KpiReportCalculation();
	
	private KpiReportModelInfo kpiCalculated = new KpiReportModelInfo();
	private KpiReportModelInfo kpiGrabbed= new KpiReportModelInfo();


	@Before
	public void setUp() throws Exception {
		
		kpiCalculated = calculationKpiData.kpiReportInfo("");
		kpiGrabbed = grabbedKpiData.grabbedKpiInfoFromfile();
		
		
		/*System.out.println(kpiCalculated);
		System.out.println(kpiGrabbed);*/

		}

	@Test
	public void us34001ValidateKpiReportTest() throws Exception {
		
		kpiReport.validateKpi(kpiCalculated, kpiGrabbed);
		customVerification.printErrors();
	
		
		
	}
}
