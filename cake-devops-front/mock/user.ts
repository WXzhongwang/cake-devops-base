import { defineMock } from "umi";
import Mock from "mockjs";

export default {
  "GET /api/devops/getUser": (req: Request, res: Response) => {
    const result = {
      success: true,
      code: "200",
      msg: "请求成功",
      content: {
        userId: "123456",
        userName: "hz管理员",
        realName: undefined,
      },
    };
    console.log("user", result);
    res.json(result);
  },
};
