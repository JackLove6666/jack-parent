package com.cloud.jack.app.test.reconfiguration;

import java.util.Vector;

/**
 * 1.thisAmount 是个临时变量，所以我可以直接把新函数的返回值赋予它。
 * 2.我愚蠢地将amountFor() 的返回值型别声明为int，而不是double
 * 3.观察amountFor() 时，我发现这个函数使用了来自Rental class 的信息，却没有使 用来自Customer class 的信息。
 * 4.下一件引我注意的事是：thisAmount 如今变成多余了。它接受each.charge 的执行结果，然后就不再有任何改变。所以我可以运用 Replace Temp with Query 除去。
 */
public class Customer {

    private String _name;//姓名

    private Vector<Rental> _rentals = new Vector();//租赁记录

    public Customer(String name) {
        _name = name;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void addRentals(Rental arg) {
        _rentals.addElement(arg);
    }


    public String statement1(){
        String result = "Rental Record for " + get_name() + "\n";

        for (Rental each : _rentals){
            result += "\t" + each.get_movie().get_title() + "\t" + String.valueOf(each.getCharge()) + "\n";
        }
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
        return result;
    }

    /**
     * 总金额计算
     * @return
     */
    private double getTotalCharge(){
        double result = 0;
        for (Rental each : _rentals){
            result += each.getCharge();
        }
        return result;
    }

    /**
     * 总积分计算
     */
    private int getTotalFrequentRenterPoints(){
        int result = 0;
        for (Rental each : _rentals){
            result += each.getFrequentRenterPoints();
        }
        return result;
    }


    /**
     * 客人消费金额
     * @param each
     * @return
     */
    private static double getThisAmount(Rental each) {
        double thisAmount = 0;
        switch (each.get_movie().get_priceCode()){//取得影片出租价格
            case Movie.REGULAR://普通片
                thisAmount += 2;
                if (each.get_daysRented() > 2){
                    thisAmount += (each.get_daysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE://新片
                thisAmount += each.get_daysRented() * 3;
                break;
            case Movie.CHILDRENS://儿童片
                thisAmount += 1.5;
                if (each.get_daysRented() > 3){
                    thisAmount += (each.get_daysRented() - 3) * 1.5;
                }
                break;
        }
        return thisAmount;
    }

    /**
     * 生成详单
     * @return
     */
    public String statement(){
        double totalAmount = 0;//总金额
        int frequentRenterPoints = 0;//积分
        String result = "Rental Record for " + get_name() + "\n";

        for (Rental each : _rentals){
            double thisAmount = 0;
            switch (each.get_movie().get_priceCode()){//取得影片出租价格
                case Movie.REGULAR://普通片
                    thisAmount += 2;
                    if (each.get_daysRented() > 2){
                        thisAmount += (each.get_daysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE://新片
                    thisAmount += each.get_daysRented() * 3;
                    break;
                case Movie.CHILDRENS://儿童片
                    thisAmount += 1.5;
                    if (each.get_daysRented() > 3){
                        thisAmount += (each.get_daysRented() - 3) * 1.5;
                    }
                    break;
            }
            frequentRenterPoints ++;
            if (each.get_movie().get_priceCode() == Movie.NEW_RELEASE && each.get_daysRented() > 1){
                frequentRenterPoints ++;
            }
            result += "\t" + each.get_movie().get_title() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

    /**
     * 生成详单 html
     * @return
     */
    public String htmlStatement(){
        String result = "<H1>Rentals for <EM>" + get_name() + "</EM></ H1><P>\n";

        for (Rental each : _rentals){
            result += each.get_movie().get_title()+ ": " +
                    String.valueOf(each.getCharge()) + "<BR>\n";
        }
        result +=  "<P>You owe <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
        result += "On this rental you earned <EM>" +
                String.valueOf(getTotalFrequentRenterPoints()) +
                "</EM> frequent renter points<P>";
        return result;
    }
}
