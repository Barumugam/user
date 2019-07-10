package com.ison.app.dao;

import java.util.List;
import java.util.Map;

public interface DemoDAO {

  public List<?> getDataList() throws Exception;

  public Map<String, String> getStateMap() throws Exception;

}
