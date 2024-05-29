package ru.mikhailin.mybatis;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import ru.mikhailin.entity.ScoringOrder;

import java.util.List;

@Mapper
public interface ScoringOrderRepository {
    @Insert("INSERT INTO SCORING_ORDERS(INN, REGION, CAPITAL) VALUES (#{inn}, #{region}, #{capital})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "ID")
    Integer insertScoringOrder(ScoringOrder scoringOrder);

    @Select("SELECT * FROM SCORING_ORDERS WHERE ID = ")
    List<ScoringOrder> getOrders();
}
