package com.foodordering.model;

public class Order {
    private Long id;
    private Long customerId;
    private Long foodId;
    private String foodName;
    private int quantity;
    private Double unitPrice;
    private Double totalAmount;
    private String status;

    public Order() {
    }

    public Order(Long id, Long customerId, Long foodId,
                 String foodName, int quantity,
                 Double unitPrice, Double totalAmount,
                 String status) {
        this.id = id;
        this.customerId = customerId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getFoodId() { return foodId; }
    public void setFoodId(Long foodId) { this.foodId = foodId; }

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   public Double getUnitPrice() {
    return unitPrice;
}

public void setUnitPrice(Double unitPrice) {
    this.unitPrice = unitPrice;
}

public Double getTotalAmount() {
    return totalAmount;
}

public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
}

    public String getStatus() { return status; }
    public void setStatus(String status) {
        this.status = status;
    }
}