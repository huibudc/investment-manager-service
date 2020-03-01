package investment.foundation.controller;

import investment.cache.FileStorage;
import investment.foundation.modal.Foundation;
import investment.foundation.modal.FoundationChart;
import investment.foundation.modal.FoundationChartOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

import static investment.cache.cacheStore.getFoundationMapStorage;
import static investment.utils.Utils.GSON;

@RestController
public class FoundationController {
    private final static Logger LOGGER = LoggerFactory.getLogger(FoundationController.class);
    public static final String EMPTY_STRING = "";

    @GetMapping("/all")
    public String all() {
        List<Foundation> foundationList = new ArrayList<>();
        getFoundationMapStorage()
                .values()
                .forEach(foundationList::addAll);
        Map<String, List<FoundationChart>> foundationCharts = foundationList
                .stream()
                .map(this::convert)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(FoundationChart::getCode));
        List<FoundationChartOption> result = foundationCharts
                .keySet()
                .stream()
                .map((key) -> {
                    System.out.println(key);
                    List<FoundationChart> list = foundationCharts.get(key);
                    list.sort(Comparator.comparing(FoundationChart::getActualDate).thenComparing(FoundationChart::getActualDate));
                    String name = list.get(0).getName();
                    List<String> xAxis = list.stream().map(FoundationChart::getActualDate).collect(Collectors.toList());
                    List<String> yAxis = list.stream().map(FoundationChart::getActualGain).collect(Collectors.toList());
                    return new FoundationChartOption(key, name, xAxis, yAxis);
                }).collect(Collectors.toList());
        LOGGER.info("Get data success {}", result);
        return GSON.toJson(result);
    }

    private FoundationChart convert(Foundation foundation) {
        try {
            String[] split = foundation.getActualValue().split(" ");
            String value = split[0];
            String date = split[1].replace(")", EMPTY_STRING).replace("(", EMPTY_STRING);
            return new FoundationChart(
                    foundation.getCode(),
                    foundation.getName(),
                    date,
                    value,
                    foundation.getActualGain().split(" ")[0].replace("%", EMPTY_STRING)
            );
        } catch (Exception e) {
            return null;
        }
    }
}
