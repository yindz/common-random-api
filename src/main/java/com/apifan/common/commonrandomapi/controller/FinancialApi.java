package com.apifan.common.commonrandomapi.controller;

import com.apifan.common.commonrandomapi.response.Result;
import com.apifan.common.random.entity.CurrencyInfo;
import com.apifan.common.random.entity.KChartData;
import com.apifan.common.random.source.FinancialSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 金融API
 *
 * @author yin
 */
@Api(value = "/financial", tags = {"金融API"})
@RestController
@RequestMapping("/financial")
public class FinancialApi {
    private static final Logger logger = LoggerFactory.getLogger(FinancialApi.class);

    /**
     * 随机沪深股票
     *
     * @return
     */
    @GetMapping(value = "/randomStock")
    @ApiOperation(value = "随机沪深股票", httpMethod = "GET", tags = {"金融API"}, produces = "application/json")
    public Result<String[]> randomStock() {
        return Result.success(FinancialSource.getInstance().randomStock());
    }

    /**
     * 随机港股
     *
     * @return
     */
    @GetMapping(value = "/randomHKStock")
    @ApiOperation(value = "随机港股", httpMethod = "GET", tags = {"金融API"}, produces = "application/json")
    public Result<String[]> randomHKStock() {
        return Result.success(FinancialSource.getInstance().randomHKStock());
    }

    /**
     * 随机新三板
     *
     * @return
     */
    @GetMapping(value = "/randomXsbStock")
    @ApiOperation(value = "随机新三板", httpMethod = "GET", tags = {"金融API"}, produces = "application/json")
    public Result<String[]> randomXsbStock() {
        return Result.success(FinancialSource.getInstance().randomXsbStock());
    }

    /**
     * 随机开放式基金
     *
     * @return
     */
    @GetMapping(value = "/randomFund")
    @ApiOperation(value = "随机开放式基金", httpMethod = "GET", tags = {"金融API"}, produces = "application/json")
    public Result<String[]> randomFund() {
        return Result.success(FinancialSource.getInstance().randomFund());
    }

    /**
     * 随机货币
     *
     * @return
     */
    @GetMapping(value = "/randomCurrencyInfo")
    @ApiOperation(value = "随机货币", httpMethod = "GET", tags = {"金融API"}, produces = "application/json")
    public Result<CurrencyInfo> randomCurrencyInfo() {
        return Result.success(FinancialSource.getInstance().randomCurrencyInfo());
    }

    /**
     * 随机日K线数据
     *
     * @return
     */
    @GetMapping(value = "/randomDailyKChartData")
    @ApiOperation(value = "随机日K线数据", httpMethod = "GET", tags = {"金融API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginDate", value = "开始日期(yyyyMMdd)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endDate", value = "结束日期(yyyyMMdd)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "beginPrice", value = "起始价格", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "limitUp", value = "单日最大涨幅", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "limitDown", value = "单日最大跌幅", required = true, dataType = "Double")
    })
    public Result<List<KChartData>> randomDailyKChartData(@RequestParam(name = "beginDate", required = false, defaultValue = "") String beginDate,
                                                          @RequestParam(name = "endDate", required = false, defaultValue = "") String endDate,
                                                          @RequestParam(name = "beginPrice", required = false, defaultValue = "1") double beginPrice,
                                                          @RequestParam(name = "limitUp", required = false, defaultValue = "0.01") double limitUp,
                                                          @RequestParam(name = "limitDown", required = false, defaultValue = "-0.01") double limitDown) {
        return Result.success(FinancialSource.getInstance().randomDailyKChartData(beginPrice, limitUp, limitDown, beginDate, endDate));
    }
}
