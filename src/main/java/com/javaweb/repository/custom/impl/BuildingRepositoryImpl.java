package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public void queryJoin(BuildingSearchRequest buildingRequest, StringBuilder sql) {
        Long areaFrom = buildingRequest.getAreaFrom();
        Long areaTo = buildingRequest.getAreaTo();
        if (areaFrom != null || areaTo != null) {
            sql.append(" JOIN rentarea r ON r.buildingid = b.id ");
        }
        Long staffId = buildingRequest.getStaffId();
        if ( staffId != null ) {
            sql.append(" JOIN assignmentbuilding asm ON asm.buildingid = b.id ");
        }
    }

    public void queryWhereNormal(BuildingSearchRequest buildingRequest, StringBuilder where){
        try {
            // Reflection
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
            // Array of field in BuildingSearchBuilder (like name, street, ward, ...)
            for (Field item : fields) {
                // give permission to use reflection each item
                item.setAccessible(true);
                // get the field name
                String fieldName = item.getName();
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
                        && !fieldName.startsWith("rentPrice")) {
                    // get the value of the field name
                    Object value = item.get(buildingRequest);
                    if (value != null ) {
                        if (!value.toString().equals("")){
                            if (item.getType().getName().equals("java.lang.Long")) {
                                where.append(" AND b." + fieldName.toLowerCase() + " = " + value);
                            } else if (item.getType().getName().equals("java.lang.Double")){
                                where.append(" AND b." + fieldName.toLowerCase() + " = " + value);
                            } else if (item.getType().getName().equals("java.lang.String")) {
                                where.append(" AND b." + fieldName.toLowerCase() + " LIKE '%" + value + "%' ");
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void queryWhereSpecial(BuildingSearchRequest buildingRequest, StringBuilder where) {
        Long staffId = buildingRequest.getStaffId();
        if ( staffId != null ) {
            where.append(" AND asm.staffid = " + staffId);
        }
        Long areaFrom = buildingRequest.getAreaFrom();
        Long areaTo = buildingRequest.getAreaTo();
        if (areaFrom != null) {
            where.append(" AND r.value >= " + areaFrom);
        }
        if (areaTo != null) {
            where.append(" AND r.value <= " + areaTo);
        }
        Long rentPriceFrom = buildingRequest.getRentPriceFrom();
        Long rentPriceTo = buildingRequest.getRentPriceTo();
        if (rentPriceFrom != null) {
            where.append(" AND b.rentprice >= " + rentPriceFrom);
        }
        if (rentPriceTo != null) {
            where.append(" AND b.rentprice <= " + rentPriceTo);
        }
        // Java 8
        if (buildingRequest.getTypeCode() != null && !buildingRequest.getTypeCode().isEmpty()) {
            where.append(" AND (");
            String sqlJoin = buildingRequest.getTypeCode().stream()
                    .collect(Collectors.joining(",", " b.type LIKE " + "'%", "%'" ));
            where.append(sqlJoin + ") ");
        }
    }
    @Override
    public List<BuildingEntity> findByField(BuildingSearchRequest buildingRequest) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b");
        // join statement
        queryJoin(buildingRequest, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        // where statement
        queryWhereNormal(buildingRequest, where);
        queryWhereSpecial(buildingRequest, where);
        sql.append(where);
        sql.append(" GROUP BY b.id ");
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItems(BuildingSearchRequest buildingRequest) {
        return findByField(buildingRequest).size();
    }

}
