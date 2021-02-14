package com.seongbindb.recipe.utils;

/**
 * <pre>
 * 페이징 처리를 위한 클래스
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:33:28
 * @Version : 1.0
 */
public class Pagination {

    private final int rowsPerPage;
    private final int pagesPerPage;
    private final int pageNo;
    private final int totalRows;

    public Pagination(int pageNo, int totalRows) {
        this.rowsPerPage = 8;
        this.pagesPerPage = 4;
        this.pageNo = pageNo;
        this.totalRows = totalRows;
    }

    @Override
    public String toString() {
        return "Pagination [rowsPerPage=" + rowsPerPage + ", pagesPerPage=" + pagesPerPage + ", pageNo=" + pageNo
                + ", totalRows=" + totalRows + "]";
    }

    /**
     * <pre>
     * 조회된 값에서 총 표시해야할 페이지를 나타낸다.
     * </pre>
     *
     * @return 총 표시해야할 페이지
     */
    public int getTotalPageCount() {
        return NumberUtils.ceilForPagination(totalRows, rowsPerPage);
    }

    /**
     * <pre>
     * 전체 구간 개수를 구한다.
     * </pre>
     *
     * @return 전체 구간 개수
     */
    public int getTotalBlocksCount() {
        return NumberUtils.ceilForPagination(getTotalPageCount(), pagesPerPage);
    }

    /**
     * <pre>
     * 현재구간이 어딘지 나타낸다.
     * </pre>
     *
     * @return 현재 구간
     */
    public int getCurrentBlockNo() {
        return NumberUtils.ceilForPagination(pageNo, pagesPerPage);
    }

    /**
     * <pre>
     * 시작하는 페이지 (1,5,9)등을 나타낸다.
     * </pre>
     *
     * @return 시작하는 페이지 번호
     */
    public int getBeginPage() {
        return (getCurrentBlockNo() - 1) * pagesPerPage + 1;
    }

    /**
     * <pre>
     * 조회된 값의 마지막 페이지를 알려준다.
     * </pre>
     *
     * @return 끝 페이지 번호
     */
    public int getEndPage() {
        return (getCurrentBlockNo() * pagesPerPage);
    }

    /**
     * <pre>
     * 요청한 페이지 번호에 해당하는 데이터의 조회 시작 번호
     * </pre>
     *
     * @return 특정 열을 기준으로 정렬하고 순번을 부여했을 때 조회 구간의 시작 번호
     */
    public int getBeginIndex() {
        return (pageNo - 1) * rowsPerPage + 1;
    }

    /**
     * <pre>
     * 요청한 페이지 번호에 해당하는 데이터의 조회 끝 번호
     * </pre>
     *
     * @return 특정 열을 기준으로 정렬하고 순번을 부여했을 때 조회 구간의 끝 번호
     */
    public int getEndIndex() {
        return pageNo * rowsPerPage;
    }

    /**
     * <pre>
     * 현재 페이지번호
     * </pre>
     *
     * @return 현재 페이지 번호
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * <pre>
     * 총 조회된 데이터 수를 나타낸다.
     * </pre>
     *
     * @return 총 데이터 조회 갯수
     */
    public int getTotalRows() {
        return totalRows;
    }
}
