package easy.idea;

import java.awt.*;

import org.apache.commons.lang.StringUtils;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;

import easy.config.TranslationSetting;
import easy.trans.LANG;
import easy.trans.factory.TFactory;
import easy.trans.factory.TranslatorFactory;

/**
 * @author limengyu
 * @create 2017/10/30
 */
public class ToChineseAction extends AnAction {
	private TranslationSetting translationSetting;

	@Override
	public void actionPerformed(AnActionEvent event) {
		this.translationSetting = TranslationSetting.getInstance();
		Editor editor = event.getData(PlatformDataKeys.EDITOR);
		boolean hasSelection = editor.getSelectionModel().hasSelection();
		if (hasSelection) {
			String text = editor.getSelectionModel().getSelectedText();
			if (StringUtils.isNotBlank(text)) {
				String engine = getEngine();
				System.out.println("翻译引擎: " + engine);
				String result = this.transaction(engine, text);
				showPopupBalloon(editor, result, engine);
			}
		}
	}
	public String transaction(String engine, String text) {
		try {
			TFactory factory = new TranslatorFactory();
			String result = factory.get(engine).trans(LANG.EN, LANG.ZH, text);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "翻译失败,换个引擎试试";
	}
	public String getEngine() {
		return this.translationSetting.getEngine();
	}
	private void showPopupBalloon(final Editor editor, final String result, String engine) {
		ApplicationManager.getApplication().invokeLater(() -> {
			JBPopupFactory factory = JBPopupFactory.getInstance();
			factory.createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(186, 238, 186), new Color(73, 117, 73)), null)
					.setFadeoutTime(4000)
					.setTitle("由" + engine + "翻译")
					.createBalloon()
					.show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
		});
	}
}
