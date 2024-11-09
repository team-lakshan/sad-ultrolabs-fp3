
package util;

import java.util.Date;

public class GRNItem {
    
    private String productId;
    private String brandName;
    private String productName;
    private String category;
    private String color;
    private String size;
    private double qty;
    private double buyingPrice;
    private double sellingPrice;
    private Date mfd;
    private Date exp;

    
    public String getProductId() {
        return productId;
    }

    
    public void setProductId(String productId) {
        this.productId = productId;
    }

    
    public String getBrandName() {
        return brandName;
    }

    
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    
    public String getProductName() {
        return productName;
    }

    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getCategory() {
        return category;
    }

    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getColor() {
        return color;
    }

    
    public void setColor(String color) {
        this.color = color;
    }
    
    public String getSize() {
        return size;
    }

    
    public void setSize(String size) {
        this.size = size;
    }

  
    public double getQty() {
        return qty;
    }

    
    public void setQty(double qty) {
        this.qty = qty;
    }

    
    public double getBuyingPrice() {
        return buyingPrice;
    }

    
    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    
    public double getSellingPrice() {
        return sellingPrice;
    }

    
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    
    public Date getMfd() {
        return mfd;
    }

    
    public void setMfd(Date mfd) {
        this.mfd = mfd;
    }

    
    public Date getExp() {
        return exp;
    }

    
    public void setExp(Date exp) {
        this.exp = exp;
    }
    
}
