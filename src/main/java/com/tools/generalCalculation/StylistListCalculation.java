package com.tools.generalCalculation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.connectors.http.StylistListMagentoCalls;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.geolocation.DistanceCalculator;
import com.tools.utils.DateUtils;

public class StylistListCalculation {

	/**
	 * Takes the whole list of stylecoaches in two rounds(memory
	 * constraints),filters the list by qualifications and returns compatible
	 * list in range.If more than five exist, returns 5 closest
	 * 
	 * mode - 1 for customer lead ,2 for host lead and 3 for stylecoach lead
	 * 
	 * @param coordinatesModel
	 * @param range
	 * @param filter
	 * @param operand
	 * @param operand2
	 * @param filterValue
	 * @param mode
	 */
	public static List<DBStylistModel> getCompatibleStylistsInRangeFromList(CoordinatesModel coordinatesModel,
			String range, String filter, String operand, String operand2, String filterValue, int mode) {

		List<DBStylistModel> initialList = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart2 = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart3 = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart4 = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart5 = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart6 = new ArrayList<DBStylistModel>();

		initialList = StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "0", "1000");
		initialListPart2 = StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "1001", "2000");
		initialListPart3 = StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "2001", "3000");
		initialListPart4 = StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "3001", "4000");
		initialListPart5= StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "4001", "5000");
		initialListPart6 =StylistListMagentoCalls.getStylistList(filter, "gt", "5000");
		initialList.addAll(initialListPart2);
		initialList.addAll(initialListPart3);
		initialList.addAll(initialListPart4);
		initialList.addAll(initialListPart5);
		initialList.addAll(initialListPart6);
		
	//	System.out.println(initialList);

		List<DBStylistModel> compatibleList = new ArrayList<DBStylistModel>();

		for (DBStylistModel dbStylistModel : initialList) {

			switch (mode) {

			case 1:
				if (!isStylistIncompatibleForCustomerRetrieval(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(
							calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);
				}
				break;

			case 2:
				if (!isStylistIncompatibleForHost(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(
							calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);
				}
				break;

			case 3:
				if (!isStylistIncompatibleForSCRetrieval(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(
							calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);
				}
				break;

			case 4:
				if (!isStylistIncompatibleForDistributionDuringCheckout(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(
							calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);
				}
				break;
			}
		}

		return getFirstFiveStylistInRangeOrClosest(coordinatesModel, compatibleList, range);

	}
	/**
	 * Takes the whole list of stylecoaches in two rounds(memory
	 * constraints),filters the list by qualifications and returns compatible
	 * list in range.If more than five exist, returns 5 from each category
	 * 
	 * mode - 1 for customer lead ,2 for host lead and 3 for stylecoach lead
	 * 
	 * @param coordinatesModel
	 * @param range
	 * @param filter
	 * @param operand
	 * @param operand2
	 * @param filterValue
	 * @param mode
	 */
	public static List<DBStylistModel> getCompatibleStylistsForDysks(CoordinatesModel coordinatesModel, String range,
			String filter, String operand, String operand2, String filterValue, int mode) {

		/*List<DBStylistModel> initialList = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart2 = new ArrayList<DBStylistModel>();

		initialList = StylistListMagentoCalls.getStylistList(filter, operand, filterValue);
		initialListPart2 = StylistListMagentoCalls.getStylistList(filter, operand2, filterValue);
		initialList.addAll(initialListPart2);
		*/
		
		List<DBStylistModel> initialList = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart2 = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart3 = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart4 = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart5 = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart6 = new ArrayList<DBStylistModel>();

		initialList = StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "0", "1000");
		initialListPart2 = StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "1001", "2000");
		initialListPart3 = StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "2001", "3000");
		initialListPart4 = StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "3001", "4000");
		initialListPart5= StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "4001", "5000");
		initialListPart6 =StylistListMagentoCalls.getStylistList(filter, "gt", "5000");
		
		initialList.addAll(initialListPart2);
		initialList.addAll(initialListPart3);
		initialList.addAll(initialListPart4);
		initialList.addAll(initialListPart5);
		initialList.addAll(initialListPart6);
		
		List<DBStylistModel> compatibleList = new ArrayList<DBStylistModel>();

		for (DBStylistModel dbStylistModel : initialList) {

			switch (mode) {

		
			case 1:
				if (!isStylistIncompatibleForCustomerRetrievalAssignation(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(
							calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					System.out.println("stylist compatibil: "+dbStylistModel.getEmail());
					compatibleList.add(dbStylistModel);
				}
				break;

			case 2:
				if (!isStylistIncompatibleForHostAssignation(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(
							calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);
				}
				break;

			case 3:
				if (!isStylistIncompatibleForSCRetrievalAssignation(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(
							calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);
				}
				break;

			case 4:
				if (!isStylistIncompatibleForDistributionDuringCheckout(dbStylistModel)) {

					dbStylistModel.setDistanceFromCoordinates(
							calculateDistanceFromCustomersCoordinates(coordinatesModel, dbStylistModel));
					compatibleList.add(dbStylistModel);
				}
				break;
			}
		}

		return getFiveStylistsFromListForeachAgeCategoryIfExist(coordinatesModel, compatibleList, range);

	}

	public static List<DBStylistModel> getDykscStylistByName(String firstName, String lastName, String filter,
			String operand, String operand2, String filterValue, int mode) {
/*
		List<DBStylistModel> initialList = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart2 = new ArrayList<DBStylistModel>();

		initialList = StylistListMagentoCalls.getStylistList(filter, operand, filterValue);
		initialListPart2 = StylistListMagentoCalls.getStylistList(filter, operand2, filterValue);
		initialList.addAll(initialListPart2);*/
		
		List<DBStylistModel> initialList = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart2 = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart3 = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart4 = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart5 = new ArrayList<DBStylistModel>();
		List<DBStylistModel> initialListPart6 = new ArrayList<DBStylistModel>();

		initialList = StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "0", "1000");
		initialListPart2 = StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "1001", "2000");
		initialListPart3 = StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "2001", "3000");
		initialListPart4 = StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "3001", "4000");
		initialListPart5= StylistListMagentoCalls.getStylistListInRange(filter, "from", "to", "4001", "5000");
		initialListPart6 =StylistListMagentoCalls.getStylistList(filter, "gt", "5000");
		
		initialList.addAll(initialListPart2);
		initialList.addAll(initialListPart3);
		initialList.addAll(initialListPart4);
		initialList.addAll(initialListPart5);
		initialList.addAll(initialListPart6);

		List<DBStylistModel> compatibleList = new ArrayList<DBStylistModel>();
	//	int i=0;
		for (DBStylistModel dbStylistModel : initialList) {
			
			if (!isStylistIncompatibleForDykscSearchByName(dbStylistModel, firstName, lastName)) {

				compatibleList.add(dbStylistModel);

			}
		}
		return compatibleList;
	}

	private static boolean isStylistIncompatibleForCustomerRetrieval(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0")
				|| stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getQualifiedCustomer().contentEquals("0")
				|| stylistModel.getStylistId().contentEquals("1")
				|| !stylistModel.getStylistQuiteDate().contentEquals("0")
				|| !stylistModel.getStylistContractStatus().contentEquals("0")
				|| stylistModel.getStylistContractStatus().contentEquals("3");

	}

	private static boolean isStylistIncompatibleForCustomerRetrievalAssignation(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0")
				|| stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getQualifiedCustomer().contentEquals("0")
				|| stylistModel.getStylistId().contentEquals("1")
				|| !stylistModel.getStylistQuiteDate().contentEquals("0")
				|| !stylistModel.getStylistContractStatus().contentEquals("0")
				|| stylistModel.getStylistContractStatus().contentEquals("3")
				|| !stylistModel.getConfirmed().contentEquals("1") || !stylistModel.getAvatar().contentEquals("1")
				|| !stylistModel.getSlogan().contentEquals("1") || stylistModel.getBirthDate().contentEquals("");

	}

	private static boolean isStylistIncompatibleForSCRetrieval(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0")
				|| stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getStylistId().contentEquals("1") || stylistModel.getQualifiedSC().contentEquals("0")
				|| !stylistModel.getStylistQuiteDate().contentEquals("0")
				|| !stylistModel.getStylistContractStatus().contentEquals("0")
				|| stylistModel.getStylistContractStatus().contentEquals("3")
				|| Integer.parseInt(stylistModel.getTotalSCCurrentWeek()) >= Integer
						.parseInt(stylistModel.getMaxSCPerWeek());
	}

	private static boolean isStylistIncompatibleForSCRetrievalAssignation(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0")
				|| stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getStylistId().contentEquals("1") || stylistModel.getQualifiedSC().contentEquals("0")
				|| !stylistModel.getStylistQuiteDate().contentEquals("0")
				|| !stylistModel.getStylistContractStatus().contentEquals("0")
				|| stylistModel.getStylistContractStatus().contentEquals("3")
				|| Integer.parseInt(stylistModel.getTotalSCCurrentWeek()) >= Integer
						.parseInt(stylistModel.getMaxSCPerWeek())
				|| !stylistModel.getConfirmed().contentEquals("1") || !stylistModel.getAvatar().contentEquals("1")
				|| !stylistModel.getSlogan().contentEquals("1") || stylistModel.getBirthDate().contentEquals("");
	}

	private static boolean isStylistIncompatibleForHost(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0")
				|| stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getQualifiedHost().contentEquals("0") || stylistModel.getStylistId().contentEquals("1")
				|| !stylistModel.getStylistQuiteDate().contentEquals("0")
				|| !stylistModel.getStylistContractStatus().contentEquals("0")
				|| stylistModel.getStylistContractStatus().contentEquals("3");
	}

	private static boolean isStylistIncompatibleForHostAssignation(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0")
				|| stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getQualifiedHost().contentEquals("0") || stylistModel.getStylistId().contentEquals("1")
				|| !stylistModel.getStylistQuiteDate().contentEquals("0")
				|| !stylistModel.getStylistContractStatus().contentEquals("0")
				|| stylistModel.getStylistContractStatus().contentEquals("3")
				|| !stylistModel.getConfirmed().contentEquals("1") || !stylistModel.getAvatar().contentEquals("1")
				|| !stylistModel.getSlogan().contentEquals("1") || stylistModel.getBirthDate().contentEquals("");
	}

	private static boolean isStylistIncompatibleForDistributionDuringCheckout(DBStylistModel stylistModel) {
		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLattitude().contentEquals("0")
				|| stylistModel.getQualifiedCustomer().contentEquals("0")
				|| stylistModel.getStylistId().contentEquals("1");
	}

	private static boolean isStylistIncompatibleForDykscSearchByName(DBStylistModel stylistModel, String firstName,
			String lastName) {
	/*	System.out.println(" ----->   start    < -----");
		System.out.println("stylistModel.getStatus() "+stylistModel.getStatus().contentEquals("0"));
		System.out.println("stylistModel.getLeadRetrievalPaused() "+stylistModel.getLeadRetrievalPaused().contentEquals("1"));
		System.out.println("stylistModel.getStylistId "+stylistModel.getStylistId().contentEquals("1"));
		System.out.println("!stylistModel.getConfirmed() "+!stylistModel.getConfirmed().contentEquals("1"));
		System.out.println("!stylistModel.getAvatar() "+!stylistModel.getAvatar().contentEquals("1"));
		System.out.println("!stylistModel.getSlogan() "+!stylistModel.getSlogan().contentEquals("1"));
		System.out.println("stylistModel.getBirthDate() "+stylistModel.getBirthDate());
		System.out.println("stylistModel.getFirstName() "+stylistModel.getFirstName().contains(firstName));
		System.out.println("!stylistModel.getLastName() "+!stylistModel.getLastName().contains(lastName));
		System.out.println("!stylistModel.getStylistQuiteDate() "+!stylistModel.getStylistQuiteDate().contentEquals("0"));*/

		return stylistModel.getStatus().contentEquals("0") || stylistModel.getLeadRetrievalPaused().contentEquals("1")
				|| stylistModel.getStylistId().contentEquals("1") || !stylistModel.getConfirmed().contentEquals("1")
				|| !stylistModel.getAvatar().contentEquals("1") || !stylistModel.getSlogan().contentEquals("1")
				|| !stylistModel.getFirstName().contains(firstName)
				|| !stylistModel.getLastName().contains(lastName) || !stylistModel.getStylistQuiteDate().contentEquals("0") ;
	}

	public static boolean isStylistInRange(CoordinatesModel coordinateaModel, DBStylistModel dBStylistModel,
			String range) {
		double distance = DistanceCalculator.getDistance(Double.parseDouble(coordinateaModel.getLattitude()),
				Double.parseDouble(coordinateaModel.getLongitude()), Double.parseDouble(dBStylistModel.getLattitude()),
				Double.parseDouble(dBStylistModel.getLongitude()), "K");
		return distance <= Double.parseDouble(range);
	}

	public static List<DBStylistModel> sortStylistListByRange(List<DBStylistModel> stylistsList) {

		Collections.sort(stylistsList, new Comparator<DBStylistModel>() {

			public int compare(DBStylistModel stylist1, DBStylistModel stylist2) {
				double delta = Double.parseDouble(stylist1.getDistanceFromCoordinates())
						- Double.parseDouble(stylist2.getDistanceFromCoordinates());
				if (delta > 0)
					return 1;
				if (delta < 0)
					return -1;
				return 0;
			}
		});

		return stylistsList;
	}

	/**
	 * Returns the compatible stylists ordered by distance.If more than 5
	 * stylists are found,the top five are taken.If no stylist is found in
	 * range,the closest stylist is taken
	 * 
	 * @param coordinatesModel
	 * @param stylistsList
	 * @param range
	 * @return compatibleStylistsInRange
	 */
	public static List<DBStylistModel> getFirstFiveStylistInRangeOrClosest(CoordinatesModel coordinatesModel,
			List<DBStylistModel> stylistsList, String range) {

		stylistsList = sortStylistListByRange(stylistsList);

		List<DBStylistModel> compatibleStylists = new ArrayList<DBStylistModel>();

		for (DBStylistModel stylist : stylistsList) {
			if (isStylistInRange(coordinatesModel, stylist, range)) {
				compatibleStylists.add(stylist);
			}
		}
		if (compatibleStylists.size() > 5) {
			compatibleStylists = compatibleStylists.subList(0, 5);

		} else if (compatibleStylists.size() == 0) {
			compatibleStylists.add(stylistsList.get(0));

		}

		return compatibleStylists;
	}

	/**
	 * Returns 5 stylecoaces if more than 5 are found. If less than five are
	 * found,all the stylecoaches will be returned. If more than five are found,
	 * each age category will have 1 stylecoach if there is a stylecoach for
	 * that category,the rest of available places will be filled in with
	 * compatible stylecoaches in range (random)
	 * 
	 * @param coordinatesModel
	 * @param stylistsList
	 * @param range
	 * @return compatibleStylistsInRange
	 */

	public static List<DBStylistModel> getFiveStylistsFromListForeachAgeCategoryIfExist(
			CoordinatesModel coordinatesModel, List<DBStylistModel> stylistsList, String range) {

		
		stylistsList = sortStylistListByRange(stylistsList);
		List<DBStylistModel> category30Age = new ArrayList<DBStylistModel>();
		List<DBStylistModel> category45Age = new ArrayList<DBStylistModel>();
		List<DBStylistModel> category100Age = new ArrayList<DBStylistModel>();

		List<DBStylistModel> compatibleStylists = new ArrayList<DBStylistModel>();

		for (DBStylistModel stylist : stylistsList) {
			if (isStylistInRange(coordinatesModel, stylist, range)) {
				if (calculateStylistAge(stylist) <= 30) {
					category30Age.add(stylist);

				} else if (calculateStylistAge(stylist) > 30 && calculateStylistAge(stylist) <= 45) {
					category45Age.add(stylist);

				} else if (calculateStylistAge(stylist) > 45 && calculateStylistAge(stylist) <= 100) {
					category100Age.add(stylist);
				}

				compatibleStylists.add(stylist);
			}
		}
		if (compatibleStylists.size() > 5) {

			compatibleStylists.clear();

			while (compatibleStylists.size() < 5) {

				if (category30Age.size() > 0) {
					compatibleStylists.add(category30Age.get(0));
					category30Age.remove(0);
				}
				if (category45Age.size() > 0) {
					compatibleStylists.add(category45Age.get(0));
					category45Age.remove(0);
				}
				if (category100Age.size() > 0) {
					compatibleStylists.add(category100Age.get(0));
					category100Age.remove(0);
				}
				// this is because we can reach the 5 elements in the first if
				// and until we enter in while loop,we can have 6 or 7
				// elements
				compatibleStylists.subList(0, 5);
			}

		} else if (compatibleStylists.size() == 0) {
			compatibleStylists.add(stylistsList.get(0));

		}

		return compatibleStylists;
	}

	public static String calculateDistanceFromCustomersCoordinates(CoordinatesModel coordinateaModel,
			DBStylistModel dBStylistModel) {
		double distance = DistanceCalculator.getDistance(Double.parseDouble(coordinateaModel.getLattitude()),
				Double.parseDouble(coordinateaModel.getLongitude()), Double.parseDouble(dBStylistModel.getLattitude()),
				Double.parseDouble(dBStylistModel.getLongitude()), "K");

		return String.valueOf(distance);
	}

	public static int calculateStylistAge(DBStylistModel stylist) {
		return DateUtils.getAge(stylist.getBirthDate());
	}

}
