package com.BFF_paye_ton_kawa.Utils;

import java.util.List;

public class JsonSingleResponse<T> {
   private DataContent<T> data;

   public DataContent<T> getData() {
      return data;
   }

   public void setData(DataContent<T> data) {
      this.data = data;
   }
}
