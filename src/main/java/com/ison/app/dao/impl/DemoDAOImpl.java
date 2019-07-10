package com.ison.app.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ison.app.constants.AppicationConstants;
import com.ison.app.dao.DemoDAO;

@Repository
public class DemoDAOImpl implements DemoDAO {

  @PersistenceContext(unitName = AppicationConstants.FIRST_PERSISTENCE_UNIT_NAME)
  public EntityManager firstEntityManager;

  @Autowired
  @Qualifier(AppicationConstants.FIRST_JDBC_TEMPLATE)
  JdbcTemplate firstJdbcTemplate;

  private Logger logger = Logger.getLogger(DemoDAOImpl.class);

  @Override
  public List<?> getDataList() throws Exception {
    List<String> employee = new ArrayList<>();
    employee.add("Balaji");
    employee.add("Vijay");
    employee.add("Prega");
    employee.add("Bala");
    employee.add("Sanjay");
    return employee;
  }

  @SuppressWarnings("unchecked")
  public Map<String, String> getStateMap() {
    final Map<String, String> stateMap;
    StringBuilder sqlQry = null;
    List<Object[]> resultList = null;
    try {
      sqlQry = new StringBuilder("SELECT USERNAME,EMPLOYEEID FROM EMPLOYEE ORDER BY EMPLOYEEID");
      Query queryObj = firstEntityManager.createNativeQuery(sqlQry.toString());
      resultList = queryObj.getResultList();
      stateMap = new LinkedHashMap<>();
      if (resultList != null && !resultList.isEmpty()) {
        resultList.forEach(stateObj -> stateMap.put(stateObj[1].toString(), stateObj[0].toString()));
      }
    } catch (Exception e) {
      logger.info("Error :: DemoDAOImpl :: getStateMap() : " + e);
      throw e;
    } finally {
      firstEntityManager.close();
    }
    return stateMap;
  }


}
