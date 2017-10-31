package easy.config;

import easy.common.Constant;
import org.jdom.Element;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;

/**
 * @author limengyu
 * @create 2017/10/30
 */
@State(name = "TranslationSetting", storages = { @Storage(id = "other", file = "$APP_CONFIG$/translationSetting.xml") })
public class TranslationSetting implements PersistentStateComponent<Element> {
	private String engine;

	public TranslationSetting() {
	}
	public static TranslationSetting getInstance() {
		return ServiceManager.getService(TranslationSetting.class);
	}
	@Nullable
	@Override
	public Element getState() {
		Element element = new Element("TranslationSetting");
		element.setAttribute("engine", this.getEngine());
		return element;
	}
	@Override
	public void loadState(Element element) {
		this.setEngine(element.getAttributeValue("engine"));
	}
	public String getEngine() {
		return this.engine == null ? Constant.TRANSLATE_TYPE.GOOGLE.getName() : this.engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
}
