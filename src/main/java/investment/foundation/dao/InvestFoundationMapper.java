package investment.foundation.dao;

import investment.foundation.modal.InvestFoundation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InvestFoundationMapper {
    @Select("select distinct " +
            "    code," +
            "    name" +
            " from invest_foundation")
    List<InvestFoundation> allInvestFoundations();
}
