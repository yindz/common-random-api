package com.apifan.common.commonrandomapi.controller;

import com.apifan.common.commonrandomapi.response.Result;
import com.apifan.common.random.source.OtherSource;
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

/**
 * 其它信息API
 *
 * @author yin
 */
@Api(value = "/other", tags = {"其它信息API"})
@RestController
@RequestMapping("/other")
public class OtherApi {
    private static final Logger logger = LoggerFactory.getLogger(OtherApi.class);

    /**
     * 随机汉字
     *
     * @param count
     * @return
     */
    @GetMapping(value = "/randomChinese")
    @ApiOperation(value = "随机汉字", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "count", value = "个数", required = true, dataType = "Integer")
    })
    public Result<String> randomChinese(@RequestParam("count") int count) {
        Preconditions.checkArgument(count > 0, "count无效");
        return Result.success(OtherSource.getInstance().randomChinese(count));
    }

    /**
     * 随机车牌号
     *
     * @param isNewEnergyVehicle
     * @return
     */
    @GetMapping(value = "/randomPlateNumber")
    @ApiOperation(value = "随机车牌号", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isNewEnergyVehicle", value = "是否为新能源车", required = true, dataType = "Boolean")
    })
    public Result<String> randomPlateNumber(@RequestParam("isNewEnergyVehicle") boolean isNewEnergyVehicle) {
        return Result.success(OtherSource.getInstance().randomPlateNumber(isNewEnergyVehicle));
    }

    /**
     * 随机企业名称
     *
     * @param province
     * @return
     */
    @GetMapping(value = "/randomCompanyName")
    @ApiOperation(value = "随机企业名称", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "province", value = "省份名称", required = true, dataType = "String")
    })
    public Result<String> randomCompanyName(@RequestParam("province") String province) {
        return Result.success(OtherSource.getInstance().randomCompanyName(province));
    }

    /**
     * 随机企业部门名称
     *
     * @return
     */
    @GetMapping(value = "/randomCompanyDepartment")
    @ApiOperation(value = "随机企业部门名称", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    public Result<String> randomCompanyDepartment() {
        return Result.success(OtherSource.getInstance().randomCompanyDepartment());
    }

    /**
     * 随机中文短句
     *
     * @return
     */
    @GetMapping(value = "/randomChineseSentence")
    @ApiOperation(value = "随机中文短句", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    public Result<String> randomChineseSentence() {
        return Result.success(OtherSource.getInstance().randomChineseSentence());
    }

    /**
     * 随机RGB颜色
     *
     * @return
     */
    @GetMapping(value = "/randomRgbColor")
    @ApiOperation(value = "随机RGB颜色", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    public Result<int[]> randomRgbColor() {
        return Result.success(OtherSource.getInstance().randomRgbColor());
    }

    /**
     * 随机16进制颜色
     *
     * @return
     */
    @GetMapping(value = "/randomHexColor")
    @ApiOperation(value = "随机16进制颜色", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    public Result<String> randomHexColor() {
        return Result.success(OtherSource.getInstance().randomHexColor());
    }

    /**
     * 随机手机型号
     *
     * @return
     */
    @GetMapping(value = "/randomMobileModel")
    @ApiOperation(value = "随机手机型号", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    public Result<String> randomMobileModel() {
        return Result.success(OtherSource.getInstance().randomMobileModel());
    }

}
