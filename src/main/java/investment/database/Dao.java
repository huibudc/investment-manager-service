package investment.database;

import com.google.gson.Gson;
import investment.database.model.InvestFoundation;
import investment.foundation.modal.Foundation;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static investment.utils.Utils.convertToYYYY_MM_DD;

@Slf4j
public class Dao {
    private final static String INSERT_FOUNDATIONS = """
            insert into foundation (
               code ,
                   date ,
                   name,
                   estimatedValue ,
                   estimatedGain,
                   actualValue,
                   actualGain ,
                   accumulativeValue,
                   gainWithinWeek,
                   gainWithinMonth,
                   gainWithinThreeMonth,
                   gainWithinSixMonth,
                   rankWithinWeek,
                   rankWithinMonth,
                   rankWithinThreeMonth,
                   rankWithinSixMonth
            ) values (
                 ? ,
                   ? ,
                   ?,
                   ? ,
                   ?,
                   ?,
                   ? ,
                   ?,
                   ?,
                   ?,
                   ?,
                   ?,
                   ?,
                   ?,
                   ?,
                   ?
            )
            """;


    public List<InvestFoundation> investFoundations() {
        List<InvestFoundation> investFoundations = new ArrayList<>();
        try (Connection connection = DbUtils.connection();
             Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery("select * from invest_foundation");
            while (resultSet.next()) {
                investFoundations.add(new InvestFoundation(resultSet.getString("code"), resultSet.getString("name")));
            }
            return investFoundations;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return investFoundations;
        }
    }

    public List<Foundation> foundations() {
        List<Foundation> foundations = new ArrayList<>();
        try (Connection connection = DbUtils.connection();
             Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery("select * from foundation");
            while (resultSet.next()) {
                String code = resultSet.getString("code");
                String date = resultSet.getString("date");
                String name = resultSet.getString("name");
                String estimatedValue = resultSet.getString("estimatedValue");
                String estimatedGain = resultSet.getString("estimatedGain");
                String actualValue = resultSet.getString("actualValue");
                String actualGain = resultSet.getString("actualGain");
                String accumulativeValue = resultSet.getString("accumulativeValue");
                String gainWithinWeek = resultSet.getString("gainWithinWeek");
                String gainWithinMonth = resultSet.getString("gainWithinMonth");
                String gainWithinThreeMonth = resultSet.getString("gainWithinThreeMonth");
                String gainWithinSixMonth = resultSet.getString("gainWithinSixMonth");
                String rankWithinWeek = resultSet.getString("rankWithinWeek");
                String rankWithinMonth = resultSet.getString("rankWithinMonth");
                String rankWithinThreeMonth = resultSet.getString("rankWithinThreeMonth");
                String rankWithinSixMonth = resultSet.getString("rankWithinSixMonth");
                Foundation foundation = new Foundation();
                foundation.setCode(code);
                foundation.setDate(date);
                foundation.setName(name);
                foundation.setDate(convertToYYYY_MM_DD(estimatedValue));
                foundation.setEstimatedValue(estimatedValue);
                foundation.setEstimatedGain(estimatedGain);
                foundation.setActualValue(actualValue);
                foundation.setActualGain(actualGain);
                foundation.setAccumulativeValue(accumulativeValue);
                foundation.setGainWithinWeek(gainWithinWeek);
                foundation.setGainWithinMonth(gainWithinMonth);
                foundation.setGainWithinThreeMonth(gainWithinThreeMonth);
                foundation.setGainWithinSixMonth(gainWithinSixMonth);
                foundation.setRankWithinWeek(rankWithinWeek);
                foundation.setRankWithinMonth(rankWithinMonth);
                foundation.setRankWithinThreeMonth(rankWithinThreeMonth);
                foundation.setRankWithinSixMonth(rankWithinSixMonth);
                foundations.add(foundation);
            }
            return foundations;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return foundations;
        }
    }

    public int addFoundationsInfo(List<Foundation> foundations) {
        if (foundations == null || foundations.isEmpty()) return 0;
        try (Connection connection = DbUtils.connection();
             PreparedStatement statement = connection.prepareStatement(INSERT_FOUNDATIONS);) {
            for (Foundation foundation : foundations) {
                statement.setString(1, foundation.getCode());
                statement.setString(2, foundation.getDate());
                statement.setString(3, foundation.getName());
                statement.setString(4, foundation.getEstimatedValue());
                statement.setString(5, foundation.getEstimatedGain());
                statement.setString(6, foundation.getActualValue());
                statement.setString(7, foundation.getActualGain());
                statement.setString(8, foundation.getAccumulativeValue());
                statement.setString(9, foundation.getGainWithinWeek());
                statement.setString(10, foundation.getGainWithinMonth());
                statement.setString(11, foundation.getGainWithinThreeMonth());
                statement.setString(12, foundation.getGainWithinSixMonth());
                statement.setString(13, foundation.getRankWithinWeek());
                statement.setString(14, foundation.getRankWithinMonth());
                statement.setString(15, foundation.getRankWithinThreeMonth());
                statement.setString(16, foundation.getRankWithinSixMonth());
                statement.addBatch();
            }
            return statement.executeBatch().length;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return 0;
        }
    }


    public static void main(String[] args) {
        System.out.println(new Gson().toJson(new Dao().investFoundations()));
    }

}
