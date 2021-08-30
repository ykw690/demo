package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/test")
//@Path("/test")
public class TestController {
    //    @Qualifier
    @Autowired(required = false)
    private TestClass1 testClass1;
    @Resource()
    private TestClass2 testClass2;
    @Autowired
    private Configuration configuration;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private RedisUtil redisUtil;

    public void init(){
        System.out.println("控制器初始化了!!!!!!!!!!!!!!!!!!!");
    }

    public static HashMap<String,String> map = new HashMap(16);

//    @RequestMapping("/getTest")
//    public String getString(){
//        Bean1 s1 = Bean1.builder().i1(1).s1("s1").build();
//        System.out.println(JSON.toJSONString(s1));
//        return "123";
//    }

    @RequestMapping("/getClass1")
    public String getClass1(HttpServletRequest httpServletRequest) throws JsonProcessingException {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        String parameter = httpServletRequest.getParameter("1");
        String header = httpServletRequest.getHeader("Content-Type");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        TestClass1 testClass1 = new TestClass1();
        testClass1.setS1("s11");
        testClass1.setOptional(Optional.empty());
        ArrayList<Object> l = new ArrayList<>();
        l.add(0,"1");
        testClass1.setList(l);
        return objectMapper.writeValueAsString(testClass1);
    }

    @RequestMapping("/put")
    public void put(@RequestParam("path") String path){
        map.put(path,path);
    }
    @RequestMapping("/get")
    public String get(@RequestParam("path") String path){
        return map.get(path);
    }

    @RequestMapping("/getKey")
    public String getKey(@RequestParam("key") String key) {
        return map.get(key);
    }

    @RequestMapping("/getKey2")
    @Cacheable(value = "test",key = "#key")
    public String getKey2(@RequestParam("key") String key) {
        return map.get(key);
    }

    @RequestMapping("/setKey")
    public String setKey(@RequestParam("key") String key) {
        map.put(key,key);
        return map.get(key);
    }

    @RequestMapping("/setKey2")
    public String setKey2(@RequestParam("key") String key) {
        map.put(key,key + 1);
        return map.get(key);
    }

    @RequestMapping("/getConfigure")
    public String getConfigure() {
        return configuration.getName();
    }

    @RequestMapping("/test1")
    public void test1(HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        HashMap<String, String> map = new HashMap<>();
        map.put("msg","123");
        map.put("code","success");
        response.getWriter().println(map);
    }

