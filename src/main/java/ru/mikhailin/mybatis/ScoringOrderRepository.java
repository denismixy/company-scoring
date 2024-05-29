package ru.mikhailin.mybatis;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import ru.mikhailin.entity.ScoringOrder;

@Mapper
public interface ScoringOrderRepository {
    @Insert("INSERT INTO SCORING_ORDERS(INN, REGION, CAPITAL) VALUES (#{inn}, #{region}, #{capital})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "ID")
    void insertScoringOrder(ScoringOrder scoringOrder);
}
