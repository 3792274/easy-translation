package easy.common;

/**
 * @author limengyu
 * @create 2017/10/30
 */
public class Constant {

    public static enum TRANSLATE_TYPE {
        GOOGLE(1, "谷歌"),
        BAI_DU(2, "百度"),
        JIN_SHAN(3, "金山"),
        YOU_DAO(4, "有道"),
        TENCENT(5, "腾讯"),
        OMI(6, "欧米");

        private int code;
        private String name;

        private TRANSLATE_TYPE(int code, String name) {
            this.code = code;
            this.name = name;
        }
        public int getCode() {
            return code;
        }
        public String getName() {
            return name;
        }
    }

    /**
     * 按照Code和类型获取名称
     * @param code
     * @return
     */
    public static String getNameByCode(int code) {
        TRANSLATE_TYPE[] values = TRANSLATE_TYPE.values();
        for (TRANSLATE_TYPE value : values) {
            if (value.getCode() == code) {
                return value.getName();
            }
        }
        return Constant.TRANSLATE_TYPE.GOOGLE.getName();
    }
}
