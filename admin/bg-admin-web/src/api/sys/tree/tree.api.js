import {fetch} from '../../base';


// 根据菜单流水ID来更新菜单数据
export const updateFirstTree = params => {
  return fetch('/tree/updateFirstTree',params);
};

// 根据菜单流水ID来更新菜单数据
export const updateSecondTree = params => {
  return fetch('/tree/updateSecondTree',params);
};

// 根据菜单ID来获取菜单数据
export const getTreeByTreeId = params => {
  return fetch('/tree/getTreeByTreeId',params);
};

// 根据菜单ID来获取菜单数据
export const getTreeByTreeName = params => {
  return fetch('/tree/getTreeByTreeName',params);
};

// 根据菜单ID来获取菜单数据
export const getTreeByCategoryId = params => {
  return fetch('/tree/getTreeByCategoryId',params);
};

// 根据菜单ID来获取菜单数据
export const getTreeByCategoryIdNot0 = params => {
  return fetch('/tree/getTreeByCategoryIdNot0',params);
};

// 删除菜单

export const deleteFirstTree = params => {
  return fetch('/tree/deleteFirstTree',params);
};

// 删除菜单
export const deleteSecondTree = params => {
  return fetch('/tree/deleteSecondTree',params);
};

// 增加菜单
export const addFirstTree = params => {
  return fetch('/tree/addFirstTree',params);
};


// 冻结/解冻按钮
export const operateButton = params => {
  return fetch('/tree/operateButton',params);
};

// 增加按钮
export const addButton = params => {
  return fetch('/tree/addButton',params);
};

// 验证菜单节点是否已经增加过
export const checkTreeCode = params => {
  return fetch('/tree/checkTreeCode',params);
};

// 更新按钮节点
export const updateButton = params => {
  return fetch('/tree/updateButton',params);
};

// 删除按钮节点
export const deleteButton = params => {
  return fetch('/tree/deleteButton',params);
};

// 获取菜单的按钮的列表
export const queryTreeButtonList = params => {
  return fetch('/tree/queryTreeButtonList',params);
};

// 获取菜单树目录
export const getTreeList = params => {
  return fetch('/tree/getTreeList',params);
};
