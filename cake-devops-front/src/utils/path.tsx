// utils.ts

export interface PathItem {
  name: string;
  path: string;
}

export function isEmptyStr(value: string): boolean {
  return value.trim().length === 0;
}

export function getPathAnalysis(
  analysisPath: string,
  paths: PathItem[] = []
): PathItem[] {
  const lastSymbol = analysisPath.lastIndexOf("/");
  if (lastSymbol === -1) {
    paths.unshift({
      name: "/",
      path: "/",
    });
    return paths;
  }
  const name = analysisPath.substring(lastSymbol, analysisPath.length);
  if (!isEmptyStr(name) && name !== "/") {
    paths.unshift({
      name: name.substring(1, name.length),
      path: analysisPath,
    });
  }
  return getPathAnalysis(analysisPath.substring(0, lastSymbol), paths);
}

/**
 * Normalize the given path by replacing backslashes with forward slashes
 * and removing any duplicate slashes.
 *
 * @param {string} path - The path to normalize.
 * @returns {string} - The normalized path.
 */
export function getPath(path: string): string {
  return path.replace(/\\+/g, "/").replace(/\/+/g, "/");
}

/**
 * Get the parent directory of the given path.
 *
 * @param {string} path - The path from which to get the parent directory.
 * @returns {string} - The parent directory path.
 */
export function getParentPath(path: string): string {
  const paths = getPath(path).split("/");
  const len = paths.length;
  if (len <= 2) {
    return "/";
  }
  let parent = "";
  for (let i = 0; i < len - 1; i++) {
    parent += paths[i];
    if (i !== len - 2) {
      parent += "/";
    }
  }
  return parent;
}
