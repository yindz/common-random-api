package com.apifan.common.commonrandomapi.controller;

import com.apifan.common.commonrandomapi.response.Result;
import com.apifan.common.random.constant.CompetitionType;
import com.apifan.common.random.source.SportSource;
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
 * 体育竞技API
 *
 * @author yin
 */
@Api(value = "/sport", tags = {"体育竞技API"})
@RestController
@RequestMapping("/sport")
public class SportApi {
    private static final Logger logger = LoggerFactory.getLogger(SportApi.class);

    /**
     * 随机足球队名称
     *
     * @return
     */
    @GetMapping(value = "/randomFootballTeam")
    @ApiOperation(value = "随机足球队名称", httpMethod = "GET", tags = {"体育竞技API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型：英超/西甲/德甲/意甲/法甲/荷甲", required = true, dataType = "String")
    })
    public Result<String> randomFootballTeam(@RequestParam(name = "type", required = false, defaultValue = "西甲") String type) {
        CompetitionType competitionType = null;
        if ("英超".equals(type)) {
            competitionType = CompetitionType.PREMIER_LEAGUE;
        } else if ("西甲".equals(type)) {
            competitionType = CompetitionType.LA_LIGA;
        } else if ("德甲".equals(type)) {
            competitionType = CompetitionType.BUNDESLIGA;
        } else if ("意甲".equals(type)) {
            competitionType = CompetitionType.SERIE_A;
        } else if ("法甲".equals(type)) {
            competitionType = CompetitionType.LIGUE_1;
        } else if ("荷甲".equals(type)) {
            competitionType = CompetitionType.EREDIVISIE;
        }
        return Result.success(SportSource.getInstance().randomFootballTeam(competitionType));
    }

    /**
     * 随机篮球队名称
     *
     * @return
     */
    @GetMapping(value = "/randomBasketballTeam")
    @ApiOperation(value = "随机篮球队名称", httpMethod = "GET", tags = {"体育竞技API"}, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型：CBA/NBA", required = true, dataType = "String")
    })
    public Result<String> randomBasketballTeam(@RequestParam(name = "type", required = false, defaultValue = "西甲") String type) {
        CompetitionType competitionType = null;
        if ("CBA".equals(type)) {
            competitionType = CompetitionType.CBA;
        } else if ("NBA".equals(type)) {
            competitionType = CompetitionType.NBA;
        }
        return Result.success(SportSource.getInstance().randomBasketballTeam(competitionType));
    }
}
