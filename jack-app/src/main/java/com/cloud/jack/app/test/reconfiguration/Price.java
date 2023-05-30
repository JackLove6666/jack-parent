package com.cloud.jack.app.test.reconfiguration;

/**
 * 价格 多态
 */
public abstract class Price {

    abstract int getPriceCode();


    abstract double getCharge(int daysRented);


    int getFrequentRenterPoints(int daysRented){
        return 1;
    }


    static class ChildrensPrice extends Price {
        int getPriceCode() {
            return Movie.CHILDRENS;
        }

        double getCharge(int daysRented){
            double result = 0;
            result += 1.5;
            if (daysRented > 3)
                result += (daysRented - 3) * 1.5;
            return result;
        }
    }

    static class NewReleasePrice extends Price {
        int getPriceCode() {
            return Movie.NEW_RELEASE;
        }

       double getCharge(int daysRented){
            double result = 0;
            result += daysRented * 3;
            return result;
        }

        int getFrequentRenterPoints(int daysRented){
            return (daysRented > 1) ? 2: 1;
        }
    }

    static class RegularPrice extends Price {
        int getPriceCode() {
            return Movie.REGULAR;
        }

        double getCharge(int daysRented){
            double result = 0;
            result += 2;
            if (daysRented > 2)
                result += (daysRented - 2) * 1.5;
            return result;
        }
    }

}
