package investment.foundation;

import java.util.Map;

public class Config {

    public final static Map<String, String> FOUNDATIONS = Map.of(
            "519674", "银河创新成长混合",
            "110022", "易方达消费行业股票"
    );

    public static String foundationUrl(String foundationCode){
        return String.format("http://fund.eastmoney.com/%s.html", foundationCode);
    }
}
