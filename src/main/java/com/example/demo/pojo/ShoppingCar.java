package com.example.demo.pojo;

import java.io.Serializable;

public class ShoppingCar implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer carId;

    private Integer goodsNum;

    private Integer stuId;

    private String goodsName;

    private String goodsDetails;

    private Integer goodsPrivce;

    private String goodsImg;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsDetails() {
        return goodsDetails;
    }

    public void setGoodsDetails(String goodsDetails) {
        this.goodsDetails = goodsDetails == null ? null : goodsDetails.trim();
    }

    public Integer getGoodsPrivce() {
        return goodsPrivce;
    }

    public void setGoodsPrivce(Integer goodsPrivce) {
        this.goodsPrivce = goodsPrivce;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg == null ? null : goodsImg.trim();
    }

	@Override
	public String toString() {
		return "ShoppingCar [carId=" + carId + ", goodsNum=" + goodsNum + ", stuId=" + stuId + ", goodsName="
				+ goodsName + ", goodsDetails=" + goodsDetails + ", goodsPrivce=" + goodsPrivce + ", goodsImg="
				+ goodsImg + "]";
	}
    
}