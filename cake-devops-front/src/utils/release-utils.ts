export const getReleaseStatusText = (status: string) => {
  switch (status) {
    case "AWAIT_APPROVAL":
      return "审批中";
    case "READY":
      return "待发布";
    case "PENDING":
      return "发布中";
    case "FINISHED":
      return "已完成";
    case "FAILED":
      return "发布失败";
    case "CLOSED":
      return "已关闭";
    default:
      return "未知状态";
  }
};

export const getDeployStatusText = (status: string) => {
  switch (status) {
    case "0":
      return "发布中";
    case "1":
      return "发布失败";
    case "2":
      return "发布成功";
    default:
      return "未知状态";
  }
};

export const mapRoleToChinese = (role: string) => {
  switch (role) {
    case "OWNER":
      return "拥有者";
    case "DEVELOPER":
      return "开发";
    case "TESTER":
      return "测试";
    case "OPERATOR":
      return "运维";
    case "ARCHITECT":
      return "架构师";
    case "REPORTER":
      return "告警接收";
    case "CHECKER":
      return "部署审批";
    default:
      return role;
  }
};

export const getApprovalStatusText = (status: string) => {
  switch (status) {
    case "PENDING":
      return "审批中";
    case "APPROVED":
      return "已同意";
    case "AUTO_APPROVED":
      return "免批通过";
    case "REPEALED":
      return "已撤销";
    case "REJECTED":
      return "已驳回";
    default:
      return "未知状态";
  }
};
