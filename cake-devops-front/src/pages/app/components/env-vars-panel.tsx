import React from "react";
import { ColumnsType } from "antd/lib/table";
import ConfigPanel from "./config-table-panel";
import { set } from "lodash";

interface EnvVar {
  id: string;
  key: string;
  value: string;
  editable?: boolean;
}

interface EnvVarConfigPanelProps {
  initialEnvVars: EnvVar[];
  panelKey: number;
}

const envVarColumns: ColumnsType<EnvVar> = [
  { title: "Name", dataIndex: "name", key: "name" },
  { title: "Value", dataIndex: "value", key: "value" },
];

const initialEnvVarsTest: EnvVar[] = [
  { id: "1", key: "API_URL", value: "https://api.example.com" },
  { id: "2", key: "APP_ENV", value: "production" },
];

const EnvVarConfigPanel: React.FC<EnvVarConfigPanelProps> = ({
  initialEnvVars,
  panelKey,
}) => {
  const handleEnvVarSubmit = (data: EnvVar[]) => {
    console.log("Environment Variables submitted:", data);
  };

  return (
    <ConfigPanel<EnvVar>
      configType="环境变量"
      columns={envVarColumns}
      initialData={initialEnvVars}
      onSubmit={handleEnvVarSubmit}
      panelKey={panelKey}
    />
  );
};

export default EnvVarConfigPanel;
