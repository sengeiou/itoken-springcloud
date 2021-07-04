package com.funtl.itoken.common.web.components.datatables;

import com.funtl.itoken.common.dto.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName DataTablesResult
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/10 11:19
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class DataTablesResult extends BaseResult implements Serializable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private String error;
}
