package com.ecut.frozenpearassistant.orm.entity;

public class ProductEntity {
   private String  productId ;
   private String  userId ;
   private String  productType ;
   private String  title ;
   private String  sellPoint ;
   private String  image ;
   private String  status ;
   private String  message ;
   private String  price ;
   private String  postage ;

   private String phone;
   private String userName;

   private  int totalPage;
   private  int currentPage;

   public int getTotalPage() {
      return totalPage;
   }

   public void setTotalPage(int totalPage) {
      this.totalPage = totalPage;
   }

   public int getCurrentPage() {
      return currentPage;
   }

   public void setCurrentPage(int currentPage) {
      this.currentPage = currentPage;
   }

   public String getProductId() {
      return productId;
   }

   public void setProductId(String productId) {
      this.productId = productId;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getProductType() {
      return productType;
   }

   public void setProductType(String productType) {
      this.productType = productType;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getSellPoint() {
      return sellPoint;
   }

   public void setSellPoint(String sellPoint) {
      this.sellPoint = sellPoint;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String getPrice() {
      return price;
   }

   public void setPrice(String price) {
      this.price = price;
   }

   public String getPostage() {
      return postage;
   }

   public void setPostage(String postage) {
      this.postage = postage;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }
}
