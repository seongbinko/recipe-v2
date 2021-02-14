package com.seongbindb.recipe.form;

import lombok.*;

/**
 * <pre>
 * 메인 화면의 검색, 정렬, 분류, 페이징 기능을 담당할 객체
 *
 * </pre>
 *
 *
 * @Company : SeongbinDB
 * @Author  : Seongbin Ko
 * @Date    : 2021. 2. 14. 오후 7:33:24
 * @Version : 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Searchform {
    private Integer pageNo;
    private String keyword;
    private Integer categoryNo;
    private String orderBy;
    private Integer beginIndex;
    private Integer endIndex;
}
