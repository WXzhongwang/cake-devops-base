import { defineMock } from "umi";
import { AppInfo } from "@/models/app";
import Mock from "mockjs";

const { Random } = Mock;

const generateAppInfo = (id: number) => ({
  id: id.toString(),
  appName: Random.word(),
  repo: Random.word(),
  defaultBranch: Random.pick(["master", "develop", "feature"]),
  departmentAbbreviation: Random.word(),
  department: Random.word(),
  language: Random.pick(["JAVA", "Python", "Go"]),
  description: Random.sentence(4, 6),
  developMode: Random.pick(["Freedom", "Branch", "GitFlow"]),
  owner: Random.word(),
  healthCheck: "/ok",
  status: Random.pick(["启用", "停用"]),
});

const totalApps = 100; // 假设总共有 100 条应用数据

const appList = Array.from({ length: totalApps }, (_, index) =>
  generateAppInfo(index + 1)
);

export default {
  "POST /api/devops/app/pageApp": (req: Request, res: Response) => {
    const { pageNo = 1, pageSize = 10 } = req.body;
    const start = (pageNo - 1) * pageSize;
    const end = start + pageSize;
    const result = {
      list: appList.slice(start, end),
      total: totalApps,
    };
    res.json(result);
  },
};
