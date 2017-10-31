package easy.trans.impl;

import easy.http.HttpParams;
import easy.http.HttpPostParams;
import easy.trans.AbstractOnlineTranslator;
import easy.trans.LANG;
import easy.trans.annotation.TranslatorComponent;
import net.sf.json.JSONObject;

@TranslatorComponent(id = "金山")
final public class JinshanTranslator extends AbstractOnlineTranslator {
	public JinshanTranslator() {
		langMap.put(LANG.EN, "en");
		langMap.put(LANG.ZH, "zh");
	}
	@Override
	protected String getResponse(LANG from, LANG targ, String query) throws Exception {
		HttpParams params = new HttpPostParams().put("f", langMap.get(from)).put("t", langMap.get(targ)).put("w", query);
		return params.send2String("http://fy.iciba.com/ajax.php?a=fy");
	}
	@Override
	protected String parseString(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		String result = jsonObject.getJSONObject("content").getString("out");
		return result;
	}
}
