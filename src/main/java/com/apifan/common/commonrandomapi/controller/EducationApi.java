package com.apifan.common.commonrandomapi.controller;

import com.apifan.common.commonrandomapi.response.Result;
import com.apifan.common.random.source.EducationSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 教育信息API
 *
 * @author yin
 */
@Api(value = "/education", tags = {"教育信息API"})
@RestController
@RequestMapping("/education")
public class EducationApi {
    private static final Logger logger = LoggerFactory.getLogger(EducationApi.class);

    /**
     * 随机学历
     *
     * @return
     */
    @GetMapping(value = "/randomDegree")
    @ApiOperation(value = "随机学历", httpMethod = "GET", tags = {"教育信息API"}, produces = "application/json")
    public Result<String> randomDegree() {
        return Result.success(EducationSource.getInstance().randomDegree());
    }

    /**
     * 随机小学名称
     *
     * @return
     */
    @GetMapping(value = "/randomPrimarySchoolName")
    @ApiOperation(value = "随机小学名称", httpMethod = "GET", tags = {"教育信息API"}, produces = "application/json")
    public Result<String> randomPrimarySchoolName() {
        return Result.success(EducationSource.getInstance().randomPrimarySchoolName());
    }

    /**
     * 随机小学年级
     *
     * @return
     */
    @GetMapping(value = "/randomPrimarySchoolGrade")
    @ApiOperation(value = "随机小学年级", httpMethod = "GET", tags = {"教育信息API"}, produces = "application/json")
    public Result<String> randomPrimarySchoolGrade() {
        return Result.success(EducationSource.getInstance().randomPrimarySchoolGrade());
    }

    /**
     * 随机中学名称
     *
     * @return
     */
    @GetMapping(value = "/randomHighSchoolName")
    @ApiOperation(value = "随机中学名称", httpMethod = "GET", tags = {"教育信息API"}, produces = "application/json")
    public Result<String> randomHighSchoolName() {
        return Result.success(EducationSource.getInstance().randomHighSchoolName());
    }

    /**
     * 随机中学年级
     *
     * @return
     */
    @GetMapping(value = "/randomHighSchoolGrade")
    @ApiOperation(value = "随机中学年级", httpMethod = "GET", tags = {"教育信息API"}, produces = "application/json")
    public Result<String> randomHighSchoolGrade() {
        return Result.success(EducationSource.getInstance().randomHighSchoolGrade());
    }

    /**
     * 随机班级名称
     *
     * @return
     */
    @GetMapping(value = "/randomClassName")
    @ApiOperation(value = "随机班级名称", httpMethod = "GET", tags = {"教育信息API"}, produces = "application/json")
    public Result<String> randomClassName() {
        return Result.success(EducationSource.getInstance().randomClassName());
    }

    /**
     * 随机高校名称
     *
     * @return
     */
    @GetMapping(value = "/randomCollege")
    @ApiOperation(value = "随机高校名称", httpMethod = "GET", tags = {"教育信息API"}, produces = "application/json")
    public Result<String> randomCollege() {
        return Result.success(EducationSource.getInstance().randomCollege());
    }
}
