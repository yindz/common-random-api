package com.apifan.common.commonrandomapi.controller;

import com.apifan.common.commonrandomapi.response.Result;
import com.apifan.common.random.entity.CountryOrRegionCode;
import com.apifan.common.random.source.AreaSource;
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

/**
 * 地区API
 *
 * @author yin
 */
@Api(value = "/area", tags = {"地区API"})
@RestController
@RequestMapping("/area")
public class AreaApi {
    private static final Logger logger = LoggerFactory.getLogger(AreaApi.class);

    /**
     * 随机省份
     *
     * @return
     */
    @GetMapping(value = "/randomProvince")
    @ApiOperation(value = "随机省份", httpMethod = "GET", tags = {"地区API"}, produces = "application/json")
    public Result<String> randomProvince() {
        return Result.success(AreaSource.getInstance().randomProvince());
    }

    /**
     * 随机省份和城市
     *
     * @return
     */
    @GetMapping(value = "/randomCity")
    @ApiOperation(value = "随机省份和城市", httpMethod = "GET", tags = {"地区API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "separator", value = "省份和城市之间的分隔符(默认为逗号)", required = true, dataType = "String")
    })
    public Result<String> randomCity(@RequestParam(name = "separator", required = false, defaultValue = ",") String separator) {
        return Result.success(AreaSource.getInstance().randomCity(separator));
    }

    /**
     * 随机地址
     *
     * @return
     */
    @GetMapping(value = "/randomAddress")
    @ApiOperation(value = "随机地址", httpMethod = "GET", tags = {"地区API"}, produces = "application/json")
    public Result<String> randomAddress() {
        return Result.success(AreaSource.getInstance().randomAddress());
    }

    /**
     * 随机邮编
     *
     * @return
     */
    @GetMapping(value = "/randomZipCode")
    @ApiOperation(value = "随机邮编", httpMethod = "GET", tags = {"地区API"}, produces = "application/json")
    public Result<String> randomZipCode() {
        return Result.success(AreaSource.getInstance().randomZipCode());
    }

    /**
     * 随机固话区号
     *
     * @return
     */
    @GetMapping(value = "/randomPhoneCode")
    @ApiOperation(value = "随机固话区号", httpMethod = "GET", tags = {"地区API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "province", value = "省份名称", required = true, dataType = "String")
    })
    public Result<String> randomPhoneCode(@RequestParam(name = "province", required = false, defaultValue = ",") String province) {
        return Result.success(AreaSource.getInstance().randomPhoneCode(province));
    }

    /**
     * 随机固话号码
     *
     * @return
     */
    @GetMapping(value = "/randomPhoneNumber")
    @ApiOperation(value = "随机固话号码", httpMethod = "GET", tags = {"地区API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "province", value = "省份名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "delimiter", value = "区号和号码之间的分隔符", required = true, dataType = "String")
    })
    public Result<String> randomPhoneNumber(@RequestParam(name = "province", required = false, defaultValue = ",") String province,
                                            @RequestParam(name = "delimiter", required = false, defaultValue = ",") String delimiter) {
        return Result.success(AreaSource.getInstance().randomPhoneNumber(province, delimiter));
    }

    /**
     * 随机国家或地区信息
     *
     * @return
     */
    @GetMapping(value = "/randomCountryOrRegionCode")
    @ApiOperation(value = "随机国家或地区信息", httpMethod = "GET", tags = {"地区API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startsWith", value = "首字母", required = true, dataType = "String")
    })
    public Result<CountryOrRegionCode> randomCountryOrRegionCode(@RequestParam(name = "startsWith", required = false, defaultValue = "") String startsWith) {
        return Result.success(AreaSource.getInstance().randomCountryOrRegionCode(startsWith));
    }

    /**
     * 随机经度
     *
     * @return
     */
    @GetMapping(value = "/randomLongitude")
    @ApiOperation(value = "随机经度", httpMethod = "GET", tags = {"地区API"}, produces = "application/json")
    public Result<Double> randomLongitude() {
        return Result.success(AreaSource.getInstance().randomLongitude());
    }

    /**
     * 随机纬度
     *
     * @return
     */
    @GetMapping(value = "/randomLatitude")
    @ApiOperation(value = "随机纬度", httpMethod = "GET", tags = {"地区API"}, produces = "application/json")
    public Result<Double> randomLatitude() {
        return Result.success(AreaSource.getInstance().randomLatitude());
    }
}
