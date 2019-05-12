package whs.picture.domain;
import java.util.List;

/**
 * 显示分页数据
 * @param <T>
 */
public class PageBean<T> {
    //当前页数
    private Integer page;
    //每页显示记录数
    private Integer limit;
    //总记录数
    private Integer totalCount;
    //总页数
    private Integer totalPage;
    //产品集合,需要显示到浏览器的产品
    private List<T> list;


    public PageBean() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }



    public void setList(List<T> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        return "Pagebean{" +
                "page=" + page +
                ", limit=" + limit +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}
