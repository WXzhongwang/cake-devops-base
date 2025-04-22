// src/pages/app/components/ReleaseDetailsDrawer.tsx
import React from "react";
import { Drawer, Space, Descriptions, Button, Typography } from "antd";
import { ReleaseRecord } from "@/models/release";
import dayjs from "dayjs";
import {
  getReleaseStatusText,
  getDeployStatusText,
  getApprovalStatusText,
} from "@/utils/release-utils";

const { Paragraph } = Typography;
const { Item } = Descriptions;

interface ReleaseDetailsDrawerProps {
  visible: boolean;
  onClose: () => void;
  release: ReleaseRecord | null;
}

const ReleaseDetailsDrawer: React.FC<ReleaseDetailsDrawerProps> = ({
  visible,
  onClose,
  release,
}) => {
  if (!release) return null;

  return (
    <Drawer title="发布单详情" width={600} onClose={onClose} open={visible}>
      <Space style={{ width: "100%" }} direction="vertical" size="small">
        <Descriptions
          labelStyle={{ width: "160px" }}
          column={1}
          bordered
          title="基础信息"
        >
          <Item label="发布单号">
            <Paragraph
              copyable={{ tooltips: ["点击复制", "复制成功"] }}
              style={{ display: "inline" }}
            >
              {release.releaseNo}
            </Paragraph>
          </Item>
          <Item label="预计发布时间">
            {dayjs(release?.releaseDate).format("YYYY-MM-DD HH:mm:ss")}
          </Item>
          <Item label="发布分支">{release.releaseBranch}</Item>
          <Item label="发布版本">{release.releaseVersion}</Item>
          <Item label="创建时间">
            {dayjs(release?.gmtCreate).format("YYYY-MM-DD HH:mm:ss")}
          </Item>
          <Item label="更新时间">
            {dayjs(release?.gmtModified).format("YYYY-MM-DD HH:mm:ss")}
          </Item>
          <Item label="发布状态">
            {getReleaseStatusText(release.releaseStatus)}
          </Item>
          {/* 其他字段按照需要添加 */}
        </Descriptions>

        {/* 审批详情 */}
        {release.approvalDTO && (
          <Descriptions
            labelStyle={{ width: "160px" }}
            column={1}
            bordered
            title="审批单详情"
          >
            <Item label="发布单号">
              <Paragraph
                copyable={{ tooltips: ["点击复制", "复制成功"] }}
                style={{ display: "inline" }}
              >
                {release.approvalDTO.approvalId}
              </Paragraph>
            </Item>
            <Item label="发起时间">
              {dayjs(release.approvalDTO?.changeDate).format(
                "YYYY-MM-DD HH:mm:ss"
              )}
            </Item>

            <Item label="文档地址">{release.approvalDTO?.docAddress}</Item>
            <Item label="审批状态">
              {getApprovalStatusText(release.approvalDTO.approvalStatus)}
            </Item>
          </Descriptions>
        )}

        <Button onClick={onClose} style={{ marginTop: 16 }}>
          关闭
        </Button>
      </Space>
    </Drawer>
  );
};

export default ReleaseDetailsDrawer;
