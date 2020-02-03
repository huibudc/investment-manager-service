package investment.config;

import investment.foundation.dao.InvestFoundationMapper;
import investment.foundation.modal.InvestFoundation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static investment.utils.Utils.GSON;

@Component
@Slf4j
public class DBConfigLoader {
    public final static Map<String, String> FOUNDATIONS = new ConcurrentHashMap<>();
    private final InvestFoundationMapper investFoundationMapper;

    @Autowired
    public DBConfigLoader(InvestFoundationMapper investFoundationMapper) {
        this.investFoundationMapper = investFoundationMapper;
    }

    public void loadInvestFoundation() {
        try {
            log.info("Start to load invest foundations from DB");
            List<InvestFoundation> investFoundations = investFoundationMapper.allInvestFoundations();
            investFoundations.forEach(investFoundation -> FOUNDATIONS.put(investFoundation.getCode(), investFoundation.getName()));
            log.info("Success to load invest foundations from DB, they are:  {}", GSON.toJson(investFoundations));
        } catch (Exception e) {
            log.info("Failed to load invest foundations from DB");
            e.printStackTrace();
        }
    }

    public Map<String, String> investFoundations(){
        if (FOUNDATIONS.isEmpty()) {
            loadInvestFoundation();
        }
        return FOUNDATIONS;
    }

}
