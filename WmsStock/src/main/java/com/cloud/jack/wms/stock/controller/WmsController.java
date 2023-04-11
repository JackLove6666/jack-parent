package com.cloud.jack.wms.stock.controller;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloud.jack.wms.stock.entity.WmsStock;
import com.cloud.jack.wms.stock.mapper.WmsStockMapper;
import com.cloud.jack.wms.stock.service.WmsStockService;
import com.cloud.jack.wms.stock.util.RedissonLocker;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@RestController
@Slf4j
public class WmsController {

    @Autowired
    private WmsStockService wmsStockService;

    Lock reentrantLock = new ReentrantLock();

    @Autowired
    private WmsStockMapper wmsStockMapper;

    @Autowired
    private RedissonLocker redissonLocker;



    /**
     * jvm  多例模式、事务未提交、多进程
     * 1.reentrantLock
     * 2.synchronized
     *
     * 一句SQL扣减
     * 锁范围：行锁、表锁
     *
     * 悲观锁
     * select ... for update
     * 乐观锁
     * update ... where product_code = 'jkg01' and version = #{version}
     *
     *
     */
    @GetMapping("/reduceStock")
    public synchronized void reduceStock(){

        reentrantLock.lock();

        try {
            WmsStock wmsStock = wmsStockService.getOne(new QueryWrapper<WmsStock>().eq("product_code", "jkg01"));

            if(wmsStock != null && wmsStock.getStockQuantity() > 0){
                wmsStock.setStockQuantity(wmsStock.getStockQuantity() - 1);
                boolean update = wmsStockService.updateById(wmsStock);
                if(!update){
                    throw new RuntimeException("运行报错！");
                }else {
                    log.info("成功操作！！");
                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }

    }

    /**
     * 一条SQL  并发吞吐量低 1.锁范围：行锁、表锁
     * 2.无法处理相同商品不同地区的产品
     *
     */
    @GetMapping("/reduceStock1")
    public void reduceStock1(){
        int updateStock = wmsStockMapper.updateStock("jkg01");
        if(updateStock > 0){
            log.info("成功操作！！");
        }else {
            throw new RuntimeException("失败操作！");
        }
    }

    /**
     * 乐观锁、版本字段   适合读多
     */
    @GetMapping("/reduceStock2")
    public void reduceStock2(){
        List<WmsStock> wmsStocks = wmsStockMapper.selectList(new QueryWrapper<WmsStock>().eq("product_code", "jkg01"));

        if(CollUtil.isNotEmpty(wmsStocks)){
            WmsStock wmsStock = wmsStocks.get(0);
            wmsStock.setStockQuantity(wmsStock.getStockQuantity() -1);
            wmsStock.setVersion(wmsStock.getVersion()+1);
            int update = wmsStockMapper.update(wmsStock,new UpdateWrapper<WmsStock>().eq("version",wmsStock.getVersion())
                    .eq("id",wmsStock.getId()));
            if(update < 0){//失败重新调用
                reduceStock2();
            }
        }

    }

    @GetMapping("/reduceStock3")
    public void reduceStock3(){
        redissonLocker.tryDoBusiness("jkg",1000,10000,()->{
            List<WmsStock> wmsStocks = wmsStockMapper.selectList(new QueryWrapper<WmsStock>().eq("product_code", "jkg01"));
            if(CollUtil.isNotEmpty(wmsStocks)){
                WmsStock wmsStock = wmsStocks.get(0);
                wmsStock.setStockQuantity(wmsStock.getStockQuantity()-1);
                int update = wmsStockMapper.update(wmsStock, new UpdateWrapper<WmsStock>().eq("id", wmsStock.getId()));
                if(update < 0){
                    throw new RuntimeException("操作失败!");
                }
            }
        });
    }
}
