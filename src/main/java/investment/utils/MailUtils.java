package investment.utils;

import investment.foundation.modal.Foundation;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.*;
import java.util.stream.Collectors;

import static investment.cache.FileStorage.loadStorageFile;
import static investment.utils.Utils.getDateYYYY_MM_DD;

public class MailUtils {
    private final static TemplateEngine templateEngine = new TemplateEngine();
    static {
        templateEngine.setTemplateResolver(new ClassLoaderTemplateResolver());
    }

    public static String email() {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>" +
                generateAggregateInfo() +
                "</br>" +
                "</br>" +
                generateFoundationDetailsTables() +
                "</body>" +
                "</html>";
    }
    
    public static String renderFoundationEmailContent(List<Foundation> foundations) {
        return email();
    }



    private static String generateAggregateInfo() {
        List<Foundation> list = loadStorageFile();
        OptionalInt max = loadStorageFile().stream().mapToInt(it -> Integer.parseInt(it.getDate().replace("-", ""))).max();
        StringBuilder builder = new StringBuilder();
        if (max.isPresent()) {
            List<Foundation> collect = list.stream().filter(it -> String.valueOf(max.getAsInt()).equalsIgnoreCase(it.getDate().replace("-", ""))).collect(Collectors.toList());
            builder.append("<table border=\"1\">" +
                    "<th>日期</th>" +
                    "<th>代码</th>" +
                    "<th>名称</th>" +
                    "<th>估值</th>" +
                    "<th>估值涨幅</th>" +
                    "<th>当日净值</th>" +
                    "<th>当日涨幅</th>" +
                    "<th>累计净值</th>" +
                    "<th>周内涨幅</th>" +
                    "<th>月内涨幅</th>" +
                    "<th>三月内涨幅</th>" +
                    "<th>六月内涨幅</th>" +
                    "<th>周内同类排名</th>" +
                    "<th>月内同类排名</th>" +
                    "<th>三月内同类排名</th>" +
                    "<th>六月内同类排名</th>" +
                    "<tbody>"
            );
            builder.append("<tr>");
            collect.forEach(it-> {
                builder.append("<tr>");
                builder.append("<td>").append(it.getDate()).append("</td>")
                        .append("<td>").append(it.getName()).append("</td>")
                        .append("<td>").append(it.getCode()).append("</td>")
                        .append("<td>").append(it.getEstimatedValue()).append("</td>")
                        .append("<td>").append(it.getEstimatedGain()).append("</td>")
                        .append("<td>").append(it.getActualValue()).append("</td>")
                        .append("<td>").append(it.getActualGain()).append("</td>")
                        .append("<td>").append(it.getActualValue()).append("</td>")
                        .append("<td>").append(it.getGainWithinWeek()).append("</td>")
                        .append("<td>").append(it.getGainWithinMonth()).append("</td>")
                        .append("<td>").append(it.getGainWithinThreeMonth()).append("</td>")
                        .append("<td>").append(it.getGainWithinSixMonth()).append("</td>")
                        .append("<td>").append(it.getRankWithinWeek()).append("</td>")
                        .append("<td>").append(it.getRankWithinMonth()).append("</td>")
                        .append("<td>").append(it.getRankWithinThreeMonth()).append("</td>")
                        .append("<td>").append(it.getRankWithinSixMonth()).append("</td>");
                builder.append("</tr>");
            });
            builder.append("    </tbody>" +
                    "</table>");
        }


        return builder.toString();
    }
    
    private static String generateFoundationDetailsTables() {
        List<Foundation> list = loadStorageFile();
        Map<String, List<Foundation>> foundationsGroupByCode = list.stream().collect(Collectors.groupingBy(Foundation::getCode));
        StringBuilder builder = new StringBuilder();
        foundationsGroupByCode.forEach((code, foundations) -> {
            builder.append("<div style=\"height:400px; width:400px;float:left;margin-left:20px;\">")
                    .append("<table border=\"1\">")
                    .append("<th>日期</th>" +
                            "    <th>代码</th>" +
                            "    <th>名称</th>" +
                            "    <th>当日净值</th>" +
                            "    <th>当日涨幅</th>"
                    )
                    .append("<tbody>");
            Optional<Foundation> foundation = foundations.stream().findFirst();
            String date = foundation.isPresent() ? foundation.get().getDate() : getDateYYYY_MM_DD();
            foundations.forEach(it -> {
                builder.append("<tr>").append("<td>").append(date).append("</td>")
                        .append("<td>").append(code).append("</td>")
                        .append("<td>").append(it.getName()).append("</td>")
                        .append("<td>").append(it.getActualValue()).append("</td>")
                        .append("<td>").append(it.getActualGain()).append("</td>")
                        .append("</tr>");
            });
            builder.append("<tbody>")
                    .append("</table>")
                    .append("</div>");
        });
        return builder.toString();
    }

    public static void main(String[] args) {
//        OptionalInt max = loadStorageFile().stream().mapToInt(it -> Integer.parseInt(it.getDate().replace("-", ""))).max();
//        System.out.println(max.getAsInt());
        System.out.println(email());
//        System.out.println(generateAggregateInfo());
//        System.out.println(generateFoundationDetailsTables());
    }
}
