package com.springvoice.controller;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "native", method = RequestMethod.GET)
public class nativeController {
    //设置APPID/AK/SK
    public static final String APP_ID = "10126416";
    public static final String API_KEY = "vMZIdoZ5X4VcydPvWyDGLhiN";
    public static final String SECRET_KEY = "e90ba778ef5e273baa4c00d4c7f7a6b8";


    @RequestMapping(value = "/wenjie", method = RequestMethod.GET)
    public String sysHello(String txt, Model model) {
        List<String> txtList = getStrList(txt, 100);

        // 初始化一个FaceClient
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", "5");
        options.put("pit", "5");
        options.put("per", "1");

        // 保存data至文件，即为可播放的MP3文件
        File file = new File("E:/aa/Hello.mp3");
        FileOutputStream fos = null;

        try {

            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file, true);
            for (int i = 0; i < txtList.size(); i++) {
                // 调用API
                TtsResponse res = client.synthesis(txtList.get(i), "zh", 1, options);
                System.out.println(res.getErrorCode());
                byte[] data = res.getData();

                fos.write(data);
                fos.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        model.addAttribute("item", "");

        return "/second";
    }


    @RequestMapping(value = "/toVoice", method = RequestMethod.GET)
    public String topage() {

        return "/first";
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString 原始字符串
     * @param length      指定长度
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
     * @param inputString 原始字符串
     * @param length      指定长度
     * @param size        指定列表大小
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
     * @param str 原始字符串
     * @param f   开始位置
     * @param t   结束位置
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
