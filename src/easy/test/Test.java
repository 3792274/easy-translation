package easy.test;

import easy.common.Constant;
import easy.trans.LANG;
import easy.trans.factory.TFactory;
import easy.trans.factory.TranslatorFactory;

/**
 * @author limengyu
 * @create 2017/10/30
 */
public class Test {
	public static void main(String[] args) throws Exception {
		String text = "The default method implementations (inherited or otherwise) do not apply any synchronization protocol.  If a {@code Collection} implementation has a specific synchronization protocol, then it must override default implementations to apply that protocol.";
		TFactory factory = new TranslatorFactory();
//		String result = factory.get("金山").trans(LANG.EN, LANG.ZH, text);

		//中文---->英文
		text = "编程语言";
		String jinshan = factory.get("金山").trans(LANG.ZH,LANG.EN,text);
		String baidu = factory.get("百度").trans(LANG.ZH,LANG.EN,text);
		String tengxun = factory.get("腾讯").trans(LANG.ZH,LANG.EN,text);
		String omi = factory.get("欧米").trans(LANG.ZH,LANG.EN,text);
		System.out.println("jinshan: " + jinshan);
		System.out.println("baidu: " + baidu);
		System.out.println("tengxun: " + tengxun);
		System.out.println("omi: " + omi);
	}
}
