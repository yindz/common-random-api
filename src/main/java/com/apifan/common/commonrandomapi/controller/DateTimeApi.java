package com.apifan.common.commonrandomapi.controller;

import com.apifan.common.commonrandomapi.response.Result;
import com.apifan.common.random.source.DateTimeSource;
import com.google.common.base.Preconditions;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期时间API
 *
 * @author yin
 */
@Api(value = "/datetime", tags = {"日期时间API"})
@RestController
@RequestMapping("/datetime")
public class DateTimeApi {
    private static final Logger logger = LoggerFactory.getLogger(DateTimeApi.class);

    private static final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 某年内随机日期
     *
     * @param year
     * @param pattern
     * @return
     */
    @GetMapping(value = "/randomDateInYear")
    @ApiOperation(value = "某年内随机日期", httpMethod = "GET", tags = {"日期时间API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "4位数年份", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pattern", value = "返回值格式(默认yyyyMMdd)", dataType = "String")
    })
    public Result<String> randomDateInYear(@RequestParam("year") int year,
                                           @RequestParam(name = "pattern", required = false, defaultValue = "yyyyMMdd") String pattern) {
        Preconditions.checkArgument(year >= 1900 && year <= 9999, "year无效");
        return Result.success(DateTimeSource.getInstance().randomDate(year, pattern));
    }

    /**
     * 随机日期
     *
     * @param beginDate
     * @param endDate
     * @param pattern
     * @return
     */
    @GetMapping(value = "/randomDate")
    @ApiOperation(value = "随机日期", httpMethod = "GET", tags = {"日期时间API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginDate", value = "开始日期(格式:yyyyMMdd)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endDate", value = "结束日期(格式:yyyyMMdd)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pattern", value = "返回值格式(默认yyyyMMdd)", dataType = "String")
    })
    public Result<String> randomDate(@RequestParam("beginDate") String beginDate,
                                     @RequestParam("endDate") String endDate,
                                     @RequestParam(name = "pattern", required = false, defaultValue = "yyyyMMdd") String pattern) {
        return Result.success(DateTimeSource.getInstance().randomDate(LocalDate.parse(beginDate, yyyyMMdd), LocalDate.parse(endDate, yyyyMMdd), pattern));
    }

    /**
     * 随机未来日期
     *
     * @param pattern
     * @return
     */
    @GetMapping(value = "/randomFutureDate")
    @ApiOperation(value = "随机未来日期", httpMethod = "GET", tags = {"日期时间API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pattern", value = "返回值格式(默认yyyyMMdd)", dataType = "String")
    })
    public Result<String> randomFutureDate(@RequestParam(name = "pattern", required = false, defaultValue = "yyyyMMdd") String pattern) {
        return Result.success(DateTimeSource.getInstance().randomFutureDate(pattern));
    }

    /**
     * 随机过去日期
     *
     * @param pattern
     * @return
     */
    @GetMapping(value = "/randomPastDate")
    @ApiOperation(value = "随机过去日期", httpMethod = "GET", tags = {"日期时间API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pattern", value = "返回值格式(默认yyyyMMdd)", dataType = "String")
    })
    public Result<String> randomPastDate(@RequestParam(name = "pattern", required = false, defaultValue = "yyyyMMdd") String pattern) {
        return Result.success(DateTimeSource.getInstance().randomPastDate(pattern));
    }

    /**
     * 随机时间
     *
     * @param year
     * @param month
     * @param dayOfMonth
     * @param pattern
     * @return
     */
    @GetMapping(value = "/randomTime")
    @ApiOperation(value = "随机时间", httpMethod = "GET", tags = {"日期时间API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "4位数年份", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "month", value = "月", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "dayOfMonth", value = "日", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pattern", value = "返回值格式(默认yyyyMMddHHmmss)", dataType = "String")
    })
    public Result<String> randomTime(@RequestParam("year") int year,
                                     @RequestParam("month") int month,
                                     @RequestParam("dayOfMonth") int dayOfMonth,
                                     @RequestParam(name = "pattern", required = false, defaultValue = "默认yyyyMMddHHmmss") String pattern) {
        LocalDateTime time = DateTimeSource.getInstance().randomTime(year, month, dayOfMonth);
        return Result.success(DateTimeFormatter.ofPattern(pattern).format(time));
    }

    /**
     * 随机过去时间
     *
     * @param maxSeconds
     * @param pattern
     * @return
     */
    @GetMapping(value = "/randomPastTime")
    @ApiOperation(value = "随机过去时间", httpMethod = "GET", tags = {"日期时间API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "maxSeconds", value = "最大间隔秒数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pattern", value = "返回值格式(默认yyyyMMddHHmmss)", dataType = "String")
    })
    public Result<String> randomPastTime(@RequestParam("maxSeconds") int maxSeconds,
                                         @RequestParam(name = "pattern", required = false, defaultValue = "默认yyyyMMddHHmmss") String pattern) {
        LocalDateTime time = DateTimeSource.getInstance().randomPastTime(LocalDateTime.now(), maxSeconds);
        return Result.success(DateTimeFormatter.ofPattern(pattern).format(time));
    }

    /**
     * 随机未来时间
     *
     * @param maxSeconds
     * @param pattern
     * @return
     */
    @GetMapping(value = "/randomFutureTime")
    @ApiOperation(value = "随机未来时间", httpMethod = "GET", tags = {"日期时间API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "maxSeconds", value = "最大间隔秒数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pattern", value = "返回值格式(默认yyyyMMddHHmmss)", dataType = "String")
    })
    public Result<String> randomFutureTime(@RequestParam("maxSeconds") int maxSeconds,
                                           @RequestParam(name = "pattern", required = false, defaultValue = "默认yyyyMMddHHmmss") String pattern) {
        LocalDateTime time = DateTimeSource.getInstance().randomFutureTime(LocalDateTime.now(), maxSeconds);
        return Result.success(DateTimeFormatter.ofPattern(pattern).format(time));
    }

    /**
     * 随机时间戳
     *
     * @param date
     * @return
     */
    @GetMapping(value = "/randomTimestamp")
    @ApiOperation(value = "随机时间戳", httpMethod = "GET", tags = {"日期时间API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期(格式:yyyyMMdd)", required = true, dataType = "String")
    })
    public Result<Long> randomTimestamp(@RequestParam("date") String date) {
        return Result.success(DateTimeSource.getInstance().randomTimestamp(LocalDate.parse(date, yyyyMMdd)));
    }

    /**
     * 随机过去时间戳
     *
     * @param maxSeconds
     * @return
     */
    @GetMapping(value = "/randomPastTimestamp")
    @ApiOperation(value = "随机过去时间戳", httpMethod = "GET", tags = {"日期时间API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "maxSeconds", value = "最大间隔秒数", required = true, dataType = "Integer")
    })
    public Result<Long> randomPastTimestamp(@RequestParam("maxSeconds") int maxSeconds) {
        return Result.success(DateTimeSource.getInstance().randomPastTimestamp(LocalDateTime.now(), maxSeconds));
    }

    /**
     * 随机未来时间戳
     *
     * @param maxSeconds
     * @return
     */
    @GetMapping(value = "/randomFutureTimestamp")
    @ApiOperation(value = "随机未来时间戳", httpMethod = "GET", tags = {"日期时间API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "maxSeconds", value = "最大间隔秒数", required = true, dataType = "Integer")
    })
    public Result<Long> randomFutureTimestamp(@RequestParam("maxSeconds") int maxSeconds) {
        return Result.success(DateTimeSource.getInstance().randomFutureTimestamp(LocalDateTime.now(), maxSeconds));
    }

    /**
     * 随机时区名称
     *
     * @return
     */
    @GetMapping(value = "/randomTimezoneName")
    @ApiOperation(value = "随机时区名称", httpMethod = "GET", tags = {"日期时间API"}, produces = "application/json")
    public Result<String> randomTimezoneName() {
        return Result.success(DateTimeSource.getInstance().randomTimezoneName());
    }

}
