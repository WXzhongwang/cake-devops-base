package com.rany.cake.devops.base.api.query.webhook;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WebhookPageQuery extends BaseQuery {
    private String name;
    private String url;
}
