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
            "    a.date," +
            "    a.code," +
            "    a.name," +
            "    a.estimatedValue," +
            "    a.estimatedGain," +
            "    a.actualValue," +
            "    a.actualGain," +
            "    a.accumulativeValue," +
            "    a.gainWithinWeek," +
            "    a.gainWithinMonth," +
            "    a.gainWithinThreeMonth," +
            "    a.gainWithinSixMonth," +
            "    a.rankWithinWeek," +
            "    a.rankWithinMonth," +
            "    a.rankWithinThreeMonth," +
            "    a.rankWithinSixMonth " +
            " from foundation a " +
            " join (select distinct date from foundation order by date desc limit 5) as b on a.date = b.date " +
            " order by a.date desc, a.code desc")
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
            "       date = values(date)," +
            "       name = values(name)," +
            "       estimatedValue = values(estimatedValue)," +
            "       estimatedGain = values(estimatedGain)," +
            "       actualValue = values(actualValue)," +
            "       accumulativeValue = values(accumulativeValue)," +
            "       gainWithinWeek = values(gainWithinWeek)," +
            "       gainWithinMonth = values(gainWithinMonth)," +
            "       gainWithinThreeMonth = values(gainWithinThreeMonth)," +
            "       gainWithinSixMonth = values(gainWithinSixMonth)," +
            "       rankWithinWeek = values(rankWithinWeek)," +
            "       rankWithinMonth = values(rankWithinMonth)," +
            "       rankWithinThreeMonth = values(rankWithinThreeMonth)," +
            "       rankWithinSixMonth = values(rankWithinSixMonth)")
    void saveOrUpdateFoundation(Foundation foundation);

}
