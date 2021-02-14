package com.seongbindb.recipe.service;

import com.seongbindb.recipe.mapper.CategoryMapper;
import com.seongbindb.recipe.vo.RecipeCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 레시피 카테고리와 관련된 로직을 처리하는 Service 구현체
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:35:41
 * @Version : 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public RecipeCategory getCategoryByName(String categoryName) {
        return categoryMapper.selectCategoryByName(categoryName);
    }

}
