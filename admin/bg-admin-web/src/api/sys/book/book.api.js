import {fetch} from '../../base';

// 验证字典的类型和编码是否重复
// export const checkTypeAndCode = params => {
//   return fetch('/dict/checkTypeAndCode',params);
// };

// 增加数据字典的数据
export const addBook = params => {
  return fetch('/book/saveBookInformation',params);
};

//删除数据字典的数据
export const deleteBook = params => {
  return fetch('/book/deleteBookInformationById',params);
};

// 更新数据字典的数据
export const updateBook = params => {
  return fetch('/book/updateBookInformation',params);
};

// 获取数据字典列表数据
export const queryBookList = params => {
  return fetch('/book/queryBookList',params);
};

export const SearchAllCategoryName = params => {
  return fetch('/book/SearchAllCategoryName',params);
};
