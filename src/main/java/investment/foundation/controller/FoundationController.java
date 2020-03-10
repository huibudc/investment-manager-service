package investment.foundation.controller;

import investment.foundation.modal.FoundationChartOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static investment.utils.Utils.GSON_PRETTY;

@RestController
public class FoundationController {
    private final static Logger LOGGER = LoggerFactory.getLogger(FoundationController.class);
    public static final String EMPTY_STRING = "";

}
