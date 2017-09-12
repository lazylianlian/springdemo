package com.springvoice.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by llsam on 2017/9/9.
 */


@Controller
@RequestMapping(value = "/hello", method = RequestMethod.GET)
public class testController {

    @RequestMapping(value = "/llsam", method = RequestMethod.GET)
    public String sysHello(String txt,Model model) {
        List<String> txtList=getStrList(txt,100);
        model.addAttribute("txtList",txtList);
        return "/voice";
    }
    @RequestMapping(value = "/toPage", method = RequestMethod.GET)
    public String topage() {

        return "/hello";
    }

    private List<String> getTxtList(String txt,Integer lenth) {
        List<String> textList=new ArrayList<String>();
        int length = txt.length();
        if (length<lenth){
            textList.add(txt);
        }else {
            int count = length/lenth;
            if (length%lenth>0)
                count+=1;
            for (int i = 0; i < count; i+=lenth) {
                int a=i;
                String str = txt.substring(i,i+lenth);
                textList.add(str);
            }
        }
        return textList;
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString
     *            原始字符串
     * @param length
     *            指定长度
     * @return
     */
    public static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString
     *            原始字符串
     * @param length
     *            指定长度
     * @param size
     *            指定列表大小
     * @return
     */
    public static List<String> getStrList(String inputString, int length,
                                          int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }

    /**
     * 分割字符串，如果开始位置大于字符串长度，返回空
     *
     * @param str
     *            原始字符串
     * @param f
     *            开始位置
     * @param t
     *            结束位置
     * @return
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }



}
