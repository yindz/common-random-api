package com.apifan.common.commonrandomapi.controller;

import com.apifan.common.commonrandomapi.response.Result;
import com.apifan.common.random.source.InternetSource;
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

import java.util.function.Supplier;

/**
 * 互联网数据API
 *
 * @author yin
 */
@Api(value = "/internet", tags = {"互联网数据API"})
@RestController
@RequestMapping("/internet")
public class InternetApi {
    private static final Logger logger = LoggerFactory.getLogger(InternetApi.class);

    /**
     * 随机邮箱
     *
     * @return
     */
    @GetMapping(value = "/randomEmail")
    @ApiOperation(value = "随机邮箱", httpMethod = "GET", tags = {"互联网数据API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "maxLength", value = "最大长度", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "suffix", value = "邮箱后缀", required = true, dataType = "String")
    })
    public Result<String> randomEmail(@RequestParam(name = "maxLength", required = false, defaultValue = "10") int maxLength,
                                      @RequestParam(name = "suffix", required = false, defaultValue = "gmail.com") String suffix) {
        return Result.success(InternetSource.getInstance().randomEmail(maxLength, suffix));
    }

    /**
     * 随机域名
     *
     * @return
     */
    @GetMapping(value = "/randomDomain")
    @ApiOperation(value = "随机域名", httpMethod = "GET", tags = {"互联网数据API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "maxLength", value = "最大长度", required = true, dataType = "Integer")
    })
    public Result<String> randomDomain(@RequestParam(name = "maxLength", required = false, defaultValue = "10") int maxLength) {
        return Result.success(InternetSource.getInstance().randomDomain(maxLength));
    }

    /**
     * 随机静态资源URL
     *
     * @return
     */
    @GetMapping(value = "/randomStaticUrl")
    @ApiOperation(value = "随机静态资源URL", httpMethod = "GET", tags = {"互联网数据API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "suffix", value = "后缀", required = true, dataType = "String")
    })
    public Result<String> randomStaticUrl(@RequestParam(name = "suffix", required = false, defaultValue = "html") String suffix) {
        return Result.success(InternetSource.getInstance().randomStaticUrl(suffix));
    }

    /**
     * 随机IPv4地址
     *
     * @return
     */
    @GetMapping(value = "/randomIpv4")
    @ApiOperation(value = "随机IPv4地址", httpMethod = "GET", tags = {"互联网数据API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类别：1公网地址，2内网地址", required = true, dataType = "String")
    })
    public Result<String> randomIpv4(@RequestParam(name = "type", required = false, defaultValue = "1") String type) {
        return Result.success("1".equals(type) ? InternetSource.getInstance().randomPublicIpv4() : InternetSource.getInstance().randomPrivateIpv4());
    }

    /**
     * 随机IPv6地址
     *
     * @return
     */
    @GetMapping(value = "/randomIpv6")
    @ApiOperation(value = "随机IPv6地址", httpMethod = "GET", tags = {"互联网数据API"}, produces = "application/json")
    public Result<String> randomIpv6() {
        return Result.success(InternetSource.getInstance().randomIpV6());
    }

    /**
     * 随机端口号
     *
     * @return
     */
    @GetMapping(value = "/randomPort")
    @ApiOperation(value = "随机端口号", httpMethod = "GET", tags = {"互联网数据API"}, produces = "application/json")
    public Result<Integer> randomPort() {
        return Result.success(InternetSource.getInstance().randomPort());
    }

    /**
     * 随机网卡MAC地址
     *
     * @return
     */
    @GetMapping(value = "/randomMacAddress")
    @ApiOperation(value = "随机网卡MAC地址", httpMethod = "GET", tags = {"互联网数据API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "separator", value = "各段之间的分隔符(默认为:)", required = true, dataType = "String")
    })
    public Result<String> randomMacAddress(@RequestParam(name = "separator", required = false, defaultValue = ":") String separator) {
        return Result.success(InternetSource.getInstance().randomMacAddress(separator));
    }

    /**
     * 随机UserAgent
     *
     * @return
     */
    @GetMapping(value = "/randomUserAgent")
    @ApiOperation(value = "随机UserAgent", httpMethod = "GET", tags = {"互联网数据API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类别：1-安卓，2-iOS, 3-PC", required = true, dataType = "String")
    })
    public Result<String> randomUserAgent(@RequestParam(name = "type", required = false, defaultValue = "1") String type) {
        Supplier<String> ua = () -> InternetSource.getInstance().randomPCUserAgent();
        if ("1".equals(type)) {
            ua = () -> InternetSource.getInstance().randomAndroidUserAgent();
        } else if ("2".equals(type)) {
            ua = () -> InternetSource.getInstance().randomIOSUserAgent();
        }
        return Result.success(ua.get());
    }

    /**
     * 随机appBundleId
     *
     * @return
     */
    @GetMapping(value = "/randomAppBundleId")
    @ApiOperation(value = "随机appBundleId", httpMethod = "GET", tags = {"互联网数据API"}, produces = "application/json")
    public Result<String> randomAppBundleId() {
        return Result.success(InternetSource.getInstance().randomAppBundleId());
    }

    /**
     * 随机App名称
     *
     * @return
     */
    @GetMapping(value = "/randomAppName")
    @ApiOperation(value = "随机App名称", httpMethod = "GET", tags = {"互联网数据API"}, produces = "application/json")
    public Result<String> randomAppName() {
        return Result.success(InternetSource.getInstance().randomAppName());
    }

    /**
     * 随机App版本号
     *
     * @return
     */
    @GetMapping(value = "/randomAppVersionCode")
    @ApiOperation(value = "随机app版本号", httpMethod = "GET", tags = {"互联网数据API"}, produces = "application/json")
    public Result<String> randomAppVersionCode() {
        return Result.success(InternetSource.getInstance().randomAppVersionCode());
    }
}
