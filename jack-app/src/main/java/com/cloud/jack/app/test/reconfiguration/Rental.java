package com.cloud.jack.app.test.reconfiguration;

/**
 * 租赁 代表某个顾客租了一部影片
 */
public class Rental {

    private Movie _movie;//影片

    private int _daysRented;//租期

    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public Movie get_movie() {
        return _movie;
    }

    public void set_movie(Movie _movie) {
        this._movie = _movie;
    }

    public int get_daysRented() {
        return _daysRented;
    }

    public void set_daysRented(int _daysRented) {
        this._daysRented = _daysRented;
    }

    /**
     * 计算租赁费用
     * @return
     */
    public double getCharge(){
       return _movie.getCharge(_daysRented);
    }

    /**
     * 计算积分
     * @return
     */
    public int getFrequentRenterPoints(){
        return _movie.getFrequentRenterPoints(_daysRented);
    }
}
