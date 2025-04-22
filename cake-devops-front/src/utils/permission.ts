// src/utils/permission.ts
import { MenuTreeDTO, UserRoleMenuDTO } from "@/models/user";

export const checkPermission = (
  path: string,
  menu: UserRoleMenuDTO
): boolean => {
  const hasPermission = (menuTree: MenuTreeDTO[]): boolean => {
    console.log("compare path", path, menuTree);
    return menuTree?.some((item) => {
      if (item.path === path) {
        return true;
      }
      if (item.children) {
        return hasPermission(item.children);
      }

      return false;
    });
  };

  return hasPermission(menu.menuTree);
};
