    package com.BFF_paye_ton_kawa.config;

    import jakarta.annotation.PostConstruct;
    import jakarta.validation.constraints.NotBlank;
    import org.hibernate.validator.constraints.URL;
    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.boot.context.properties.EnableConfigurationProperties;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.stereotype.Component;
    import org.springframework.validation.annotation.Validated;

    @Component
    @Validated
    @ConfigurationProperties(prefix = "external.apis")
    public class ApiProperties {
        @NotBlank(message = "Product API URL must not be blank")
        @URL(message = "Product API URL must be a valid URL")
        private String productUrl;
        @NotBlank(message = "Client API URL must not be blank")
        @URL(message = "Product API URL must be a valid URL")

        private String clientUrl;
        @NotBlank(message = "Order API URL must not be blank")
        @URL(message = "Product API URL must be a valid URL")

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
