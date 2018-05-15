package com.tools.commision;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tools.data.frontend.ClosedPartyPerformanceModel;
import com.tools.data.frontend.PartyBonusCalculationModel;
import com.tools.data.soap.DBOrderModel;

public class PartyHostBonusCalculator {

	private static List<String> payedStatusesList = new ArrayList<String>(
			Arrays.asList("complete", "payment_complete", "closed"));
	
	private static List<String> notPayedStatusesList = new ArrayList<String>(Arrays.asList("pending", "processing",
			"waiting_authorization", "payment_review", "payment_failed", "pending_payment", "payment_in_progress"));
	
	private static boolean isNotPayed(String  orderStatus) {
		boolean found = false;
		for (String status : notPayedStatusesList) {
			if (orderStatus.contentEquals(status)) {
				found = true;
			}
		}
		return found;
	}
	
	private static boolean isPayed(String  orderStatus) {
		boolean found = false;
		for (String status : payedStatusesList) {
			if (orderStatus.contentEquals(status)) {
				found = true;
			}
		}
		return found;
	}

	public static  List<PartyBonusCalculationModel> partyBonusCalculationModelList (List<DBOrderModel> orders)  {
		BigDecimal grandTotal=BigDecimal.ZERO;
		
		
		List<PartyBonusCalculationModel> hostBonussesList=new ArrayList<PartyBonusCalculationModel>();
		
		for (DBOrderModel order : orders) {
			PartyBonusCalculationModel hostBonus=new PartyBonusCalculationModel();
			grandTotal= BigDecimal.valueOf(Double.parseDouble(order.getGrandTotal())).setScale(2, RoundingMode.HALF_UP);
			hostBonus.setTotal(grandTotal.toString());
			hostBonus.setIp(order.getTotalIp());
			hostBonus.setOrderId(order.getIncrementId());
			hostBonus.setDate(order.getCreatedAt());
			hostBonus.setOrderStatus(order.getStatus());
			hostBonus.setShippingValue(order.getShippingAmount());
			hostBonussesList.add(hostBonus);
			
		}
		return hostBonussesList;
	}
	
	public static  ClosedPartyPerformanceModel partyTotalBonusCalculation (List<PartyBonusCalculationModel> partyBonusses, String percentDiscount, boolean isFirst)  {
		BigDecimal grandTotal=BigDecimal.ZERO;
		BigDecimal totalIp=BigDecimal.ZERO;
		BigDecimal ipThisParty=BigDecimal.ZERO;
		BigDecimal ipInProgress=BigDecimal.ZERO;
		BigDecimal shippingValue=BigDecimal.ZERO;
		ClosedPartyPerformanceModel hostBonus=new ClosedPartyPerformanceModel();
		
		for (PartyBonusCalculationModel bonus : partyBonusses) {
			
			grandTotal= grandTotal.add(BigDecimal.valueOf(Double.parseDouble(bonus.getTotal())).setScale(2, RoundingMode.HALF_UP));
			totalIp=totalIp.add(BigDecimal.valueOf(Double.parseDouble(bonus.getIp())).setScale(2, RoundingMode.HALF_UP));
			if(isPayed(bonus.getOrderStatus())){
				ipThisParty=ipThisParty.add(BigDecimal.valueOf(Double.parseDouble(bonus.getIp())).setScale(2, RoundingMode.HALF_UP));
			}
			if(isNotPayed(bonus.getOrderStatus())){
				ipInProgress=ipInProgress.add(BigDecimal.valueOf(Double.parseDouble(bonus.getIp())).setScale(2, RoundingMode.HALF_UP));
			}
			
			shippingValue=shippingValue.add(BigDecimal.valueOf(Double.parseDouble(bonus.getShippingValue())));
			
		}
		
		
		hostBonus.setRetail(grandTotal.toString());
		hostBonus.setRetailNoShipping(grandTotal.subtract(shippingValue).toString());
		hostBonus.setIp(totalIp.toString());
		hostBonus.setNoOfOrders(String.valueOf(partyBonusses.size()));
		hostBonus.setIpThisParty(ipThisParty.toString());
		hostBonus.setIpInPayment(ipInProgress.toString());
		hostBonus.setShippingTotal(shippingValue.toString());
		hostBonus.setJewelryBonus(calculatePartyReatail(grandTotal,shippingValue,percentDiscount,isFirst));
		return hostBonus;
	}
	
	public static String calculatePartyReatail(BigDecimal partyTotal,BigDecimal shippingValue, String percentDiscount, boolean isFirst){
	
		BigDecimal retail= BigDecimal.ZERO;
		BigDecimal value= BigDecimal.ZERO;
		BigDecimal hundred=BigDecimal.valueOf(100);
		
		
		
		value=BigDecimal.valueOf(Integer.parseInt(percentDiscount)).divide(hundred);
		retail=partyTotal.subtract(shippingValue).multiply(value).setScale(2, RoundingMode.HALF_UP);
		
		return retail.toString();
		
	}
	
	public static void main(String[] args) {
		System.out.println(calculatePartyReatail(BigDecimal.valueOf(245.75),BigDecimal.valueOf(5.9),"10",true));
	}

}
