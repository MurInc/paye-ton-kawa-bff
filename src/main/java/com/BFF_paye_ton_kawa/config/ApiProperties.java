    package com.BFF_paye_ton_kawa.config;

    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.boot.context.properties.EnableConfigurationProperties;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.stereotype.Component;

    @Component
    @ConfigurationProperties(prefix = "external.apis")
    public class ApiProperties {
        private String productUrl;
        private String clientUrl;
        private String orderUrl;

        public String getProductUrl() {
            return productUrl;
        }

        public void setProductUrl(String productUrl) {
            this.productUrl = productUrl;
        }

        public String getClientUrl() {
            return clientUrl;
        }

        public void setClientUrl(String userUrl) {
            this.clientUrl = userUrl;
        }

        public String getOrderUrl() {
            return orderUrl;
        }

        public void setOrderUrl(String orderUrl) {
            this.orderUrl = orderUrl;
        }
    }
