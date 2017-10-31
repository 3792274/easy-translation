package easy.idea;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import easy.common.Constant;
import easy.config.TranslationSetting;
import easy.trans.LANG;
import easy.trans.factory.TFactory;
import easy.trans.factory.TranslatorFactory;

/**
 * 中文转英文
 * @author Administrator
 */
public class ToEnglishAction extends AnAction {
	private TranslationSetting translationSetting;

	@Override
	public void actionPerformed(AnActionEvent event) {
		this.translationSetting = TranslationSetting.getInstance();
		Project project = event.getData(PlatformDataKeys.PROJECT);
		String chineseName = Messages.showInputDialog(project, "想翻译成英文？", "中文转英文", Messages.getQuestionIcon());
		String engine = this.getEngine();
		String title = engine;
		boolean isGoogle = this.checkGoogleEngine(engine);
		if (isGoogle) {
			engine = Constant.TRANSLATE_TYPE.BAI_DU.getName();
			title = "谷歌不支持英->中,切换到" + engine;
		}
		String englishName = transaction(engine, chineseName);
		Messages.showMessageDialog(project, englishName, title, Messages.getInformationIcon());
	}
	public String transaction(String engine, String text) {
		try {
			TFactory factory = new TranslatorFactory();
			String result = factory.get(engine).trans(LANG.ZH, LANG.EN, text);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "翻译失败,换个引擎试试";
	}
	/**
	 * google不支持英文->中文
	 * @param engine
	 * @return
	 */
	private boolean checkGoogleEngine(String engine) {
		if (Constant.TRANSLATE_TYPE.GOOGLE.getName().equals(engine)) {
			return true;
		} else {
			return false;
		}
	}
	public String getEngine() {
		return this.translationSetting.getEngine();
	}
}
