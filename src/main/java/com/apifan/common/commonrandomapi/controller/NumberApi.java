package com.apifan.common.commonrandomapi.controller;

import com.apifan.common.commonrandomapi.response.Result;
import com.apifan.common.random.source.NumberSource;
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
 * 数字API
 *
 * @author yin
 */
@Api(value = "/number", tags = {"数字API"})
@RestController
@RequestMapping("/number")
public class NumberApi {
    private static final Logger logger = LoggerFactory.getLogger(NumberApi.class);

    /**
     * 随机整数
     *
     * @param min
     * @param max
     * @param size
     * @return
     */
    @GetMapping(value = "/randomInt")
    @ApiOperation(value = "随机整数", httpMethod = "GET", tags = {"数字API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "min", value = "最小值", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "max", value = "最大值", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer")
    })
    public Result<int[]> randomInt(@RequestParam("min") int min,
                                   @RequestParam("max") int max,
                                   @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        Preconditions.checkArgument(min < max, "min必须小于max");
        Preconditions.checkArgument(size > 0, "size必须大于0");
        return Result.success(NumberSource.getInstance().randomInt(min, max, size));
    }

    /**
     * 随机长整数
     *
     * @param min
     * @param max
     * @param size
     * @return
     */
    @GetMapping(value = "/randomLong")
    @ApiOperation(value = "随机长整数", httpMethod = "GET", tags = {"数字API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "min", value = "最小值", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "max", value = "最大值", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer")
    })
    public Result<long[]> randomLong(@RequestParam("min") long min,
                                     @RequestParam("max") long max,
                                     @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        Preconditions.checkArgument(min < max, "min必须小于max");
        Preconditions.checkArgument(size > 0, "size必须大于0");
        return Result.success(NumberSource.getInstance().randomLong(min, max, size));
    }

    /**
     * 随机双精度浮点数
     *
     * @param min
     * @param max
     * @param size
     * @return
     */
    @GetMapping(value = "/randomDouble")
    @ApiOperation(value = "随机双精度浮点数", httpMethod = "GET", tags = {"数字API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "min", value = "最小值", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "max", value = "最大值", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer")
    })
    public Result<double[]> randomDouble(@RequestParam("min") double min,
                                         @RequestParam("max") double max,
                                         @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        Preconditions.checkArgument(min < max, "min必须小于max");
        Preconditions.checkArgument(size > 0, "size必须大于0");
        return Result.success(NumberSource.getInstance().randomDouble(min, max, size));
    }
}
