package com.apifan.common.commonrandomapi.controller;

import com.apifan.common.commonrandomapi.component.BatchDataGenerator;
import com.apifan.common.commonrandomapi.response.Result;
import com.apifan.common.random.constant.CreditCardType;
import com.apifan.common.random.source.PersonInfoSource;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

/**
 * 个人信息API
 *
 * @author yin
 */
@Api(value = "/person", tags = {"个人信息API"})
@RestController
@RequestMapping("/person")
public class PersonApi {
    private static final Logger logger = LoggerFactory.getLogger(PersonApi.class);

    private static final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Autowired
    private BatchDataGenerator batchDataGenerator;

    /**
     * 虚拟中文名
     *
     * @param gender
     * @return
     */
    @GetMapping(value = "/randomChineseName")
    @ApiOperation(value = "虚拟中文名", httpMethod = "GET", tags = {"个人信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gender", value = "性别：1男性，0女性，留空为随机", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer")
    })
    public Result<String[]> randomChineseName(@RequestParam(name = "gender", required = false) Integer gender,
                                              @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        Supplier<String> nameGetter = () -> PersonInfoSource.getInstance().randomChineseName();
        if (gender != null) {
            if (gender == 1) {
                nameGetter = () -> PersonInfoSource.getInstance().randomMaleChineseName();
            } else {
                nameGetter = () -> PersonInfoSource.getInstance().randomFemaleChineseName();
            }
        }
        return Result.success(batchDataGenerator.getStringArray(nameGetter, size));
    }

