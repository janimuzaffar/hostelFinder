package com.example.hostel.models;

public class PropertyOwner {

    public static final String PRICE = "price";
    public static final String PROPERTY_NAME = "propertyName";
    public static final String SOCITY_NAME = "socityName";
    public static final String BEDROOMS = "bedrooms";
    public static final String VACANT = "vacant";
    public static final String OWNER_NAME = "ownerName";
    public static final String PHONE = "phone";
    public static final String ELIGIBILITY = "eligibility";
    public static final String FURNISHING = "furnishing";
    public static final String PROPERTY_LOCATION = "propertyLocation";
    public static final String PROPERTY_IMAGE = "propertyImage";
    public static final String PROPERTY_ID = "propertyId";

    private String propertyId;
    private String userId;
    private int price;
    private String propertyName;
    private String ownership;
    private String socityName;
    private String bedrooms;
    private String vacant;
    private String ownerName;
    private String phone;
    private String eligibility;
    private String furnishing;
    private String propertyLocation;
    private String propertyImage;


    public PropertyOwner() {}

   public PropertyOwner(
           String propName,
           int price,
           String ownership,
           String socityName,
           String bedrooms,
           String vacant,
           String ownerName,
           String phone,
           String eligibility,
           String furnishing,
           String propertyLocation,
           String propertyImage,
           String userId) {
  //  public PropertyOwner(String propName, int price) {
        this.propertyName = propName;
        this.ownership=ownership;
        this.price = price;
        this.socityName=socityName;
        this.bedrooms=bedrooms;
        this.vacant=vacant;
        this.ownerName=ownerName;
        this.phone=phone;
        this.eligibility = eligibility;
        this.furnishing=furnishing;
        this.propertyLocation=propertyLocation;
        this.propertyImage=propertyImage;
        this.userId = userId;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getSocityName() {
        return socityName;
    }

    public void setSocityName(String socityName) {
        this.socityName = socityName;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getVacant() {
        return vacant;
    }

    public void setVacant(String vacant) {
        this.vacant = vacant;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getFurnishing() {
        return furnishing;
    }

    public void setFurnishing(String furnishing) {
        this.furnishing = furnishing;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String _getPropertyId() {
        return propertyId;
    }

    public void _setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyLocation() {
        return propertyLocation;
    }

    public void setPropertyLocation(String propertyLocation) {
        this.propertyLocation = propertyLocation;
    }

    public String getPropertyImage() {
        return propertyImage;
    }

    public void setPropertyImage(String propertyImage) {
        this.propertyImage = propertyImage;
    }
}
