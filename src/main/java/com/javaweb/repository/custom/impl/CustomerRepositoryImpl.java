package com.javaweb.repository.custom.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.Status;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public void queryJoin(CustomerSearchRequest customerRequest, StringBuilder sql) {
        Long staffId = customerRequest.getStaffId();
        if ( staffId != null ) {
            sql.append(" JOIN assignmentcustomer asm ON asm.customerid = c.id ");
        }
    }

    public void queryWhereNormal(CustomerSearchRequest customerRequest, StringBuilder where){
        try {
            // Reflection
            Field[] fields = CustomerSearchRequest.class.getDeclaredFields();
            // Array of field in CustomerSearchRequest
            for (Field item : fields) {
                // give permission to use reflection each item
                item.setAccessible(true);
                // get the field name
                String fieldName = item.getName();
                if (!fieldName.equals("staffId")) {
                    // get the value of the field name
                    Object value = item.get(customerRequest);
                    if (value != null ) {
                        if (!value.toString().equals("")){
                            if (item.getType().getName().equals("java.lang.Long")) {
                                where.append(" AND c." + fieldName.toLowerCase() + " = " + value);
                            } else if (item.getType().getName().equals("java.lang.Double")){
                                where.append(" AND c." + fieldName.toLowerCase() + " = " + value);
                            } else if (item.getType().getName().equals("java.lang.String")) {
                                where.append(" AND c." + fieldName.toLowerCase() + " LIKE '%" + value + "%' ");
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void queryWhereSpecial(CustomerSearchRequest customerRequest, StringBuilder where) {
        Long staffId = customerRequest.getStaffId();
        if ( staffId != null ) {
            where.append(" AND asm.staffid = " + staffId);
        }
    }


    @Override
    public List<CustomerEntity> searchByField(CustomerSearchRequest customerRequest) {
        StringBuilder sql = new StringBuilder("SELECT c.* FROM customer c");
        // chuyển từ CHUA_XU_LI sang "Chưa xử lí" và tương tự
        customerRequest.setStatus(Status.getValueByKey(customerRequest.getStatus()));
        queryJoin(customerRequest, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 AND is_active = 1 ");
        // where statement
        queryWhereNormal(customerRequest, where);
        queryWhereSpecial(customerRequest, where);
        sql.append(where);
        sql.append(" GROUP BY c.id ");
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItems(CustomerSearchRequest customerRequest) {
        return searchByField(customerRequest).size();
    }
}
