import React, { useState } from "react";
import { Collapse, Table, Button, Space } from "antd";
import { ColumnsType } from "antd/lib/table";

interface ConfigPanelProps<T extends object> {
  configType: string;
  columns: ColumnsType<T>;
  initialData: T[];
  onSubmit: (data: T[]) => void;
  panelKey: number;
}

const ConfigPanel = <T extends object>({
  configType,
  columns,
  initialData,
  onSubmit,
  panelKey,
}: ConfigPanelProps<T>): JSX.Element => {
  const [data, setData] = useState<T[]>(initialData);

  console.log("configType", configType, data);

  const handleAdd = (newItem: T) => {
    setData([...data, newItem]);
  };

  const handleDelete = (index: number) => {
    const newData = [...data];
    newData.splice(index, 1);
    setData(newData);
  };

  const handleEdit = (index: number, updatedItem: T) => {
    const newData = [...data];
    newData[index] = updatedItem;
    setData(newData);
  };

  return (
    <Collapse.Panel header={configType} key={panelKey}>
      <Table<T>
        dataSource={data}
        columns={columns}
        rowKey="id"
        size="small"
        style={{ marginBottom: 16 }}
      />

      <Space style={{ marginBottom: 16 }}>
        <Button onClick={() => onSubmit(data)}>提交</Button>
      </Space>
    </Collapse.Panel>
  );
};

export default ConfigPanel;
