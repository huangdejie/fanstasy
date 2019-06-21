package com.cashbang;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangdejie
 * @Date: 2019/5/31
 */
public class ExcelTest extends BaseTest {

    @Test
    public void testExcelOut() throws Exception{
        OutputStream out = new FileOutputStream("D://newFile11.xls");
        ExcelWriter excelWriter = EasyExcelFactory.getWriter(out);
        Sheet sheet = new Sheet(1,0,WriteModel.class);
        sheet.setSheetName("第一个sheet");
        excelWriter.write(createModelList(),sheet);
        excelWriter.finish();
        out.close();
    }

    private List<WriteModel> createModelList(){
        List<WriteModel> writeModelList = new ArrayList<>();
        for(int i = 0;i<100000;i++){
            WriteModel writeModel = new WriteModel();
            writeModel.setName("张华"+i);
            writeModel.setAge(i+"");
            writeModel.setCardNo("CS0001"+i);
            writeModelList.add(writeModel);
        }
        return writeModelList;
    }

    @Test
    public void testGetUrl() throws Exception{
        String urlStr = "http://csplatform-dev.oss-cn-hangzhou.aliyuncs.com/offer/offerAttachments/a8327b0e82fd4d0086c665017ba20faa.docx";
        URL url = new URL(urlStr);
        String suffix = "docx";
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5*1000);
        InputStream inputStream = connection.getInputStream();
        byte[] data = readInputStream(inputStream);
        File file = new File("D://kskk.docx");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();
    }

    private byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length=inputStream.read(buffer)) != -1){
            byteArrayOutputStream.write(buffer,0,length);
        }
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

}
