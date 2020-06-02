package com.itstyle.modules.control;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 10097454
 * @Date 2020/05/11
 * @Description TODO
 */
@Api(tags = "admin")
@Controller
@RequestMapping(value = "admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @ApiOperation(value = "getInfo")
    @RequestMapping(value = "getInfo")
    public @ResponseBody
    String getInfo() {
        logger.info("getInfo");
        String res = "{\"code\":0,\"data\":{\"name\":\"管理员\",\"roles\":[\"Home\",\"Dashbord\",\"Driver\",\"Driver-index\",\"Permission\",\"PageUser\",\"Table\",\"BaseTable\",\"ComplexTable\",\"Icons\",\"Icons-index\",\"Components\",\"Sldie-yz\",\"Upload\",\"Carousel\",\"Echarts\",\"Sldie-chart\",\"Dynamic-chart\",\"Map-chart\",\"Excel\",\"Excel-out\",\"Excel-in\",\"Mutiheader-out\",\"Error\",\"Page404\",\"Github\",\"NavTest\",\"Nav1\",\"Nav2\",\"Nav2-1\",\"Nav2-2\",\"Nav2-2-1\",\"Nav2-2-2\",\"*404\"],\"introduce\":\"亲率但将象件电见空四展装元华斗变理任以总反种广复因厂出空非天经决海根断两听必去界总。\"},\"_res\":{\"status\":401,\"data\":{\"msg\":\"未授权\"}}}";
        return res;
    }

    @ApiOperation(value = "getCardsData")
    @RequestMapping(value = "getCardsData")
    public @ResponseBody
    String getCardsData() {
        logger.info("getCardsData");
        String res = "{\"code\":-63648127.69299202,\"data\":{\"vistors\":85306101.99497089,\"message\":-1559682.4655572176,\"order\":-13883331.932919279,\"profit\":-31837494.19134161}}";
        return res;
    }

    @ApiOperation(value = "getLineData")
    @RequestMapping(value = "getLineData")
    public @ResponseBody
    String getLineData() {
        logger.info("getLineData");
        String res = "{\"code\":0,\"data\":{\"name\":\"admin\",\"roles\":[\"Home\",\"Dashbord\",\"Permission\",\"PageUser\",\"PageAdmin\",\"Roles\",\"Table\",\"BaseTable\",\"ComplexTable\",\"Icons\",\"Icons-index\",\"Components\",\"Sldie-yz\",\"Upload\",\"Carousel\",\"Echarts\",\"Sldie-chart\",\"Dynamic-chart\",\"Map-chart\",\"Excel\",\"Excel-out\",\"Excel-in\",\"Mutiheader-out\",\"Error\",\"Page404\",\"Github\",\"NavTest\",\"Nav1\",\"Nav2\",\"Nav2-1\",\"Nav2-2\",\"Nav2-2-1\",\"Nav2-2-2\",\"*404\"],\"introduce\":\"片包率不装称节象非风开在事团开感复正张发王增法子天目越改变命者志清好两温取元少共术能光华。\"},\"_res\":{\"status\":200}}";
        return res;
    }

    @ApiOperation(value = "getTableList")
    @RequestMapping(value = "getTableList")
    public @ResponseBody
    String getTableList() {
        logger.info("getTableList");
        String res = "{\"code\":-2165808.9109781235,\"data\":{\"tableList\":[{\"id\":\"qui anim\",\"name\":\"eiusmod\",\"price\":-54390655.216081195,\"quantity\":62518930.1395621,\"status\":8639729.928186715},{\"id\":\"consectetur\",\"name\":\"quis qui\",\"price\":29498780.542776167,\"quantity\":-2967477.0176068693,\"status\":-50524275.33865913},{\"id\":\"Ut minim Lorem laborum\",\"name\":\"minim cupidatat\",\"price\":26691154.886257395,\"quantity\":39810826.99273765,\"status\":-99178750.72931133},{\"id\":\"qui cillum deserunt culpa cupidatat\",\"name\":\"velit nulla voluptate sunt\",\"price\":-62086078.44818266,\"quantity\":35767902.06186113,\"status\":-37479794.84845971}]}}";
        return res;
    }

    @ApiOperation(value = "getBarData")
    @RequestMapping(value = "getBarData")
    public @ResponseBody
    String getBarData() {
        logger.info("getBarData");
        String res = "{\"code\":8145605.510918766,\"data\":{\"y2017\":[-95690564.46796732],\"y2018\":[-75143818.01183774],\"y2019\":[59756357.252441674]}}";
        return res;
    }

    @ApiOperation(value = "getRoles")
    @RequestMapping(value = "getRoles")
    public @ResponseBody
    String getRoles() {
        logger.info("getRoles");
        String res = "{\"code\":-6069541.345862061,\"data\":{\"allRoles\":[{\"key\":\"exercitation\",\"description\":\"do\",\"pages\":[\"voluptate fugiat\",\"in in sunt aliquip culp\"]},{\"key\":\"dolor\",\"description\":\"irure sed consequat Excepteur\",\"pages\":[\"reprehenderit Lor\"]},{\"key\":\"sint\",\"description\":\"commodo cupidatat ipsum et ullamco\",\"pages\":[\"laborum labore pariatur ipsum in\",\"eu id ea ipsum est\",\"Lorem veniam laborum\"]},{\"key\":\"non velit mollit\",\"description\":\"irure ut\",\"pages\":[\"in ea eiusmod anim\",\"irure dolor\"]}]}}";
        return res;
    }

    @ApiOperation(value = "getPageData1")
    @RequestMapping(value = "getPageData1")
    public @ResponseBody
    String getPageData1(Integer currentPage, Integer pageSize) {
        logger.info("getPageData1");
        String res = "{\"code\":-47841481.355395965,\"data\":{\"total\":81861020.92872417,\"tableList\":[{\"num\":63487754.626603186,\"id\":\"voluptate cupidatat\",\"name\":\"sed\",\"price\":-87697780.45884551,\"quantity\":-56489065.02782034,\"status\":33672518.30303703},{\"num\":28732762.34533702,\"id\":\"consectetur aliqua velit\",\"name\":\"cupidatat ullamco consequat fugiat culpa\",\"price\":-36213202.15619747,\"quantity\":-16365713.056882307,\"status\":72628498.72955406}]}}";
        return res;
    }

    @ApiOperation(value = "getPageData2")
    @RequestMapping(value = "getPageData2")
    public @ResponseBody
    String getPageData2() {
        logger.info("getPageData2");
        String res = "{\"code\":26762942.294141993,\"data\":{\"total\":25038023.178766757,\"tableList\":[{\"id\":96211051.02729279,\"order\":\"elit\",\"name\":\"laboris\",\"time\":\"occaecat ea\",\"address\":\"incididunt mollit commodo nisi\",\"phone\":\"adi\",\"status\":-99832896.31337419},{\"id\":17816470.00936486,\"order\":\"reprehenderit\",\"name\":\"aliqui\",\"time\":\"pariatur fugiat cupidatat ullamco\",\"address\":\"id irure\",\"phone\":\"sint Duis cupidatat\",\"status\":20440231.15409699},{\"id\":-71839756.40038404,\"order\":\"laborum fugiat reprehenderit \",\"name\":\"fugiat labore\",\"time\":\"sunt\",\"address\":\"tempor eiusmod fugiat Lorem dolore\",\"phone\":\"dolor officia\",\"status\":23250086.318991065},{\"id\":34420453.07350412,\"order\":\"non sint elit qui\",\"name\":\"sint est\",\"time\":\"sed Excepteur est ipsum\",\"address\":\"aliquip dolor Ut ut\",\"phone\":\"eu labore Ut ex deserunt\",\"status\":82642025.53970996},{\"id\":28345103.628910676,\"order\":\"eiusmod Excepteur Ut anim\",\"name\":\"magna irure id dolore reprehenderit\",\"time\":\"amet occaecat\",\"address\":\"sunt amet pariatur dolor\",\"phone\":\"qui consectetur in commodo\",\"status\":74304777.75581807}]}}";
        return res;
    }

    @ApiOperation(value = "login")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody
    String login() {
        logger.info("login");
        String res = "{\"code\":0,\"data\":{\"success\":true,\"msg\":\"登陆成功\",\"token\":\"admin-token\",\"user\":\"admin\"}}";
        return res;
    }


}
