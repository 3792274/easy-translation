package easy.trans.impl;

import easy.http.HttpParams;
import easy.http.HttpPostParams;
import easy.trans.AbstractOnlineTranslator;
import easy.trans.LANG;
import easy.trans.annotation.TranslatorComponent;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@TranslatorComponent(id = "百度")
final public class BaiduTranslator extends AbstractOnlineTranslator {
	public BaiduTranslator() {
		langMap.put(LANG.EN, "en");
		langMap.put(LANG.ZH, "zh");
	}
	@Override
	public String getResponse(LANG from, LANG targ, String query) throws Exception {
		HttpParams params = new HttpPostParams().put("from", langMap.get(from)).put("to", langMap.get(targ)).put("query", query).put("transtype", "translang").put("simple_means_flag", "3");
		return params.send2String("http://fanyi.baidu.com/v2transapi");
	}
	@Override
	protected String parseString(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray segments = jsonObject.getJSONObject("trans_result").getJSONArray("data");
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < segments.size(); i++) {
			result.append(i == 0 ? "" : "\n");
			result.append(segments.getJSONObject(i).getString("dst"));
		}
		return new String(result);
	}
}
