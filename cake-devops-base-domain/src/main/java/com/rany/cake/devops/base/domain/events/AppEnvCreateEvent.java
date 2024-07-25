package com.rany.cake.devops.base.domain.events;

import com.cake.framework.common.event.DomainEvent;
import com.rany.cake.devops.base.domain.pk.AppId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppEnvCreateEvent extends DomainEvent {

    private final AppId appId;
    private final String envId;

    public AppEnvCreateEvent(AppId appId, String envId) {
        this.appId = appId;
        this.envId = envId;
    }
}
