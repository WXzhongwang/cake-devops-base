ALTER TABLE cake_devops.app_env ADD config_map varchar(2000) NULL;
ALTER TABLE cake_devops.app_env ADD ingress_name varchar(256) NULL;
ALTER TABLE cake_devops.app_env ADD service_name varchar(256) NULL;
ALTER TABLE cake_devops.app_env ADD deployment_name varchar(256) NULL;
ALTER TABLE cake_devops.app_env ADD custom_build_script varchar(256) NULL;
