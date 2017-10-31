package easy.trans.impl;

import easy.http.HttpParams;
import easy.http.HttpPostParams;
import easy.trans.AbstractOnlineTranslator;
import easy.trans.LANG;
import easy.trans.annotation.TranslatorComponent;
import net.sf.json.JSONObject;

/**
 * @author limengyu
 * @create 2017/10/30
 */
@TranslatorComponent(id = "谷歌")
final public class GoogleTranslation extends AbstractOnlineTranslator {
	public GoogleTranslation() {
		langMap.put(LANG.EN, "en");
		langMap.put(LANG.ZH, "zh");
	}
	@Override
	protected String getResponse(LANG from, LANG targ, String query) throws Exception {
		HttpParams params = new HttpPostParams().put("f", langMap.get(from)).put("t", langMap.get(targ)).put("text", query);
		return params.send2String("http://translate.hotcn.top/translate/api");
	}
	@Override
	protected String parseString(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		String result = jsonObject.getString("text");
		return result;
	}
}
