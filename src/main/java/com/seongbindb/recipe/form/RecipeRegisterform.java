package com.seongbindb.recipe.form;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * <pre>
 * 레시피 등록 수정을 할때 폼으로 받고 폼 형식으로 사용자의 정보를 담기 위한 DTO
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:38:02
 * @Version : 1.0
 */
@Data
@ToString
public class RecipeRegisterform {
    private Integer recipeNo;
    private String recipeName;
    private String categoryName;
    private List<Integer> recipeDetailNo;
    private List<Integer> stepIndex;
    private List<String> content;
    private List<MultipartFile> img;
}
