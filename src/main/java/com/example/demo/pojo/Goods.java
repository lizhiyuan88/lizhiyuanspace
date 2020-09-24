package com.example.demo.pojo;

import java.io.Serializable;

public class Goods implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer goodsId;

    private String goodsNo;

    private String goodsName;

    private String goodsDetails;

    private Integer goodsPrivce;

    private String goodsImg;

    private Integer goodsStatus;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo == null ? null : goodsNo.trim();
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

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsNo=" + goodsNo + ", goodsName=" + goodsName + ", goodsDetails="
				+ goodsDetails + ", goodsPrivce=" + goodsPrivce + ", goodsImg=" + goodsImg + ", goodsStatus="
				+ goodsStatus + "]";
	}
    
}