    @RequestMapping("/export2")
    public HttpResponse export2() throws IOException {
        HttpResponse httpResponse = new HttpResponse();
        String data = "[{\"merchantName\":\"KFCS0001\",\"orderId\":\"b7ca39e36ea541a9aae471c210116f53\",\"orderNumber\":\"DK1621394646738x6887822\",\"sttlDate\":\"2021-05-19 11:24:06\",\"transMoney\":0.01,\"transState\":\"02\",\"transType\":\"03\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"c31da0ea979142efbf35e4552131f451\",\"orderNumber\":\"DK1621394600279x9343876\",\"sttlDate\":\"2021-05-19 11:23:20\",\"transMoney\":0.01,\"transState\":\"02\",\"transType\":\"03\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"c3bee6dc3edc46d280f07e68b623cfd7\",\"orderNumber\":\"DK1621394561216x9831781\",\"sttlDate\":\"2021-05-19 11:22:41\",\"transMoney\":0.01,\"transState\":\"02\",\"transType\":\"03\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"e4f4ce1a542e4777be6fb5de867703db\",\"orderNumber\":\"DK1621394507160x653366\",\"sttlDate\":\"2021-05-19 11:21:46\",\"transMoney\":0.01,\"transState\":\"02\",\"transType\":\"03\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"824d5fe8e44e4c4d80f8f5fc4724ef03\",\"orderNumber\":\"ZJDK202105191118267zuhd3w0\",\"sttlDate\":\"2021-05-19 11:18:26\",\"transMoney\":0.01,\"transState\":\"07\",\"transType\":\"03\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"bad0f878e93b4f75a265f535f9059224\",\"orderNumber\":\"TX1621394281114x6899812\",\"sttlDate\":\"2021-05-19 11:18:00\",\"transState\":\"03\",\"transType\":\"02\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"2693180deef341f69a5b0b57bd40caad\",\"orderNumber\":\"TX1621393993270x2099869\",\"sttlDate\":\"2021-05-19 11:13:13\",\"transMoney\":0,\"transState\":\"07\",\"transType\":\"02\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"c71fad85747448e3868c89aa1206bab8\",\"orderNumber\":\"XF1621393828129x5650662\",\"sttlDate\":\"2021-05-19 11:10:28\",\"transMoney\":0.05,\"transState\":\"07\",\"transType\":\"20\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"d21906d41d5948c1b048a96373399193\",\"orderNumber\":\"CHYQapp2021051911090868jzy43c\",\"sttlDate\":\"2021-05-19 11:09:08\",\"transMoney\":0,\"transState\":\"02\",\"transType\":\"02\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"4ceb3f917aad49d9adc291d17b45f409\",\"orderNumber\":\"XF1621393733603x9491032\",\"sttlDate\":\"2021-05-19 11:08:54\",\"transMoney\":0.05,\"transState\":\"07\",\"transType\":\"20\"}]";
        ArrayList<TradingDataBill> resultList = (ArrayList<TradingDataBill>) JSONObject.parseArray(data, TradingDataBill.class);
        if (!CollectionUtils.isEmpty(resultList)) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String random = System.currentTimeMillis() + "" + (int) ((Math.random() * 9 + 1) * Math.pow(10, 4));
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode(random, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), TradingDataBill.class).autoCloseStream(Boolean.TRUE).sheet("sheet1")
                    .doWrite(resultList);
            httpResponse.setHttpServletResponse(response);
            return httpResponse;
        }
        setResponseMsg(response, "查无交易信息");
        return httpResponse;
    }

