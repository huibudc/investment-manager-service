package investment.foundation;

import investment.foundation.dao.FoundationMapper;
import investment.foundation.modal.Foundation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static investment.utils.Utils.GSON;

@RestController
public class FoundationController {
//    private final FoundationMapper foundationMapper;
//
//    @Autowired
//    public FoundationController(FoundationMapper foundationMapper) {
//        this.foundationMapper = foundationMapper;
//    }
//
//    @GetMapping("/foundation")
//    public String allFoundations() {
//        List<Foundation> list = foundationMapper.allFoundations();
//        return GSON.toJson(list);
//    }
}
