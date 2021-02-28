package com.seongbindb.recipe.service;

import com.seongbindb.recipe.dto.MainRecipesDto;
import com.seongbindb.recipe.form.RecipeRegisterform;
import com.seongbindb.recipe.form.Searchform;
import com.seongbindb.recipe.mapper.CategoryMapper;
import com.seongbindb.recipe.mapper.RecipeMapper;
import com.seongbindb.recipe.utils.FileUtils;
import com.seongbindb.recipe.utils.Pagination;
import com.seongbindb.recipe.utils.ThumbUtils;
import com.seongbindb.recipe.vo.Recipe;
import com.seongbindb.recipe.vo.RecipeCategory;
import com.seongbindb.recipe.vo.RecipeDetail;
import com.seongbindb.recipe.vo.RecipeScrap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  레시피와 관련된 모든 로직을 처리하는 서비스 구현체
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:36:03
 * @Version : 1.0
 */
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {


    private final RecipeMapper recipeMapper;


    private final CategoryMapper categoryMapper;

    /**
     * 레시피 원본 이미지를 저장하는 디렉토리
     */
    @Value("${recipe.image.origin.save}")
    private String recipeImgOriginDirectory;

    /**
     * 레시피 썸네일을 저장하는 디렉토리
     */
    @Value("${recipe.image.thumbnail.save}")
    private String recipeImgThumbnailDirectory;

    public Integer insertRecipe(MultipartHttpServletRequest request, String userId, RecipeRegisterform recipeRegisterform) throws Exception {
        //String test = request.getSession().getServletContext().getRealPath("/");

        //System.getProperty("user.dir");

        //RECIPE TABLE 등록
        Recipe recipe = new Recipe();
        recipe.setRecipeCategoryNo(categoryMapper.selectCategoryByName(recipeRegisterform.getCategoryName()).getRecipeCategoryNo());
        recipe.setRecipeName(recipeRegisterform.getRecipeName());
        recipe.setUserId(userId);
        recipeMapper.insertRecipe(recipe);

        //RECIPE_DETAIL TABLE 등록
        int lastIndex = recipeRegisterform.getImg().size();
        for (int i = 0; i < lastIndex; i++) {

            // 1. request에 있는 file 한 개 가져오기
            MultipartFile imgFile = request.getFiles("img").get(i);

            // 2. 오리지널 파일명으로 고유한 이미지 이름 생성
            String uniqFileName = FileUtils.getUniqueOriginalFileName(recipeRegisterform.getImg().get(i));

            // 3.지정된 디렉토리(recipeImgOriginDirectory)에 이미지 저장
            FileUtils.insertImageFile(imgFile, recipeImgOriginDirectory, uniqFileName);

            // 4. 메인에 사용할 썸네일 이미지 생성 및 지정된 디렉토리 (recipeImgThumbnailDirectory)에 이미지 저장
            BufferedImage thumbImage = ThumbUtils.createThumbnail(imgFile, 252, 252);
            ImageIO.write(thumbImage, "png", new File(recipeImgThumbnailDirectory, uniqFileName));

            // 5. RecipeDetail 객체 생성 담기
            RecipeDetail detail = new RecipeDetail();
            detail.setRecipeDetailImg(uniqFileName);
            detail.setRecipeNo(recipe.getRecipeNo());
            detail.setRecipeDetailStep(i);

            // 마지막 인덱스를 제외한 나머지에 레시피 내용 담기
            // lastIndex는 대표사진 이기 때문에 content가 null
            if (i < lastIndex - 1) {
                detail.setContent(recipeRegisterform.getContent().get(i));
            }

            //6. RECIPE_DETAIL TABLE에 등록
            recipeMapper.insertRecipeDetail(detail);
        }
        // 7. RECIPE 상세정보 이동 위해 레시피 번호 반환
        return recipe.getRecipeNo();
    }

    @Override
    public Map<String, Object> getRecipeByRecipeNo(int recipeNo, String userId) {


        // 1. RECIPE 담기
        Recipe recipe = recipeMapper.selectRecipeByRecipeNo(recipeNo);
        // 2. RECIPE_CATEGORY 담기
        RecipeCategory recipeCategory = categoryMapper.selectRecipeCategoryByCategoryNo(recipe.getRecipeCategoryNo());

        // 3. RECIPE_DETAIL 리스트 담기
        String path = "/images/recipe/";
        List<RecipeDetail> recipeDetails = recipeMapper.selectRecipeDetailsByRecipeNo(recipeNo);
        for (RecipeDetail recipeDetail : recipeDetails) {
            recipeDetail.setRecipeDetailImg(path + recipeDetail.getRecipeDetailImg());
        }
        // 4. RECIPE_SCARP 정보 조회, 스크랩 상태를 알기 위함
        List<RecipeScrap> recipeScraps = recipeMapper.selectRecipeScrapsByRecipeNo(recipeNo);
        boolean scrapStatus = false;
        for (RecipeScrap recipeScrap : recipeScraps) {
            if (recipeScrap.getUserId().equals(userId)) {
                scrapStatus = true;
            }
        }
        // 5. 글 작성자인지, 일반 유저인지 구분하기 위함
        String role = recipe.getUserId().equals(userId) ? "author" : "guest";

        // 6. Map에 담기
        Map<String, Object> recipeInfo = new HashMap<String, Object>();
        recipeInfo.put("recipe", recipe);
        recipeInfo.put("recipeCategory", recipeCategory);
        recipeInfo.put("recipeDetails", recipeDetails);
        recipeInfo.put("recipeScraps", recipeScraps);
        recipeInfo.put("scrapStatus", scrapStatus);
        recipeInfo.put("role", role);

        return recipeInfo;
    }

    @Transactional
    @Override
    public void deleteRecipeByRecipeNo(int recipeNo) {
        recipeMapper.deleteRecipeScrapByNo(recipeNo);
        recipeMapper.deleteRecipeCommentByNo(recipeNo);
        recipeMapper.deleteRecipeDetailByRecipeNo(recipeNo);
        recipeMapper.deleteRecipeByRecipeNo(recipeNo);
    }

    @Override
    public int saveScrap(String userId, int recipeNo) {
        // RecipeScrap 객체 생성 및 등록
        RecipeScrap recipeScrap = new RecipeScrap(userId, recipeNo);
        recipeMapper.insertRecipeScrap(recipeScrap);

        return recipeMapper.countScrapByRecipeNo(recipeNo);
    }

    @Override
    public int deleteScrap(String userId, int recipeNo) {
        // RECIPE_SCRAP 삭제
        recipeMapper.deleteMyRecipeScraps(userId, recipeNo);

        // 총 스크랩 갯수 반환
        return recipeMapper.countScrapByRecipeNo(recipeNo);
    }

    @Override
    public void updateRecipe(MultipartHttpServletRequest request, RecipeRegisterform recipeRegisterform) throws Exception {

        Integer reicpeNo = recipeRegisterform.getRecipeNo();

        // 1. RECIPE TABLE 수정
        Recipe recipe = recipeMapper.selectRecipeByRecipeNo(reicpeNo);
        RecipeCategory category = categoryMapper.selectCategoryByName(recipeRegisterform.getCategoryName());
        recipe.setRecipeName(recipeRegisterform.getRecipeName());
        recipe.setRecipeCategoryNo(category.getRecipeCategoryNo());
        recipeMapper.updateRecipe(recipe);

        // 2. 사용자가 삭제한 RECIPE_DETAIL_NO (STEP)을 찾아서 삭제하고 반영한다.

        // DB에 저장되어 있는 RECIPE_DETAIL_NO
        List<Integer> checkDeleteNos = recipeMapper.selectRecipeDetailsNoByRecipeNo(reicpeNo);

        // 클라이언트에서 넘어온 RECIPE_DETAIL_NO
        List<Integer> updateDetailNos = recipeRegisterform.getRecipeDetailNo();
        Iterator<Integer> upIter = updateDetailNos.iterator();
        while (upIter.hasNext()) {
            Integer up = upIter.next();

            // 클라이언트에서 넘어온 것과 DB에 있는 NO가 일치하면 checkDeleteNos 에서 삭제한다.
            // 삭제할 NO만 남기기 위함
            if (up != null) {
                checkDeleteNos.remove(up);
            }
        }
        for (int checkDeleteNo : checkDeleteNos) {
            // DB에서 해당 STEP을 삭제한다.
            recipeMapper.deleteRecipeDetailByDetailNo(checkDeleteNo);
        }

        // 등록한 STEP 총 갯수를 구한다.
        int lastIndex = recipeRegisterform.getRecipeDetailNo().size();


        for (int i = 0; i < lastIndex; i++) {

            // NO가 NULL이란 것은 사용자가 새로 등록한 것 INSERT를 수행한다.
            if (updateDetailNos.get(i) == null) {

                // 1. request에 있는 file 한 개 가져오기
                MultipartFile imgFile = request.getFiles("img").get(i);

                // 2. 오리지널 파일명으로 고유한 이미지 이름 생성
                String uniqFileName = FileUtils.getUniqueOriginalFileName(recipeRegisterform.getImg().get(i));

                // 3. 지정된 디렉토리(recipeImgOriginDirectory)에 이미지 저장
                FileUtils.insertImageFile(imgFile, recipeImgOriginDirectory, uniqFileName);

                // 4. 메인에 사용할 썸네일 이미지 생성 및 지정된 디렉토리 (recipeImgThumbnailDirectory)에 이미지 저장
                BufferedImage thumImage = ThumbUtils.createThumbnail(imgFile, 252, 252);
                ImageIO.write(thumImage, "png", new File(recipeImgThumbnailDirectory, uniqFileName));

                // 5. RecipeDetail 객체 생성 담기
                RecipeDetail detail = new RecipeDetail();
                detail.setRecipeNo(reicpeNo);
                detail.setRecipeDetailStep(i);
                detail.setRecipeDetailImg(uniqFileName);

                // 마지막 인덱스를 제외한 나머지에 레시피 내용 담기
                // lastIndex는 대표사진 이기 때문에 content가 null
                if (i < lastIndex - 1) {
                    detail.setContent(recipeRegisterform.getContent().get(i));
                }
                //6. RECIPE_DETAIL TABLE에 등록
                recipeMapper.insertRecipeDetail(detail);
            }
            // NO를 바탕으로 기존의 STEP을 수정한다.
            else {

                // 1. NO에 해당하는 RecipeDetail을 조회한다.
                RecipeDetail recipeDetail = recipeMapper.selectOneRecipeDetailByRecipeDetailNo(recipeRegisterform.getRecipeDetailNo().get(i));

                // 2. 마지막 INDEX(대표사진)가 아니라면 레시피 내용과 STEP 수정
                //    마지막 INDEX 면 STEP만 수정
                if (i < lastIndex - 1) {
                    recipeDetail.setContent(recipeRegisterform.getContent().get(i));
                    recipeDetail.setRecipeDetailStep(recipeRegisterform.getStepIndex().get(i));
                } else {
                    recipeDetail.setRecipeDetailStep(recipeRegisterform.getStepIndex().get(i - 1) + 1);
                }

                // 3. Img가 비어있지 않다면 사용자가 이미지를 수정한 것
                //    새 이미지 저장 및, RecipeDetail img 수정
                if (!recipeRegisterform.getImg().get(i).isEmpty()) {

                    // 1. request에 있는 file 한 개 가져오기
                    MultipartFile imgFile = request.getFiles("img").get(i);

                    // 2. 오리지널 파일명으로 고유한 이미지 이름 생성
                    String uniqFileName = FileUtils.getUniqueOriginalFileName(recipeRegisterform.getImg().get(i));

                    // 3. 지정된 디렉토리(recipeImgOriginDirectory)에 이미지 저장
                    FileUtils.insertImageFile(imgFile, recipeImgOriginDirectory, uniqFileName);

                    // 4. 메인에 사용할 썸네일 이미지 생성 및 지정된 디렉토리 (recipeImgThumbnailDirectory)에 이미지 저장
                    BufferedImage thumImage = ThumbUtils.createThumbnail(imgFile, 252, 252);
                    ImageIO.write(thumImage, "png", new File(recipeImgThumbnailDirectory, uniqFileName));

                    // 5. 변경된 경로
                    recipeDetail.setRecipeDetailImg(uniqFileName);
                }
                // RECIPE_DETAIL TABLE UPDATE
                recipeMapper.updateRecipeDetail(recipeDetail);
            }
        }
    }

    @Override
    public Map<String, Object> searchMain(Searchform searchform) throws Exception {
        try {

            // 1. searchform(조회 조건)으로 총 조회 갯수 구하기
            int totalRows = recipeMapper.searchRecipeCount(searchform);
            // 2. pageNo 가 null 이라면 검색조건이 없다는 뜻 1 페이지를 값으로 한다.
            int pageNo = (searchform.getPageNo() != null ? searchform.getPageNo() : 1);


            // 3. pagenation 객체 생성 및 searchform set
            Pagination pg = new Pagination(pageNo, totalRows);
            searchform.setBeginIndex(pg.getBeginIndex() -1);
            searchform.setEndIndex(pg.getEndIndex());

            // 4. 조회조건으로 레시피 리스트 가져오기
            List<MainRecipesDto> recipes = recipeMapper.searchRecipesBysearchForm(searchform);
            for (MainRecipesDto recipe : recipes) {
                if(recipe.getModDate() == null) {
                    recipe.setFullDate(recipe.getCreateDate());
                } else {
                    recipe.setFullDate(recipe.getModDate());
                }
            }
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("pg", pg);
            result.put("recipes", recipes);

            return result;

        } catch (Exception e) {
            throw new Exception("전달받은 searchform : " + searchform + " / e :" + e);
        }
    }
}
