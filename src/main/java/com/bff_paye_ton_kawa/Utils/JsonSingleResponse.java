package com.bff_paye_ton_kawa.Utils;

public class JsonSingleResponse<T> {
   private DataContent<T> data;

   public DataContent<T> getData() {
      return data;
   }

   public void setData(DataContent<T> data) {
      this.data = data;
   }
}
