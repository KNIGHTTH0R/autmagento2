package com.tests.uss23;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApiCalls;
import com.connectors.navSqlServer.NavQueries;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.tests.BaseTest;
import com.tools.data.navision.SyncInfoModel;
import com.tools.requirements.Application;

@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_1.class)
@RunWith(ThucydidesRunner.class)
public class US23001VerifyStockSyncAfterOrderImportTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;

	List<SyncInfoModel> syncronizedMagentoProducts = new ArrayList<SyncInfoModel>();
	List<SyncInfoModel> syncronizedNavProducts = new ArrayList<SyncInfoModel>();

	private static List<String> idsList = new ArrayList<String>(Arrays.asList("1292", "1658"));
	private static List<String> skuList = new ArrayList<String>(Arrays.asList("R065SV-18", "N093SV"));

	@Before
	public void setUp() throws Exception {

		for (String id : idsList) {
			syncronizedMagentoProducts.add(ApiCalls.getMagProductInfo(id));
		}

		for (String sku : skuList) {
			String[] skuParts = sku.split("-");
			syncronizedNavProducts.add(NavQueries.getSyncProductInfo(skuParts[0], skuParts.length == 1 ? "" : skuParts[1]));
		}
	}

	@Test
	public void us23001VerifyStockSyncAfterOrderImportTest() throws SQLException {

	}
}
