package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.Constants;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.requirements.AbstractSteps;

public class ProductSteps extends AbstractSteps {

	private static final long serialVersionUID = 6517884556963111931L;

	/**
	 * Set the product quantity and size. If Size is set to 0 field is ignored.
	 * 
	 * @param qty
	 * @param size
	 */
	@StepGroup
	public ProductBasicModel setProductAddToCart(String qty, String size) {
		if (!size.contentEquals("0")) {
			setDropDownValue(size);
		}

		setQuantity(qty);

		ProductBasicModel result = productDetailsPage().grabProductData();
		addToCart();
		waitABit(Constants.TIME_CONSTANT);
		return result;
	}

	@StepGroup
	public BasicProductModel setBasicProductAddToCart(String qty, String size,String askingPrice,String finalPrice,String ip,String discountClass) {
		BasicProductModel result = new BasicProductModel();
		if (!size.contentEquals("0")) {
			setDropDownValue(size);
		}		
		setQuantity(qty);	
		
		result = productDetailsPage().grabBasicProductData();
		result.setDiscountClass(discountClass);
		result.setProductsPrice(askingPrice);
		result.setFinalPrice(finalPrice);
		result.setPriceIP(ip);
		
		addToCart();
		waitABit(Constants.TIME_CONSTANT);
		return result;
	}
	@Step
	public RegularBasicProductModel setRegularBasicProductAddToCart(String qty, String size,String finalPrice) {
		RegularBasicProductModel result = new RegularBasicProductModel();
		if (!size.contentEquals("0")) {
			setDropDownValue(size);
		}		
		setQuantity(qty);	
		
		result = productDetailsPage().grabRegularBasicProductData();
		
		result.setFinalPrice(finalPrice);
		result.setBonusType(Constants.REGULAR_PRICE);
		result.setBunosValue("0");
		
		addToCart();
		waitABit(Constants.TIME_CONSTANT);
		return result;
	}

	@Step
	public void setDropDownValue(String size) {
		productDetailsPage().selectValueFromDropDown(size);
	}

	@Step
	public void setQuantity(String qty) {
		productDetailsPage().setPrice(qty);
	}

	@Step
	public void addToCart() {
		productDetailsPage().addToCart();
	}

}
