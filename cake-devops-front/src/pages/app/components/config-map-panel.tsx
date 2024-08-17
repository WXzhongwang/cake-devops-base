import React from "react";
import { ColumnsType } from "antd/lib/table";
import ConfigPanel from "./config-table-panel";

interface ConfigMapItem {
  id: string;
  key: string;
  value: string;
  editable?: boolean;
}

interface ConfigMapConfigPanelProps {
  initialConfigMap: ConfigMapItem[];
  panelKey: number;
}

const configMapColumns: ColumnsType<ConfigMapItem> = [
  { title: "Name", dataIndex: "name", key: "name" },
  { title: "Value", dataIndex: "value", key: "value" },
];

const ConfigMapConfigPanel: React.FC<ConfigMapConfigPanelProps> = ({
  initialConfigMap,
  panelKey,
}) => {
  const handleConfigMapSubmit = (data: ConfigMapItem[]) => {
    console.log("configMap submitted:", data);
  };

  return (
    <ConfigPanel<ConfigMapItem>
      configType="配置项"
      columns={configMapColumns}
      initialData={initialConfigMap}
      onSubmit={handleConfigMapSubmit}
      panelKey={panelKey}
    />
  );
};

export default ConfigMapConfigPanel;
