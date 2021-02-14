package com.seongbindb.recipe.crawler;

/**
 * <pre>
 * DEFAULT 값들을 나타낸다.
 * 따로 PROPERTIES를 설정하지 않으면 DEFAULT VALUE 값이 실행된다.
 *
 * </pre>
 *
 *
 * @Company : SeongbinDB
 * @Author  : Seongbin Ko
 * @Date    : 2021. 2. 14. 오전 8:53:36
 * @Version : 1.0
 */
public class Const {

    public final static int DEFAULT_THREADS = 1;
    public final static String DEFAULT_DOWNSLOADS = "/Users/ko/projects/recipe-v2/src/main/webapp/images/recipe/";
    public final static String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36";
    public final static String DEFAULT_CONTENT_TYPE = "application/json;charset=UTF-8";
    public final static int DEFAULT_TIMEOUT = 5 * 1000;
}
