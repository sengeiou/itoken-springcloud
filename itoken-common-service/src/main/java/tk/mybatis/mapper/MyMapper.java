package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;



/**
 * @ClassName MyMapper
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/1 14:11
 * @Version 1.0
 * 自己的 Mapper
 * 特别注意，该接口不能被扫描到，否则会出错
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
