package com.dj.system.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author wxl
 * @date 2020-03-13 上午8:48
 * @company www.dfdk.com.cn
 */
@Getter
@Setter
@ToString
public class ResVo<T> {

    private Long currentPage;

    private Long countPage;

    private Long totalPage;

    private int size;

    private List<T> list;

    private IPage<T> page;

    public ResVo() {
    }

    public ResVo(IPage<T> page) {
        this.page = page;
        this.currentPage = page.getCurrent();
        this.countPage = page.getSize();
        this.totalPage = page.getTotal();
        this.list = page.getRecords();
    }

    public ResVo(Long currentPage, Long countPage, Long totalPage, List<T> list) {
        this.currentPage = currentPage;
        this.countPage = countPage;
        this.totalPage = totalPage;
        this.list = list;
        this.size = list.size();
    }

    public ResVo(int currentPage, int countPage, int totalPage, List<T> list) {
        if (list.size() > 0) {
            //只夠一頁顯示分頁數據
            if (countPage >= totalPage) {
                currentPage = 1;
                countPage = countPage;
                totalPage = 1;
                list = list.subList(0, list.size());
            }
            //多于一頁顯示控制分頁數據
            else {
                currentPage = currentPage;
                countPage = countPage;
                //多余一頁切可以整頁顯示分頁數據
                if (totalPage % countPage == 0) {
                    totalPage = totalPage / countPage;
                    list = list.subList(Math.toIntExact((currentPage - 1) * countPage), Math.toIntExact(currentPage * countPage));
                } else {
                    totalPage = totalPage / countPage + 1;
                    //多余一頁後最後一頁顯示處理分頁數據
                    if (totalPage == currentPage) {
                        list = list.subList(Math.toIntExact(currentPage * countPage - countPage), list.size());
                    }
                    //多余一頁非最後一頁分頁數據
                    else if ((totalPage > currentPage)) {
                        list = list.subList(Math.toIntExact((currentPage - 1) * countPage), Math.toIntExact(currentPage * countPage));
                    }
                    //當前頁超過總頁數分頁數據處理
                    else {
                        list = null;
                    }
                }
            }
        }
        this.currentPage = (long) currentPage;
        this.countPage = (long) countPage;
        this.totalPage = (long) totalPage;
        this.list = list;
    }

}
