package com.exam.utils;


import com.exam.constant.CharConstant;
import com.exam.constant.NumberConstant;
import com.exam.constant.PatternConstant;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 增强StringUtils
 * @author
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private StringUtils() {}

    /**
     * 获得用户远程地址
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String remoteIp = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteIp)) {
            remoteIp = request.getHeader("X-Forwarded-For");
        } else if (isNotBlank(remoteIp)) {
            remoteIp = request.getHeader("Proxy-Client-IP");
        } else if (isNotBlank(remoteIp)) {
            remoteIp = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteIp != null ? remoteIp : request.getRemoteAddr();
    }

    public static String getFormatId(Long id, String prefix) {
        return getFormatId(id, prefix, "000000000000");
    }

    public static String getFormatId(Long id, String prefix, String format) {
        if (id == null) {
            return null;
        } else {
            DecimalFormat decimalFormat = new DecimalFormat(format);
            return prefix + decimalFormat.format(id);
        }
    }

    public static String getReplaced(String str, List<String> beforeList, String after) {
        String result = trim(str);
        if (StringUtils.isNotBlank(result)) {
            for (String before : beforeList) {
                result = StringUtils.replace(result, before, after);
            }
        }
        return result;
    }

    public static List<String> getSplitList(String str, String splitter) {
        List<String> list = Lists.newArrayList();
        if (isNotBlank(str)) {
            String[] arr = str.split(splitter);
            for (String item : arr) {
                if (isNotBlank(item) && !list.contains(item.trim())) {
                    list.add(item.trim());
                }
            }
        }
        return list;
    }

    public static String getSheetName(String name) {
        if (isBlank(name)) {
            return null;
        } else {
            return name.trim().replace("/", "").replace("\\", "").replace("?", "").replace("[", "").replace("]", "").replace("*", "");
        }
    }

    /**
     * 中文钱数
     * @param money
     * @return
     */
    public static String getChineseMoney(BigDecimal money) {
        if (money != null) {
            String s = new DecimalFormat("#.00").format(money.abs());
            // 将字符串中的"."去掉
            s = s.replaceAll("\\.", "");
            char[] d = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
            String unit = "仟佰拾兆仟佰拾亿仟佰拾万仟佰拾元角分";
            int c = unit.length();
            StringBuffer sb = new StringBuffer(unit);
            for (int i = s.length() - 1; i >= 0; i--) {
                sb.insert(c - s.length() + i, d[s.charAt(i) - 0x30]);
            }
            s = sb.substring(c - s.length(), c + s.length());
            s = s.replaceAll("零[仟佰拾]", "零").replaceAll("零{2,}", "零").replaceAll("零([兆万元Ԫ])", "$1").replaceAll("零[角分]", "");
            if (BigDecimal.ZERO.compareTo(money) == 1) {
                return "负" + s + "整";
            }
            if (BigDecimal.ZERO.compareTo(money) == 0) {
                return "零元整";
            }
            return s + "整";
        }
        return "";
    }

    /**
     * 返回byte的数据大小对应的文本
     *
     * @param size 大小
     * @return String
     */
    public static String getDataSize(long size) {
        DecimalFormat formater = new DecimalFormat("####.00");
        if (size < NumberConstant.ONE_M) {
            return size + "bytes";
        } else if (size < NumberConstant.ONE_M * NumberConstant.ONE_M) {
            float kbsize = size / 1024f;
            return formater.format(kbsize) + "KB";
        } else if (size < NumberConstant.ONE_M * NumberConstant.ONE_M * NumberConstant.ONE_M) {
            float mbsize = size / 1024f / 1024f;
            return formater.format(mbsize) + "MB";
        } else if (size < NumberConstant.ONE_M * NumberConstant.ONE_M * NumberConstant.ONE_M * NumberConstant.ONE_M) {
            float gbsize = size / 1024f / 1024f / 1024f;
            return formater.format(gbsize) + "GB";
        } else {
            return "size: error";
        }
    }

    /**
     * 获取列对应的下标字母
     */
    public static String getExcelCellIndex(int index) {
        StringBuilder rs = new StringBuilder();
        while (index > 0) {
            index--;
            rs.insert(0, ((char) (index % 26 + 'A')));
            index = (index - index % 26) / 26;
        }
        return rs.toString();
    }

    public static String toString(Object obj) {
        return Objects.toString(obj, "");
    }

    public static Boolean equal(Object obj1, Object obj2) {
        return toString(obj1).equals(toString(obj2));
    }

    /**
     * 把中文转成Unicode码
     *
     * @param str 字符
     * @return 转码后的字符
     */
    public static String chinaToUnicode(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int chr1 = str.charAt(i);
            // 汉字范围 \u4e00-\u9fa5 (中文)
            if (chr1 >= 19968 && chr1 <= 171941) {
                result.append("\\u").append(Integer.toHexString(chr1));
            } else {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    /**
     * 去除  \u00A0 和 空格
     *
     * @return String 去除\u00A0和空格后的字符串
     */
    public static String trim(String str) {
        // unicode 化
        if (isEmpty(str)) {
            return null;
        }
        String u00A0 = StringEscapeUtils.unescapeJava("\u00A0");
        str = str.replaceAll(u00A0, "");
        return str.trim();
    }

    public static Boolean isHref(String href) {
        boolean foundMatch;
        try {
            foundMatch = href.matches("(?sm)^https?://[-\\w+&@#/%=~_|$?!:,.\\\\*]+$");
        } catch (Exception e) {
            return false;
        }
        return foundMatch;
    }

    public static Boolean isGtZero(String number) {
        boolean foundMatch;
        try {
            foundMatch = number.matches("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$");
        } catch (Exception e) {
            return false;
        }
        return foundMatch;
    }

    /**
     * @param bankNumber 银行卡号，如：622200000544234
     * @return String 返回类型 隐藏后的卡号，如6222****4234
     * @Title: hideBankNumber
     * @Description: 隐藏银行卡号码  -> 6222****4234
     */
    public static String hideBankNumber(String bankNumber) {
        if (org.springframework.util.StringUtils.isEmpty(bankNumber)) {
            return "";
        } else if (bankNumber.length() < NumberConstant.EIGHT) {
            return bankNumber;
        } else {
            return bankNumber.substring(0, 4) + "****" + bankNumber.substring(bankNumber.length() - 4);
        }
    }

    /**
     * 阿里云附件上传目录默认规则
     *
     * @param timeMillis 毫秒数
     * @param pattern    匹配符
     * @return String
     */
    public static String formatDate(long timeMillis, String pattern) {
        DateFormat formatter = new SimpleDateFormat(pattern, Locale.CHINESE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        return formatter.format(calendar.getTime());
    }


    /**
     * 校验微信公众号的格式 　微信号规则：微信账号仅支持6-20个字母、数字、下划线或减号，以字母开头。解释一下，只要是字母开头，可以是纯字母（hjqzHJQZhongjiqiezi），或字母数字混合
     *
     * @param weixinCode 微信号
     * @return 返回true表示校验通过
     */
    public static boolean validateWeixin(String weixinCode) {
        if (weixinCode == null) {
            return false;
        }
        // 校验微信号正则
        Pattern pat = PatternConstant.WE_CHAT_PATTERN;
        Matcher mat = pat.matcher(weixinCode);
        return mat.matches();
    }

    /**
     * 驼峰转下划线
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String clearUtf8bm4(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return str.replaceAll("(?sm)[^\u0000-\uD7FF\uE000-\uFFFF]", "");
    }

    public static void assertString(String object, String objectName) {
        Assert.notNull(object, objectName + " is null");
        Assert.hasLength(object.trim(), objectName + " is empty");
    }


    /**
     * 获取异常堆栈信息
     *
     * @param e
     * @return
     */
    public static String getStackTraceInfo(Throwable e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        return writer.getBuffer().toString();
    }

    /**
     * 字串是否包含字串集合中的某一个字串
     *
     * @param str      字串
     * @param contains 被包含字串集合
     * @return
     */
    public static boolean stringContains(String str, Set<String> contains) {
        for (String item : contains) {
            if (str.contains(item)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 字符集过滤转换
     *
     * @param filterValue 字符串
     * @return List
     */
    public static List<String> getFilterList(String filterValue) {
        List<String> filterList = Lists.newArrayList();
        if (isNotBlank(filterValue)) {
            filterValue = filterValue.trim().replace(CharConstant.CHAR_SPACE_A, CharConstant.CHAR_COMMA)
                    .replace(CharConstant.CHAR_COMMA_FULL, CharConstant.CHAR_COMMA)
                    .replace(CharConstant.CHAR_SPACE, CharConstant.CHAR_COMMA)
                    .replace(CharConstant.CHAR_SPACE_FULL, CharConstant.CHAR_COMMA)
                    .replace(CharConstant.CHAR_ENTER, CharConstant.CHAR_COMMA)
                    .replace(CharConstant.CHAR_SPACE_U, CharConstant.CHAR_COMMA);
            String[] filterArr = filterValue.split(CharConstant.CHAR_COMMA);
            for (String filter : filterArr) {
                if (isNotBlank(filter.trim())) {
                    filterList.add(filter.trim());
                }
            }
        }
        return filterList;
    }

}
