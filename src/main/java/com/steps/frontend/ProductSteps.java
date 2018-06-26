
package com.steps.frontend;

import org.junit.Assert;

import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.PomProductModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.StarterSetProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

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
	public BasicProductModel setBasicProductAddToCart(String qty, String size, String askingPrice, String finalPrice,
			String ip, String discountClass) {
		BasicProductModel result = new BasicProductModel();
		
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
	public BasicProductModel setChildProductAddToCart(ProductDetailedModel model, String qty, String size, String askingPrice, String finalPrice,
			String ip, String discountClass) {
		BasicProductModel result = new BasicProductModel();
	
		setProductColor(model.getColor());
		setProductSize(model.getProductSize());
		setQuantity(qty);
	
		
		result = productDetailsPage().grabBasicProductData();
		result.setDiscountClass(discountClass);
		result.setProductsPrice(askingPrice);
		result.setFinalPrice(finalPrice);
		result.setPriceIP(ip);
		result.setColour(model.getColor());
		result.setSize(model.getProductSize());

		
		addToCart();
		waitABit(TimeConstants.TIME_CONSTANT);
		return result;
	}
	
	@StepGroup
	public BasicProductModel setMarketingProductAddToCart(ProductDetailedModel model, String qty, String size, String askingPrice, String finalPrice,
			String ip, String discountClass) {
		BasicProductModel result = new BasicProductModel();
	
	//	setProductColor("");
	//	setProductSize("");
		setQuantity(qty);
	
		
		// work aroung until the sku is displauyed on fronted for marketing products
		result = productDetailsPage().grabMarketingProductData(model.getSku());
		System.out.println("grabbed ok");
		result.setDiscountClass(discountClass);
		System.out.println("discountClass ok");
		result.setProductsPrice(askingPrice);
		System.out.println("askingPrice ok");
		result.setFinalPrice(finalPrice);
		System.out.println("finalPrice ok");
		
		
		result.setPriceIP(ip);

		
		addToCart();
		waitABit(TimeConstants.TIME_CONSTANT);
		return result;
	}
	
	public BasicProductModel setBasicProductAddToCartBeta(String qty, String size, String askingPrice, String finalPrice,
			String ip, String discountClass) {
		BasicProductModel result = new BasicProductModel();
		
		//setQuantity(qty);
		//result = productDetailsPage().grabBasicProductData();
		result.setDiscountClass(discountClass);
		result.setProductsPrice(askingPrice);
		result.setFinalPrice(finalPrice);
		result.setPriceIP(ip);

	//	addToCart();
		// waitABit(TimeConstants.TIME_CONSTANT);
		return result;
	}
	

	@StepGroup
	public BasicProductModel setBasicProductAddToWhislist(String qty, String size, String askingPrice,
			String finalPrice, String ip, String discountClass) {
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

		addToWishlist();
		// waitABit(TimeConstants.TIME_CONSTANT);
		return result;
	}

	@StepGroup
	public BasicProductModel updateProduct(ProductDetailedModel model, String qty, String size, String askingPrice,
			String finalPrice, String ip, String discountClass) {
		BasicProductModel result = new BasicProductModel();

		result.setName(model.getName());
		result.setProdCode(model.getSku());
		result.setUnitPrice(model.getPrice());
		result.setQuantity(qty);
		result.setDiscountClass(discountClass);
		result.setProductsPrice(askingPrice);
		result.setFinalPrice(finalPrice);
		result.setPriceIP(ip);

		return result;
	}

	@StepGroup
	public RegularBasicProductModel updateRegularBasicProductAddToCart(ProductDetailedModel model, String qty,
			String size, String finalPrice, String ip) {
		RegularBasicProductModel result = new RegularBasicProductModel();

		result.setName(model.getName());
		result.setProdCode(model.getSku());
		result.setUnitPrice(model.getPrice());
		result.setQuantity(qty);
		result.setFinalPrice(finalPrice);
		result.setBonusType(ContextConstants.REGULAR_PRICE);
		result.setBunosValue("0");
		result.setIpPoints(ip);

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
	public StarterSetProductModel setStarterSetProductAddToCart(String price) {
		StarterSetProductModel result = new StarterSetProductModel();

		result.setName("IRI_STARTER_SET");
		result.setUnitPrice(price);

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
		} else {
			result.setUnitPrice("30.00");
			result.setFinalPrice("30.00");
		}
		result.setIpPoints("0");

		return result;
	}
	
	@StepGroup
	public BorrowProductModel setXXXBorrowedProductAddToCart() {
		BorrowProductModel result = new BorrowProductModel();

		result.setName("Borrow Product XXX");
		result.setProdCode("xxxx");
		result.setQty("999");
		result.setUnitPrice("0.00");
		result.setFinalPrice("0.00");
	
		result.setIpPoints("0");

		return result;
	}

	@StepGroup
	public BorrowProductModel setYYYBorrowedProductAddToCart() {
		BorrowProductModel result = new BorrowProductModel();

		result.setName("Borrow Product YYY");
		result.setProdCode("yyy");
		result.setQty("999");
		result.setUnitPrice("0.00");
		result.setFinalPrice("0.00");
	
		result.setIpPoints("0");

		return result;
	}
	
	//@Step
	public RegularBasicProductModel setRegularBasicProductAddToCart(ProductDetailedModel model, String qty, String size,
			String finalPrice, String ipPoints) {
		RegularBasicProductModel result = new RegularBasicProductModel();
		setQuantity(qty);
		
		System.out.println("finalPrice: "+finalPrice);
		result.setName(model.getName());
		result.setProdCode(model.getSku());
		result.setUnitPrice(model.getPrice());
		result.setQuantity(qty);
		result.setFinalPrice(finalPrice);
		result.setBonusType(ContextConstants.REGULAR_PRICE);
		result.setBunosValue("0");
		result.setIpPoints(ipPoints);

		setProductColor(model.getColor());
		setProductSize(model.getProductSize());

		addToCart();
		waitABit(TimeConstants.TIME_CONSTANT);
		
		return result;
	}

	@Step
	public RegularBasicProductModel setRegularBasicProductAddToWishlist(ProductDetailedModel model, String qty,
			String size, String finalPrice) {
		RegularBasicProductModel result = new RegularBasicProductModel();
		if (!size.contentEquals("0")) {
			setDropDownValue(size);
		}

		result.setName(model.getName());
		result.setProdCode(model.getSku());
		result.setUnitPrice(model.getPrice());
		result.setQuantity(qty);
		result.setFinalPrice(finalPrice);
		result.setBonusType(ContextConstants.REGULAR_PRICE);
		result.setBunosValue("0");

		addToWishlist();
		waitABit(TimeConstants.TIME_CONSTANT);
		return result;
	}

	@Step
	public HostBasicProductModel setHostBasicProductAddToCart(ProductDetailedModel model, String qty, String size,
			String finalPrice, String ipPoints) {
		HostBasicProductModel result = new HostBasicProductModel();
	/*	if (!size.contentEquals("0")) {
			setDropDownValue(size);
		}*/
		setProductColor(model.getColor());
		setProductSize(model.getProductSize());
		setQuantity(qty);

		result.setName(model.getName());
		result.setProdCode(model.getSku());
		result.setUnitPrice(model.getPrice());
		result.setQuantity(qty);
		result.setFinalPrice(finalPrice);
		result.setIpPoints(ipPoints);
		result.setBonusType(ContextConstants.REGULAR_PRICE);
		result.setBunosValue("0");

		addToCart();
		waitABit(TimeConstants.TIME_CONSTANT);
		return result;
	}
	
	
	@Step
	public HostBasicProductModel setHostChildBasicProductAddToCart(ProductDetailedModel model, String qty, String size,
			String finalPrice, String ipPoints) {
		HostBasicProductModel result = new HostBasicProductModel();
		if (!size.contentEquals("0")) {
			setDropDownValue(size);
		}
		setProductColor(model.getColor());
		setProductSize(model.getProductSize());
		
		setQuantity(qty);

		result.setName(model.getName());
		result.setProdCode(model.getSku());
		result.setUnitPrice(model.getPrice());
		result.setQuantity(qty);
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
			setDropDownValue(size);
		}
		setQuantity(qty);
		addToCart();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void addToWishlist(String qty, String size) {
		if (!size.contentEquals("0")) {
			setDropDownValue(size);
		}
		addToWishlist();
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
	public void setProductColor(String productColor) {
		productDetailsPage().setProductColor(productColor);
	}

	@Step
	public void setProductSize(String productSize) {
		productDetailsPage().setProductSize(productSize);
	}


	@Step
	public void addToWishlist() {
		productDetailsPage().addToWishlist();
	}

	@Step
	public void verifyAddToCartButton(boolean shouldBeVisible) {
		if (shouldBeVisible) {
			Assert.assertTrue("The button should be present and it's not !!!",
					productDetailsPage().getProductDetailsText().contains(ContextConstants.ADD_TO_CART));
		} else {
			Assert.assertTrue("The button is present and it shouldn't !!!",
					!productDetailsPage().getProductDetailsText().contains(ContextConstants.ADD_TO_CART));
		}
	}

	@Step
	public void verifyThatAvailabilityDateIsCorrect(String avDate) {
		Assert.assertTrue("The availability date is not correct",
				productDetailsPage().getStockStatus().contains(avDate));
	}

	@Step
	public void verifyThatProductStatusIsCorrect(String status) {
		Assert.assertTrue("The product status is not correct", productDetailsPage().getStockStatus().contains(status));
	}

}
