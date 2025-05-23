package com.BFF_paye_ton_kawa.client;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class PageQuery {
    @NotNull
    @PositiveOrZero
    private int page;

    @NotNull()
    @PositiveOrZero()
    private int limit;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
