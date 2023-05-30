package com.cloud.jack.app.test.reconfiguration;

/**
 * 影片 修改「影片分类结构」，或是改变「费用计算规则」、改变常客积点计算规则 运用多态取代与价格相关的条件逻辑
 */
public class Movie {

    public static final int CHILDRENS = 2;

    public static final int REGULAR = 0;

    public static final int NEW_RELEASE = 1;

    private String _title; //名称

    private int _priceCode;//价格码

    private Price _price;

    public Movie(String title, int priceCode) {
        _title = title;
       set_priceCode(priceCode);
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public int get_priceCode() {
        return _priceCode;
    }

    public void set_priceCode(int _priceCode) {
        switch (_priceCode){
            case REGULAR:
                _price =  new Price.RegularPrice();
                break;
            case CHILDRENS:
                _price = new Price.ChildrensPrice();
                break;
            case NEW_RELEASE:
                _price = new Price.NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    double getCharge(int daysRented){
        return _price.getCharge(daysRented);
    }

    int getFrequentRenterPoints(int daysRented){
        return _price.getFrequentRenterPoints(daysRented);
    }
}
