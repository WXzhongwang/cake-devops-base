// src/wrappers/auth.tsx
import React, { useEffect, useState } from "react";
import { connect, Outlet, useLocation } from "umi";
import { history } from "umi";
import { UserRoleMenuDTO } from "@/models/user";
import { checkPermission } from "@/utils/permission";

interface AuthProps {
  menu: UserRoleMenuDTO;
}

const AuthWrapper: React.FC<AuthProps> = ({ menu }) => {
  const location = useLocation();
  const [hasPermission, setHasPermission] = useState(false);

  console.log("url", location.pathname);
  console.log("menu", menu.menuTree, hasPermission);

  useEffect(() => {
    const isPermitted = checkPermission(location.pathname, menu);
    console.log("isPermitted", isPermitted);
    setHasPermission(isPermitted);
    if (!isPermitted && location.pathname !== "/403") {
      history.push("/403");
    }
  }, [location.pathname, menu]);

  return hasPermission ? <Outlet /> : null;
};

export default connect(
  ({
    user,
  }: {
    user: {
      menu: UserRoleMenuDTO;
    };
  }) => ({
    menu: user.menu,
  })
)(AuthWrapper);
