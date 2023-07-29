package com.cloud.jack.app.excel;

import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParserHelper {




    public static List<JSONObject> parseAndCheckByTxt(InputStream inputStream) {

        List<JSONObject> result = new ArrayList<>();
        List<String[]> dataList = new ArrayList<>();
        List<WorkBookTitleVO> titleVOS = new ArrayList<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int line = 0;
            String text = null;
            while ((text = bufferedReader.readLine()) != null) {
                if (line == 0) {
                    // 校验表头
                    String[] headers = text.split("\t");
                    titleVOS = setWorkBookTitle(headers);
                } else {
                    // 校验数据
                    if(StrUtil.isNotBlank(text) && !StrUtil.equals(text,"\u0000")){
                        String[] data = text.split("\t",-1);
                        if(StrUtil.isNotEmpty(data[0])){
                            dataList.add(data);
                        }

                    }
                }
                line++;
            }
            for (String[] data : dataList) {
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < data.length; i++) {
                    for (WorkBookTitleVO titleVO : titleVOS) {
                        jsonObject.put(titleVO.getColumnName(), data[titleVO.getCellIndex()]);
                    }
                }
                result.add(jsonObject);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static List<JSONObject> parseAndCheckByCsv(InputStream inputStream,Class clazz) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            CsvReader csvReader = new CsvReader();
            List list = csvReader.read(bufferedReader, clazz);
            System.out.println("list"+list);
            List<JSONObject> result = new ArrayList<>();
            return result;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 设置表头
     * @param headers
     * @return
     */
    private static List<WorkBookTitleVO> setWorkBookTitle(String[] headers) {
        List<WorkBookTitleVO> workBookTitleVOS = new ArrayList<>();
        for (int i = 0; i < headers.length; i++) {
            WorkBookTitleVO workBookTitleVO = new WorkBookTitleVO();
            workBookTitleVO.setColumnName(headers[i]);
            workBookTitleVO.setCellIndex(i);
            workBookTitleVOS.add(workBookTitleVO);
        }
        return workBookTitleVOS;
    }



}
