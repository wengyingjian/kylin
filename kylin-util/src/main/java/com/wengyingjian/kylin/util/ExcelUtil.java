package com.store59.charge.game.utils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel读写工具类
 *
 * @author <a href="mailto:wengyj@59store.com">翁英健</a>
 * @version 1.1 16/1/29
 * @since 1.1
 */
public class ExcelUtil {

    public static List<List<String>> read(File file) throws Exception {
        List<List<String>> list = new ArrayList<>();

        Workbook book = Workbook.getWorkbook(file);
        Sheet sheet = book.getSheet(0);

        for (int i = 0; i < sheet.getRows(); i++) {
            List<String> line = new ArrayList<>();
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(i, j);
                line.add(cell.getContents());
            }
            list.add(line);
        }

        book.close();
        return null;
    }

    public static <T> void write(File file, List<T> list) throws Exception {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        T t = null;
        Class clazz = list.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();

        List<String> fieldNames = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            fieldNames.add(field.getName());
        }
        int cols = fieldNames.size();

        WritableWorkbook book = Workbook.createWorkbook(file);
        WritableSheet sheet = book.createSheet("第一页", 0);

        //write titles
        for (int i = 0; i < cols; i++) {
            sheet.addCell(new Label(i, 0, fieldNames.get(i)));
        }
        for (int i = 0; i < cols; i++)    //context
        {
            for (int j = 0; j < list.size(); j++) {
                String content = getContent(list.get(j), fieldNames.get(i));
                if (content != null) {
                    sheet.addCell(new Label(i, j + 1, content.toString()));
                }
            }
        }
        book.write();
        book.close();
    }

    /**
     * 获取对象中的属性
     *
     * @param t
     * @param fieldName
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static <T> String getContent(T t, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Assert.notNull(t, "can not get value for field:" + fieldName + "from null object!");
        Class clazz = t.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        Object value = field.get(t);
        return value == null ? null : value.toString();
    }
}
