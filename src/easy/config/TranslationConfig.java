package easy.config;

import javax.swing.*;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.Disposer;

import easy.form.TranslationForm;

/**
 * @author limengyu
 * @create 2017/10/30
 */
public class TranslationConfig implements SearchableConfigurable, Configurable.NoScroll, Disposable {

    private TranslationForm translationForm;
    private TranslationSetting translationSetting = TranslationSetting.getInstance();

    @NotNull
    @Override
    public String getId() {
        return "TransConfig";
    }

    @Nullable
    @Override
    public Runnable enableSearch(String s) {
        return null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "翻译配置";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return this.getId();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if(null == this.translationForm) {
            this.translationForm = new TranslationForm();
        }
        return this.translationForm.mainPanel;
    }

    @Override
    public boolean isModified() {
        return !this.translationSetting.getEngine().equals(this.translationForm.getSelectValue());
    }

    @Override
    public void apply() throws ConfigurationException {
        this.translationSetting.setEngine(this.translationForm.getSelectValue());
    }

    @Override
    public void reset() {
        this.translationForm.google.setSelected(true);
    }

    @Override
    public void disposeUIResources() {
        Disposer.dispose(this);
    }

    @Override
    public void dispose() {
        translationForm = null;
    }
}
