package lab4.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.Map;

public abstract class AbstractFileFactory {
    private final Settings settings;
    private final Configuration fmConfig;

    public AbstractFileFactory(Settings settings) {
        this.settings = settings;
        Util.ensureDirectory(settings.getTargetCodeDirPath());
        fmConfig = new Configuration(Configuration.VERSION_2_3_31);
        fmConfig.setDefaultEncoding("UTF-8");
        fmConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        fmConfig.setFallbackOnNullLoopVariable(false);
        fmConfig.setClassForTemplateLoading(getClass(), "/");
    }

    protected void writeFiles(Map<String, Object> map) {
        for (String templateName : getTemplateNames()) {
            Template template;
            try {
                template = fmConfig().getTemplate(templateName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Path targetPath = settings().getTargetCodeDirPath().resolve(Util.removeExt(templateName));
            try (Writer writer = new BufferedWriter(new FileWriter(targetPath.toFile()))) {
                template.process(map, writer);
            } catch (IOException | TemplateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected Configuration fmConfig() {
        return fmConfig;
    }

    protected Settings settings() {
        return settings;
    }

    protected abstract String[] getTemplateNames();
}
