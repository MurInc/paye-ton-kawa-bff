package com.BFF_paye_ton_kawa.Utils;

import java.util.List;

public class JsonMultipleResponse<T> {
   private int page;
    private int total_pages;
    private int total_users;
   private List<DataContent<T>> data;

   public List<DataContent<T>> getData() {
      return data;
   }

   public void setData(List<DataContent<T>> data) {
      this.data = data;
   }

   public int getPage() {
      return page;
   }

   public void setPage(int page) {
      this.page = page;
   }

   public int getTotal_pages() {
      return total_pages;
   }

   public void setTotal_pages(int total_pages) {
      this.total_pages = total_pages;
   }

   public int getTotal_users() {
      return total_users;
   }

   public void setTotal_users(int total_users) {
      this.total_users = total_users;
   }
}
