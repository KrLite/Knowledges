package net.krlite.knowledges.config.disabled;

import net.krlite.knowledges.Knowledges;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDisabledConfig<T> {
    private final File file;
    protected final List<String> disabled;

    protected AbstractDisabledConfig(String fileName) {
        this.disabled = new ArrayList<>();
        this.file = Knowledges.CONFIG_PATH.resolve(fileName + ".txt").toFile();
        load();
    }

    protected void load() {
        if (file.exists()) {
            try {
                disabled.clear();
                disabled.addAll(FileUtils.readLines(file, Charset.defaultCharset()));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else {
            file.getParentFile().mkdirs();
            save();
        }
    }

    protected void save() {
        try {
            FileUtils.writeLines(file, disabled);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    protected boolean get(String key) {
        return disabled.contains(key);
    }

    public abstract boolean get(T t);

    protected void set(String key, boolean flag) {
        if (flag) {
            if (!disabled.contains(key)) {
                disabled.add(key);
                save();
            }
        } else {
            if (disabled.contains(key)) {
                disabled.remove(key);
                save();
            }
        }
    }

    public abstract void set(T t, boolean flag);
}
