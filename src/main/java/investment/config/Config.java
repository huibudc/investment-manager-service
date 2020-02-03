package investment.config;

public class Config {

    public static String foundationUrl(String foundationCode){
        return String.format("http://fund.eastmoney.com/%s.html", foundationCode);
    }
}
