package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.PomProductModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.StarterSetProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.env.constants.ContextConstants;
import com.tools.env.constants.TimeConstants;
import com.tools.persistance.MongoReader;
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
	public void setProductAddToCart(String qty, String size) {
		if (!size.contentEquals("0")) {
			setDropDownValue(size);
		}
		setQuantity(qty);
		addToCart();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@StepGroup
	public BasicProductModel setBasicProductAddToCart(String qty, String size, String askingPrice, String finalPrice, String ip, String discountClass) {
		BasicProductModel result = new BasicProductModel();
		if (!size.contentEquals("0")) {
			waitABit(TimeConstants.TIME_CONSTANT);
			setDropDownValue(size);
		}
		setQuantity(qty);
		result = productDetailsPage().grabBasicProductData();
		result.setDiscountClass(discountClass);
		result.setProductsPrice(askingPrice);
		result.setFinalPrice(finalPrice);
		result.setPriceIP(ip);

		addToCart();
		waitABit(TimeConstants.TIME_CONSTANT);
		return result;
	}

	@StepGroup
	public BasicProductModel updateProduct(String qty, String size, String askingPrice, String finalPrice, String ip, String discountClass) {
		BasicProductModel result = new BasicProductModel();

		result = productDetailsPage().grabBasicProductData();
		result.setQuantity(qty);
		result.setDiscountClass(discountClass);
		result.setProductsPrice(askingPrice);
		result.setFinalPrice(finalPrice);
		result.setPriceIP(ip);

		return result;
	}

	@StepGroup
	public BorrowProductModel setBorrowedProductAddToCart(ProductDetailedModel model, String finalPrice) {
		BorrowProductModel result = new BorrowProductModel();

		result.setName(model.getName());
		result.setProdCode(model.getSku());
		result.setUnitPrice(finalPrice);
		result.setFinalPrice(finalPrice);
		result.setIpPoints("0");

		addToCart();
		waitABit(TimeConstants.TIME_CONSTANT);
		return result;
	}

	@StepGroup
	public BorrowProductModel setBorrowedProductAddToCart(RegularBasicProductModel model, String finalPrice) {
		BorrowProductModel result = new BorrowProductModel();

		result.setName(model.getName());
		result.setProdCode(model.getProdCode());
		result.setUnitPrice(finalPrice);
		result.setFinalPrice(finalPrice);
		result.setIpPoints("0");

		waitABit(TimeConstants.TIME_CONSTANT);
		return result;
	}

	@StepGroup
	public BorrowProductModel setKoboProductAddToCart(String name, String code, String price, String ip) {
		BorrowProductModel result = new BorrowProductModel();

		result.setName(name);
		result.setProdCode(code);
		result.setUnitPrice(price);
		result.setFinalPrice(price);
		result.setIpPoints(ip);

		waitABit(TimeConstants.TIME_CONSTANT);
		return result;
	}

	@StepGroup
	public PomProductModel setPomProductAddToCart(String name, String code, String price) {
		PomProductModel result = new PomProductModel();

		result.setName(name);
		result.setProdCode(code);
		result.setUnitPrice(price);
		result.setFinalPrice(price);

		waitABit(TimeConstants.TIME_CONSTANT);
		return result;
	}

	@StepGroup
	public StarterSetProductModel setStarterSetProductAddToCart() {
		StarterSetProductModel result = new StarterSetProductModel();

		result.setName("IRI_STARTER_SET");
		result.setUnitPrice("100.00");

		return result;
	}

	@StepGroup
	public BorrowProductModel setBorrowedDefaultProductAddToCart() {
		BorrowProductModel result = new BorrowProductModel();

		result.setName("Leihgebühr");
		result.setProdCode("Z999");
		if (MongoReader.getContext().contentEquals("de")) {
			result.setUnitPrice("6.00");
			result.setFinalPrice("6.00");
		} else if (MongoReader.getContext().contentEquals("es")) {
			result.setUnitPrice("30.00");
			result.setFinalPrice("30.00");
		}
		result.setIpPoints("0");

		return result;
	}

	@Step
	public RegularBasicProductModel setRegularBasicProductAddToCart(String qty, String size, String finalPrice) {
		RegularBasicProductModel result = new RegularBasicProductModel();
		if (!size.contentEquals("0")) {
			setDropDownValue(size);
		}
		setQuantity(qty);

		result = productDetailsPage().grabRegularBasicProductData();

		result.setFinalPrice(finalPrice);
		result.setBonusType(ContextConstants.REGULAR_PRICE);
		result.setBunosValue("0");

		addToCart();
		waitABit(TimeConstants.TIME_CONSTANT);
		return result;
	}

	@Step
	public RegularBasicProductModel setRegularBasicProductAddToWishlist(String qty, String size, String finalPrice) {
		RegularBasicProductModel result = new RegularBasicProductModel();
		if (!size.contentEquals("0")) {
			setDropDownValue(size);
		}
		setQuantity(qty);

		result = productDetailsPage().grabRegularBasicProductData();

		result.setFinalPrice(finalPrice);
		result.setBonusType(ContextConstants.REGULAR_PRICE);
		result.setBunosValue("0");

		addToWishlist();
		waitABit(TimeConstants.TIME_CONSTANT);
		return result;
	}

	@Step
	public HostBasicProductModel setHostBasicProductAddToCart(String qty, String size, String finalPrice, String ipPoints) {
		HostBasicProductModel result = new HostBasicProductModel();
		if (!size.contentEquals("0")) {
			System.out.println(!size.contentEquals("0"));
			setDropDownValue(size);
		}
		setQuantity(qty);

		result = productDetailsPage().grabHostBasicProductData();

		result.setFinalPrice(finalPrice);
		result.setIpPoints(ipPoints);
		result.setBonusType(ContextConstants.REGULAR_PRICE);
		result.setBunosValue("0");

		addToCart();
		waitABit(TimeConstants.TIME_CONSTANT);
		return result;
	}

	@Step
	public void setQuantityAndAddToCart(String qty, String size) {
		if (!size.contentEquals("0")) {
			System.out.println(!size.contentEquals("0"));
			setDropDownValue(size);
		}

		addToCart();
		waitABit(TimeConstants.TIME_CONSTANT);
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

	@Step
	public void addToWishlist() {
		productDetailsPage().addToWishlist();
	}

}
