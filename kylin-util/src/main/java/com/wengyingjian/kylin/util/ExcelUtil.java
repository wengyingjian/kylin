package com.wengyingjian.kylin.util;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    /**
     * 读取excel文件
     *
     * @param file excel源文件
     * @return List<List<String>> string代表一个单元格的数据;List<String>代表一行数据;List<List<String>>代表多行数据
     * @throws Exception
     */
    public static List<List<String>> read(File file) {
        checkFileExists(file);
        Workbook book = null;
        try {
            book = Workbook.getWorkbook(file);
        } catch (Exception e) {
            ExceptionUtil.gen(e);
        }
        return doRead(book);
    }

    /**
     * 读取excel文件
     *
     * @param in excel输入流
     * @return
     * @throws Exception
     * @see ExcelUtil#read(File)
     */
    public static List<List<String>> read(InputStream in) {
        Workbook book = null;
        try {
            book = Workbook.getWorkbook(in);
        } catch (Exception e) {
            ExceptionUtil.gen(e);
        }
        return doRead(book);
    }

    private static List<List<String>> doRead(Workbook book) {
        List<List<String>> list = new ArrayList<>();
        Sheet sheet = book.getSheet(0);
        for (int i = 0; i < sheet.getRows(); i++) {
            List<String> line = new ArrayList<>();
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, i);
                line.add(cell.getContents());
            }
            list.add(line);
        }
        book.close();
        return list;
    }

    public static void checkFileExists(File file) {
        if (!file.exists()) {
            ExceptionUtil.gen("target file " + file + " not exists!");
        }
    }

    /**
     * 保存java对象至excel</br>
     * 保存的数据格式以集合中的第一个对象类型为准,如果集合中有不同的对象会出错<br/>
     *
     * @param file 保存至文件
     * @param list java对象集合
     * @param <T>
     * @throws Exception
     */
    public static <T> void write(File file, List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        Class clazz = list.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();

        List<String> fieldNames = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            fieldNames.add(field.getName());
        }
        int cols = fieldNames.size();
        WritableWorkbook book = null;
        try {
            book = getWritableWorkbook(file);
            WritableSheet sheet = book.createSheet("sheet1", 0);
            //write titles
            for (int i = 0; i < cols; i++) {
                sheet.addCell(new Label(i, 0, fieldNames.get(i)));
            }
            //loop for content
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < list.size(); j++) {
                    String content = getContent(list.get(j), fieldNames.get(i));
                    if (content != null) {
                        sheet.addCell(new Label(i, j + 1, content.toString()));
                    }
                }
            }
            book.write();
        } catch (NoSuchFieldException e) {
            ExceptionUtil.gen("NoSuchFieldException:可能出现问题的原因:集合中的对象类型不一致", e);
        } catch (Exception e) {
            ExceptionUtil.gen(e);
        } finally {
            try {
                book.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static WritableWorkbook getWritableWorkbook(File file) throws IOException {
        return Workbook.createWorkbook(file);
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
