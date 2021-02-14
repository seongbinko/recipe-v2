package com.seongbindb.recipe.crawler;

import com.seongbindb.recipe.utils.ThumbUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

/**
 * <pre>
 * 더미데이터를 만들기 위해서 구현한 크롤링
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 8:51:23
 * @Version : 1.0
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * <pre>
     * 크롤링할 url 범위를 지정한다.
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();

            int i = 6934537;
            int count = 0;
            while (i < 6934538) {

                logger.debug("추출 번호 : " + i);

                run("https://www.10000recipe.com/recipe/" + i);
                i++;
                count++;
            }
            long endTime = System.currentTimeMillis();
            logger.debug("크롤링 수행 갯수:" + count + " 소요시간:" + ((endTime - startTime) / 1000) + "초");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <pre>
     * 크롤링이 동작한다.
     * </pre>
     *
     * @param URL
     * @throws Exception
     */
    private static void run(String URL) throws Exception {

        // 1. Crawler 옵션 설정
        Properties options = new Properties();
        options.put("Content-Type", "application/html;charset=UTF-8");
        options.put("timeout", 30 * 1000);

        // 2. Crawler 생성
        Crawler crawler = new Crawler(URL, options);

        // 3. HTML 파싱
        Document html = crawler.get();

        // 3-1. 정보 추출 (닉네임, 레시피 제목, 대표사진 url)
        String nickName = html.select(".user_info2_name").text();
        String recipeName = html.select(".view2_summary h3").text();
        String mainThumbs = html.getElementById("main_thumbs").attr("src");

        if (nickName.length() > 7) {
            nickName = nickName.substring(0, 7);
        }
        String userId = nickName + (int) (Math.random() * 1000);

        if (recipeName.length() > 15) {
            recipeName = recipeName.substring(0, 15);
        }

        // 4. DB 접속 URL
        String url = "jdbc:mariadb://localhost:3306/recipe?characterEncoding=UTF-8&serverTimezone=UTC";
        Class.forName("org.mariadb.jdbc.Driver");
        Connection con0 = DriverManager.getConnection(url, "root", "0064");
        con0.setAutoCommit(false);
        try {
            // 5-0. 등록된 USER 확인
            String sql0 = "select user_id , user_nickname from recipe_user where user_nickname = ?";
            PreparedStatement pmst0 = con0.prepareStatement(sql0);
            pmst0.setString(1, nickName);
            String checkNickName = "";
            String checkUserId = "";

            ResultSet rs0 = pmst0.executeQuery();
            while (rs0.next()) {
                checkUserId = rs0.getString("user_id");
                checkNickName = rs0.getString("user_nickname");
            }
            try {
                pmst0.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // USER가 존재하지 않을 경우 -> 신규 등록
            if (!checkNickName.equals(nickName)) {
                // 5-1. USER 생성
                String sql1 = "insert into recipe_user(user_id, user_password, user_nickname) values(?,?,?)";
                PreparedStatement pmst1 = con0.prepareStatement(sql1);
                pmst1.setString(1, userId);
                pmst1.setString(2, "zxcv1234!");
                pmst1.setString(3, nickName);
                int r1 = pmst1.executeUpdate();
                logger.debug("변경된 row1 " + r1);
                try {
                    pmst1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // USER가 존재하는 경우 nickName 과 userId setting
            else {
                nickName = checkNickName;
                userId = checkUserId;
            }

            // 5-2. RECIPE 생성
            String sql2 = "insert into recipe(recipe_name, user_id, recipe_category_no) values (?,?,?)";
            PreparedStatement pmst2 = con0.prepareStatement(sql2);
            pmst2.setString(1, recipeName);
            pmst2.setString(2, userId);
            pmst2.setInt(3, 4);
            int r2 = pmst2.executeUpdate();

            logger.debug("변경된 row2 " + r2);
            // 20210211 체크 포인트
            // 5-3. RECIPE DETAIL을 위한 RECIPE_NO 조회
            ResultSet rs2 = pmst2.executeQuery(
                    "select RECIPE_NO from RECIPE order by RECIPE_NO desc limit 1"
            );
            int recipeNo = 0;
            while (rs2.next()) {
                int x = rs2.getInt("recipe_no");
                recipeNo = x;
            }
            rs2.close();
            try {
                pmst2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //5.4 파일 정보 추출 / RECIPE_DETAIL TABLE에 저장 STEP과 LAST (완성사진)
            Elements files = html.select("div[id^='stepDiv']");
            List<Map<String, String>> download_list = new ArrayList<Map<String, String>>();
            Map<String, String> download_info = null;

            // STEP 이미지 및 조리내용 추출
            for (Element file : files) {

                download_info = new HashMap<String, String>();
                String src = file.select("img").attr("src");
                String filename = System.currentTimeMillis() + src.substring(src.length() - 7);

                String sql3 = "insert into recipe_detail(RECIPE_NO, RECIPE_DETAIL_CONTENT, RECIPE_DETAIL_IMG, RECIPE_DETAIL_STEP) values(?, ?, ?, ?)";

                PreparedStatement pmst3 = con0.prepareStatement(sql3);
                pmst3.setInt(1, recipeNo);
                pmst3.setString(2, file.text());
                pmst3.setString(3, filename);
                pmst3.setInt(4, files.indexOf(file));

                int r3 = pmst3.executeUpdate();
                logger.debug("변경된 row3: " + r3);

                download_info.put("filename", filename);
                download_info.put("URL", src);
                download_list.add(download_info);

                try {
                    pmst3.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 대표사진은 따로 추출한다.
            String lastFileName = System.currentTimeMillis() + mainThumbs.substring(mainThumbs.length() - 10);
            download_info.put("filename", lastFileName);
            download_info.put("URL", mainThumbs);
            download_list.add(download_info);
            crawler.downloads(download_list);

            // RECIPE_DETAIL 정보 생성
            String sql4 = "insert into recipe_detail (recipe_no, recipe_detail_img, recipe_detail_step) values(?, ?, ?)";

            PreparedStatement pmst4 = con0.prepareStatement(sql4);
            pmst4.setInt(1, recipeNo);
            pmst4.setString(2, lastFileName);
            pmst4.setInt(3, files.size());
            int r4 = pmst4.executeUpdate();
            logger.debug("변경된 row4: " + r4);

            try {
                pmst4.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // 대표사진의 DB 파일명과 로컬저장 파일명의 불일치로 인한 조치
            String sql5 = "update  \n" +
                    "        recipe_detail\n" +
                    "    set recipe_detail_img = (select\n" +
                    "                                 recipe_detail_img\n" +
                    "                            from recipe_detail \n" +
                    "                            where recipe_detail_no = (select\n" +
                    "                                                        recipe_detail_no\n" +
                    "                                                     from\n" +
                    "                                                        recipe_detail\n" +
                    "                                                     order by recipe_detail_no desc\n" +
                    "                                                     limit 1)\n" +
                    "                            )\n" +
                    "    where\n" +
                    "        recipe_detail_no = (select\n" +
                    "                                recipe_detail_no\n" +
                    "                            from\n" +
                    "                                recipe_detail\n" +
                    "                            order by recipe_detail_no desc\n" +
                    "                            limit 1,1)";

            PreparedStatement pmst5 = con0.prepareStatement(sql5);
            int r5 = pmst5.executeUpdate();
            logger.debug("변경된 row5: " + r5);
            try {
                pmst5.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 대표사진 썸네일 생성
            ThumbUtils.createCrawlerThumbnail("/Users/ko/projects/recipe-v2/src/main/webapp/images/recipe/", lastFileName, 252, 252);
            logger.debug("commit 수행");
            con0.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug("Rollback 수행");
            con0.rollback();
        } finally {
            con0.close();
        }
    }
}