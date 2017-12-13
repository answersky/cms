package com.java.controller;

import com.java.parser.fico.BigDataScoreClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author answer
 *         2017/12/12
 *         fico 接口对接结果返回
 */
@Controller
public class FicoController {
    @Resource
    private BigDataScoreClient bigDataScoreClient;

    @RequestMapping("/ficoResult")
    @ResponseBody
    public List<Map<String, String>> ficoResult(String idCard, String tel) {
        return bigDataScoreClient.analysis(idCard, tel);
    }
}
