package com.apifan.common.commonrandomapi.component;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * 批量数据生成器
 *
 * @author yin
 */
@Component
public class BatchDataGenerator {
    private static final Logger logger = LoggerFactory.getLogger(BatchDataGenerator.class);

    /**
     * 生成字符串数组
     *
     * @param stringSupplier 字符串生成函数
     * @param size           数组大小
     * @return
     */
    public String[] getStringArray(Supplier<String> stringSupplier, int size) {
        Preconditions.checkArgument(stringSupplier != null, "字符串生成函数为空");
        Preconditions.checkArgument(size > 0, "数组大小必须大于0");
        String[] result = new String[size];
        IntStream.range(0, size).forEach(i -> result[i] = stringSupplier.get());
        return result;
    }
}
