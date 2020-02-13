package investment.config;

import java.util.Map;

public class Config {

    public static String foundationUrl(String foundationCode) {
        return String.format("http://fund.eastmoney.com/%s.html", foundationCode);
    }

    public static Map<String, String> FOUNDATIONS = Map.of(
            "519674", "银河创新成长混合",
            "110022", "易方达消费行业股票",
            "260108", "景顺长城新兴成长混合",
            "005911", "广发双擎升级混合",
            "004851", "广发医疗保健股票",
            "002121", "广发沪港深新起点股票",
//            "161725", "招商中证白酒指数基金",
            "005311", "万家经济新动能混合A",
            "519056", "海富通内需热点混合");
}