//    @RequestMapping("/test4")
//    public String test4() throws IOException {
//        String data = "[{\"merchantName\":\"KFCS0001\",\"orderId\":\"b7ca39e36ea541a9aae471c210116f53\",\"orderNumber\":\"DK1621394646738x6887822\",\"sttlDate\":\"2021-05-19 11:24:06\",\"transMoney\":0.01,\"transState\":\"02\",\"transType\":\"03\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"c31da0ea979142efbf35e4552131f451\",\"orderNumber\":\"DK1621394600279x9343876\",\"sttlDate\":\"2021-05-19 11:23:20\",\"transMoney\":0.01,\"transState\":\"02\",\"transType\":\"03\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"c3bee6dc3edc46d280f07e68b623cfd7\",\"orderNumber\":\"DK1621394561216x9831781\",\"sttlDate\":\"2021-05-19 11:22:41\",\"transMoney\":0.01,\"transState\":\"02\",\"transType\":\"03\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"e4f4ce1a542e4777be6fb5de867703db\",\"orderNumber\":\"DK1621394507160x653366\",\"sttlDate\":\"2021-05-19 11:21:46\",\"transMoney\":0.01,\"transState\":\"02\",\"transType\":\"03\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"824d5fe8e44e4c4d80f8f5fc4724ef03\",\"orderNumber\":\"ZJDK202105191118267zuhd3w0\",\"sttlDate\":\"2021-05-19 11:18:26\",\"transMoney\":0.01,\"transState\":\"07\",\"transType\":\"03\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"bad0f878e93b4f75a265f535f9059224\",\"orderNumber\":\"TX1621394281114x6899812\",\"sttlDate\":\"2021-05-19 11:18:00\",\"transState\":\"03\",\"transType\":\"02\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"2693180deef341f69a5b0b57bd40caad\",\"orderNumber\":\"TX1621393993270x2099869\",\"sttlDate\":\"2021-05-19 11:13:13\",\"transMoney\":0,\"transState\":\"07\",\"transType\":\"02\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"c71fad85747448e3868c89aa1206bab8\",\"orderNumber\":\"XF1621393828129x5650662\",\"sttlDate\":\"2021-05-19 11:10:28\",\"transMoney\":0.05,\"transState\":\"07\",\"transType\":\"20\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"d21906d41d5948c1b048a96373399193\",\"orderNumber\":\"CHYQapp2021051911090868jzy43c\",\"sttlDate\":\"2021-05-19 11:09:08\",\"transMoney\":0,\"transState\":\"02\",\"transType\":\"02\"},{\"merchantName\":\"KFCS0001\",\"orderId\":\"4ceb3f917aad49d9adc291d17b45f409\",\"orderNumber\":\"XF1621393733603x9491032\",\"sttlDate\":\"2021-05-19 11:08:54\",\"transMoney\":0.05,\"transState\":\"07\",\"transType\":\"20\"}]";
//        ArrayList<TradingDataBill> resultList = (ArrayList<TradingDataBill>) JSONObject.parseArray(data, TradingDataBill.class);
//        if (!CollectionUtils.isEmpty(resultList)) {
//            String random = System.currentTimeMillis() + "" + (int) ((Math.random() * 9 + 1) * Math.pow(10, 4));
//            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//            String fileName = URLEncoder.encode(random, "UTF-8").replaceAll("\\+", "%20");
//            // 这里需要设置不关闭流
//            FileOutputStream fileOutputStream = new FileOutputStream("D:\\一汽文件\\" + fileName + ".xlsx");
//            EasyExcel.write(fileOutputStream, TradingDataBill.class).autoCloseStream(Boolean.TRUE).sheet("sheet1")
//                    .doWrite(resultList);
//            return fileOutputStream;
//        }
//        return null;
//    }

    @RequestMapping("/test3")
    public String test3(HttpServletResponse response) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        ArrayList<TradingDataBill> list = new ArrayList<>();
        for(int i = 0;i<10;i++) {
            TradingDataBill tradingDataBill = new TradingDataBill();
            tradingDataBill.setMerchantName("faw0000"+i);
            tradingDataBill.setOrderNumber("946ea956c92c438586723013a3bf6f92");
            tradingDataBill.setRemark("摘要"+i);
            tradingDataBill.setSttlDate(format);
            tradingDataBill.setTransMoney(new BigDecimal((Math.random()*9+1) * 10000));
            tradingDataBill.setTransState("26");
            tradingDataBill.setTransType("20");
            list.add(tradingDataBill);
        }
        //生成随机数
        String random = System.currentTimeMillis()+""+(int) ((Math.random() * 9 + 1) * Math.pow(10, 4));
        String fileName = String.format("D:\\一汽文件\\%s.xlsx", random);
        File file = new File(fileName);
        boolean newFile = file.createNewFile();
        EasyExcel.write(file, TradingDataBill.class).sheet("sheet1").doWrite(list);
//        File file1 = new File(fileName);
        byte[] bytes = FileCopyUtils.copyToByteArray(file);
        String random1 = System.currentTimeMillis()+""+(int) ((Math.random() * 9 + 1) * Math.pow(10, 4));
        String fileName1 = String.format("D:\\一汽文件\\%s.xlsx", random1);
        File file2 = new File(fileName1);
        FileCopyUtils.copy(bytes,file2);
        file2.createNewFile();
        return JSON.toJSONString(bytes);
    }

