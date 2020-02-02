package investment.foundation.dao;

import investment.foundation.modal.Foundation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface FoundationMapper {
    @Select("select" +
            "    date," +
            "    code," +
            "    name," +
            "    estimatedValue," +
            "    estimatedGain," +
            "    actualValue," +
            "    actualGain," +
            "    accumulativeValue," +
            "    gainWithinWeek," +
            "    gainWithinMonth," +
            "    gainWithinThreeMonth," +
            "    gainWithinSixMonth," +
            "    rankWithinWeek," +
            "    rankWithinMonth," +
            "    rankWithinThreeMonth," +
            "    rankWithinSixMonth " +
            " from foundation" +
            " order by date, code desc")
    List<Foundation> allFoundations();

    @Select("select * from foundation where code = #{code} and date= #{date}")
    Foundation getFoundation(String code, String date);

    @Update("" +
            "    update foundation set" +
            "    code = #{code}," +
            "    date = #{date}" +
            "    name = #{name}," +
            "    estimatedValue = #{estimatedValue}," +
            "    estimatedGain = #{estimatedGain}," +
            "    actualValue =  #{actualValue}," +
            "    actualGain = #{actualGain},," +
            "    accumulativeValue = #{accumulativeValue}," +
            "    gainWithinWeek = #{gainWithinWeek}," +
            "    gainWithinMonth = #{gainWithinMonth}," +
            "    gainWithinThreeMonth =#{gainWithinThreeMonth}," +
            "    gainWithinSixMonth = #{gainWithinSixMonth}," +
            "    rankWithinWeek = #{rankWithinWeek}," +
            "    rankWithinMonth = #{rankWithinMonth}," +
            "    rankWithinThreeMonth = #{rankWithinThreeMonth}," +
            "    rankWithinSixMonth = #{rankWithinSixMonth}" +
            "    where code = #{code} " +
            "          and date = #{date}"
    )
    void updateFoundation(Foundation foundation);

    @Insert("" +
            "    insert into foundation(" +
            "    code," +
            "    date," +
            "    name," +
            "    estimatedValue," +
            "    estimatedGain," +
            "    actualValue," +
            "    actualGain," +
            "    accumulativeValue," +
            "    gainWithinWeek," +
            "    gainWithinMonth," +
            "    gainWithinThreeMonth," +
            "    gainWithinSixMonth," +
            "    rankWithinWeek," +
            "    rankWithinMonth," +
            "    rankWithinThreeMonth," +
            "    rankWithinSixMonth" +
            "  ) values(" +
            "    #{code}, " +
            "    #{date}, " +
            "    #{name}, " +
            "    #{estimatedValue}, " +
            "    #{estimatedGain}, " +
            "    #{actualValue}, " +
            "    #{actualGain}, " +
            "    #{accumulativeValue}, " +
            "    #{gainWithinWeek}, " +
            "    #{gainWithinMonth}, " +
            "    #{gainWithinThreeMonth}, " +
            "    #{gainWithinSixMonth}, " +
            "    #{rankWithinWeek}, " +
            "    #{rankWithinMonth}, " +
            "    #{rankWithinThreeMonth}, " +
            "    #{rankWithinSixMonth} " +
            "    )" +
            "    on duplicate key update " +
            "       code = values(code)," +
            "       date = values(date) ")
    void addFoundation(Foundation foundation);

}
