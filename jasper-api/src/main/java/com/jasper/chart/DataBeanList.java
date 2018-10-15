package com.jasper.chart;

import java.util.ArrayList;

public class DataBeanList {
   public ArrayList<DataBean> getDataBeanList() {
      ArrayList<DataBean> dataBeanList = new ArrayList<DataBean>();

      dataBeanList.add(produce(1,10));
      dataBeanList.add(produce(4,20));
      dataBeanList.add(produce(8,30));
      dataBeanList.add(produce(12,45));

      return dataBeanList;
   }

   /**
    * This method returns a DataBean object,
    * with name and country set in it.
    */
   private DataBean produce(Integer date, Integer count) {
      DataBean dataBean = new DataBean();
      dataBean.setDate(date);
     dataBean.setCount(count);
      
      return dataBean;
   }
}