//    public static void main(String[] args) {
//        String a = "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";
//        byte[] bytes1 = JSONObject.parseObject(a, byte[].class);
//        byte[] bytes = a.getBytes(StandardCharsets.UTF_8);
//        System.out.println("[B@2626dca".getBytes(StandardCharsets.UTF_8));
//    }

    /**
     * 设置错误信息至HttpServletResponse
     * @param response
     * @param msg
     * @throws IOException
     */
    private void setResponseMsg(HttpServletResponse response,String msg) throws IOException {
        if(null == response)return;
        // 重置response
        response.reset();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "failure");
        map.put("message", "下载文件失败:" + msg);
        response.getWriter().println(JSON.toJSONString(map));
    }

    @RequestMapping("/getPic")
    public void getPic(HttpServletResponse response) throws IOException {
        String pic = "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";
        String path = "D:\\一汽文件\\资料\\测试图片\\test.jpg";      //得到文件大小
        generateImage(pic,path);
    }

    /**
     * 字符串转图片
     * @param imgStr --->图片字符串
     * @param filename    --->图片名
     * @return
     */
    public static boolean generateImage(String imgStr, String filename) {

        if (imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(filename);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }

    public static void main(String[] args) {
        char a = '0';
        char i = (char) ( 1 + a);
        System.out.println(i);
        System.out.println(1 + a);
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        ArrayList<TradingDataBill> list = new ArrayList<>();
        for(int i = 0;i<10;i++) {
            TradingDataBill tradingDataBill = new TradingDataBill();
            tradingDataBill.setMerchantName("faw0000"+i);
            tradingDataBill.setOrderNumber("946ea956c92c438586723013a3bf6f92");
            tradingDataBill.setRemark("摘要"+i);
            tradingDataBill.setSttlDate(format);
            tradingDataBill.setTransMoney(new BigDecimal((Math.random()*9+1) * 10000));
            /** 交易状态 */
            tradingDataBill.setTransState("26");
            /** 交易类型 */
            tradingDataBill.setTransType("20");
            list.add(tradingDataBill);
        }
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String random = System.currentTimeMillis() + "" + (int) ((Math.random() * 9 + 1) * Math.pow(10, 4));
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode(random, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // 头的策略
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            // 背景设置为红色
            WriteFont headWriteFont = new WriteFont();
            headWriteFont.setFontName("Serif");
            headWriteCellStyle.setWriteFont(headWriteFont);
            // 内容的策略
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
            WriteFont contentWriteFont = new WriteFont();
            contentWriteFont.setFontName("Serif");
            contentWriteCellStyle.setWriteFont(contentWriteFont);
            // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
//            if(1==1){
//                throw new Exception("失败测试");
//            }
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), TradingDataBill.class).registerWriteHandler(horizontalCellStyleStrategy).autoCloseStream(Boolean.FALSE).sheet("sheet1")
                    .doWrite(list);
        }catch (Exception e){
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败:" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    @RequestMapping("/rollBack")
    @Transactional(rollbackFor = Exception.class)
    public void rollBack() throws Exception{
        File file = new File("D:\\国银文件\\test.txt");
        boolean newFile = file.createNewFile();
        System.out.println("-------------------------创建文件结果:"+newFile);
        throw new Exception("故意抛错");
    }
//    public static void main(String[] args) {
////        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
////        String[] fonts = environment.getAvailableFontFamilyNames();//获得系统字体
////        for (int i = 0; i < fonts.length; i++) {
////            System.out.println(fonts[i]);
////        }
////        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
////        objectObjectHashMap.put("1","1");
////        objectObjectHashMap.put("2","2");
////        objectObjectHashMap.put("3","3");
////        System.out.println(objectObjectHashMap.toString());
////        System.out.println(JSON.toJSONString(objectObjectHashMap));
//        String[] s = {};
//        boolean notEmpty = CollUtil.isNotEmpty(Arrays.asList(s));
//        System.out.println(notEmpty);
//    }

//    @RequestMapping("/redis")
//    public void redis() throws Exception{
//        redisUtil.redisSet();
//    }

    @RequestMapping("/post")
    public String testPost() throws Exception {
        throw new Exception("测试异常");
    }

    @RequestMapping("/url")
    public String testUrl(HttpServletRequest request) throws Exception {
        String requestURI = request.getRequestURI();
        String s = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        Map<String, String[]> params = request.getParameterMap();

        for (Map.Entry<String, String[]> param : params.entrySet()) {
            String key = param.getKey();  // 参数名

            String value = StringUtils.join(param.getValue());  // 参数值
        }
        String notifyStr = new String(readInputStream(request.getInputStream()), "UTF-8");
        System.out.println(notifyStr);
        JSONObject object = JSONObject.parseObject(notifyStr);
        String sign = object.getString("sign");
        String appName = object.getString("appName");
        return requestURI + "=============" + s;
    }



    public static byte[] readInputStream(InputStream inStream) {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
        } catch (IOException e) {

        }
        return outSteam.toByteArray();
    }
}
