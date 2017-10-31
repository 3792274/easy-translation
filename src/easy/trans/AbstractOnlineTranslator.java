package easy.trans;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractOnlineTranslator implements easy.trans.Translator {
	protected Map<easy.trans.LANG, String> langMap = new HashMap<>(); // 语言映射，由子类完成

	@Override
	final public String trans(easy.trans.LANG from, easy.trans.LANG targ, String query) throws Exception {
		String response = "";
		try {
			response = getResponse(from, targ, query);
			String result = parseString(response);
			return result;
		} catch (Exception e) {
			return response;
		}
	}
	abstract protected String getResponse(easy.trans.LANG from, LANG targ, String query) throws Exception;
	abstract protected String parseString(String jsonString);
}
