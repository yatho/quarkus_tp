package org.formation.configuration;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "notification")
public interface NotificationPropertiesMapping {
    String protocol();
    String host();
    int port();
    String baseUrl();
    String uri();
}
