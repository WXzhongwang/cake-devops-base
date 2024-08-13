alter table app_env
    add env_vars varchar(2000) null after config_map;