    /**
     * 虚拟英文名
     *
     * @return
     */
    @GetMapping(value = "/randomEnglishName")
    @ApiOperation(value = "虚拟英文名", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer")
    })
    public Result<String[]> randomEnglishName(@RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return Result.success(batchDataGenerator.getStringArray(() -> PersonInfoSource.getInstance().randomEnglishName(), size));
    }

    /**
     * 虚拟中文昵称
     *
     * @return
     */
    @GetMapping(value = "/randomChineseNickName")
    @ApiOperation(value = "虚拟中文昵称", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "maxLength", value = "字数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer")
    })
    public Result<String[]> randomChineseNickName(@RequestParam(name = "maxLength", required = false, defaultValue = "4") Integer maxLength,
                                                  @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return Result.success(batchDataGenerator.getStringArray(() -> PersonInfoSource.getInstance().randomChineseNickName(maxLength), size));
    }

    /**
     * 虚拟拼音昵称
     *
     * @return
     */
    @GetMapping(value = "/randomPinyinNickName")
    @ApiOperation(value = "虚拟拼音昵称", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "maxLength", value = "字数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer")
    })
    public Result<String[]> randomPinyinNickName(@RequestParam(name = "maxLength", required = false, defaultValue = "4") Integer maxLength,
                                                 @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return Result.success(batchDataGenerator.getStringArray(() -> PersonInfoSource.getInstance().randomPinyinNickName(maxLength), size));
    }

    /**
     * 虚拟英文昵称
     *
     * @return
     */
    @GetMapping(value = "/randomNickName")
    @ApiOperation(value = "虚拟英文昵称", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "maxLength", value = "字数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer")
    })
    public Result<String[]> randomNickName(@RequestParam(name = "maxLength", required = false, defaultValue = "4") Integer maxLength,
                                           @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return Result.success(batchDataGenerator.getStringArray(() -> PersonInfoSource.getInstance().randomNickName(maxLength), size));
    }

    /**
     * 虚拟手机号码
     *
     * @return
     */
    @GetMapping(value = "/randomChineseMobile")
    @ApiOperation(value = "虚拟手机号码", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    public Result<String[]> randomChineseMobile(@RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return Result.success(batchDataGenerator.getStringArray(() -> PersonInfoSource.getInstance().randomChineseMobile(), size));
    }

    /**
     * 随机强密码
     *
     * @return
     */
    @GetMapping(value = "/randomStrongPassword")
    @ApiOperation(value = "随机强密码", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length", value = "长度", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "useSpecialChar", value = "是否包含特殊字符", required = true, dataType = "Boolean")
    })
    public Result<String> randomStrongPassword(@RequestParam(name = "length", required = false, defaultValue = "8") Integer length,
                                               @RequestParam(name = "useSpecialChar", required = false, defaultValue = "true") boolean useSpecialChar) {
        return Result.success(PersonInfoSource.getInstance().randomStrongPassword(length, useSpecialChar));
    }

    /**
     * 虚拟QQ号
     *
     * @return
     */
    @GetMapping(value = "/randomQQAccount")
    @ApiOperation(value = "虚拟QQ号", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer")
    })
    public Result<String[]> randomQQAccount(@RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return Result.success(batchDataGenerator.getStringArray(() -> PersonInfoSource.getInstance().randomQQAccount(), size));
    }

    /**
     * 虚拟QQ昵称
     *
     * @return
     */
    @GetMapping(value = "/randomQQNickName")
    @ApiOperation(value = "虚拟QQ昵称", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer")
    })
    public Result<String[]> randomQQNickName(@RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return Result.success(batchDataGenerator.getStringArray(() -> PersonInfoSource.getInstance().randomQQNickName(), size));
    }

    /**
     * 虚拟男性身份证号码
     *
     * @return
     */
    @GetMapping(value = "/randomMaleIdCard")
    @ApiOperation(value = "虚拟男性身份证号码", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "minAge", value = "最小年龄", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "maxAge", value = "最大年龄", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "province", value = "省份名称", required = true, dataType = "String")
    })
    public Result<String> randomMaleIdCard(@RequestParam(name = "minAge", required = false, defaultValue = "1") int minAge,
                                           @RequestParam(name = "maxAge", required = false, defaultValue = "18") int maxAge,
                                           @RequestParam(name = "province", required = false, defaultValue = "") String province) {
        Preconditions.checkArgument(minAge <= maxAge, "年龄不正确");
        return Result.success(PersonInfoSource.getInstance().randomMaleIdCard(province, minAge, maxAge));
    }

    /**
     * 虚拟男性身份证号码(按日期范围)
     *
     * @return
     */
    @GetMapping(value = "/randomMaleIdCardByDate")
    @ApiOperation(value = "虚拟男性身份证号码(按日期范围)", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginDate", value = "开始日期(yyyyMMdd)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endDate", value = "结束日期(yyyyMMdd)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "province", value = "省份名称", required = true, dataType = "String")
    })
    public Result<String> randomMaleIdCardByDate(@RequestParam("beginDate") String beginDate,
                                                 @RequestParam("endDate") String endDate,
                                                 @RequestParam("province") String province) {
        return Result.success(PersonInfoSource.getInstance().randomMaleIdCard(province, LocalDate.parse(beginDate, yyyyMMdd), LocalDate.parse(endDate, yyyyMMdd)));
    }

    /**
     * 虚拟女性身份证号码
     *
     * @return
     */
    @GetMapping(value = "/randomFemaleIdCard")
    @ApiOperation(value = "虚拟女性身份证号码", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "minAge", value = "最小年龄", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "maxAge", value = "最大年龄", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "province", value = "省份名称", required = true, dataType = "String")
    })
    public Result<String> randomFemaleIdCard(@RequestParam(name = "minAge", required = false, defaultValue = "1") int minAge,
                                             @RequestParam(name = "maxAge", required = false, defaultValue = "18") int maxAge,
                                             @RequestParam(name = "province", required = false, defaultValue = "") String province) {
        Preconditions.checkArgument(minAge <= maxAge, "年龄不正确");
        return Result.success(PersonInfoSource.getInstance().randomFemaleIdCard(province, minAge, maxAge));
    }

    /**
     * 虚拟女性身份证号码(按日期范围)
     *
     * @return
     */
    @GetMapping(value = "/randomFemaleIdCardByDate")
    @ApiOperation(value = "虚拟女性身份证号码(按日期范围)", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginDate", value = "开始日期(yyyyMMdd)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endDate", value = "结束日期(yyyyMMdd)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "province", value = "省份名称", required = true, dataType = "String")
    })
    public Result<String> randomFemaleIdCardByDate(@RequestParam("beginDate") String beginDate,
                                                   @RequestParam("endDate") String endDate,
                                                   @RequestParam("province") String province) {
        return Result.success(PersonInfoSource.getInstance().randomFemaleIdCard(province, LocalDate.parse(beginDate, yyyyMMdd), LocalDate.parse(endDate, yyyyMMdd)));
    }

    /**
     * 虚拟信用卡号码
     *
     * @return
     */
    @GetMapping(value = "/randomCreditCardNo")
    @ApiOperation(value = "虚拟信用卡号码", httpMethod = "GET", tags = {"其它信息API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型：VISA/MasterCard/Amex/JCB/UnionPay", required = true, dataType = "String"),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer")
    })
    public Result<String[]> randomCreditCardNo(@RequestParam(name = "type", required = false, defaultValue = "") String type,
                                               @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        CreditCardType cardType = CreditCardType.UnionPay;
        if ("VISA".equalsIgnoreCase(type)) {
            cardType = CreditCardType.Visa;
        } else if ("MasterCard".equalsIgnoreCase(type)) {
            cardType = CreditCardType.MasterCard;
        } else if ("Amex".equalsIgnoreCase(type)) {
            cardType = CreditCardType.Amex;
        } else if ("JCB".equalsIgnoreCase(type)) {
            cardType = CreditCardType.JCB;
        }
        CreditCardType finalCardType = cardType;
        return Result.success(batchDataGenerator.getStringArray(() -> PersonInfoSource.getInstance().randomCreditCardNo(finalCardType), size));
    }

}
