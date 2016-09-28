package com.tools.generalCalculation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.tools.data.commission.CommissionStylistModel;

public class CommissionService {

	// TODO move this from here
	public static List<CommissionStylistModel> getStylistsFromLevel(List<CommissionStylistModel> allStylists, String stylistId, int level) throws Exception {

		List<CommissionStylistModel> levelStylists = new ArrayList<CommissionStylistModel>();

		for (CommissionStylistModel stylist : allStylists) {
			String[] ancestors = stylist.getAncestors().split(",");
			if (ArrayUtils.indexOf(ancestors, stylistId) == level - 1) {
				levelStylists.add(stylist);
			}
		}
		return levelStylists;
	}

}
