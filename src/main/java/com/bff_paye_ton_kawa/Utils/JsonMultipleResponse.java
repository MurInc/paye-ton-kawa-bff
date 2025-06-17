package com.bff_paye_ton_kawa.Utils;

import java.util.List;

public class JsonMultipleResponse<T> {
   private List<DataContent<T>> data;

   public List<DataContent<T>> getData() {
      return data;
   }

   public void setData(List<DataContent<T>> data) {
      this.data = data;
   }
}
