package investment.foundation.dao;

import investment.foundation.modal.InvestFoundation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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

    @Insert("" +
            "    insert into invest_foundation(" +
            "    code," +
            "    name" +
            "  ) values(" +
            "    #{code}, " +
            "    #{name} " +
            "    )" +
            "    on duplicate key update " +
            "       code = values(code)," +
            "       date = values(name) ")
    void saveOrUpdateFoundation(String code, String name);

    @Delete("" +
            "    delete from invest_foundation" +
            "    where code = #{code} " +
            "    and name = #{name} ")
    void deleteFoundation(String code, String name);
}
