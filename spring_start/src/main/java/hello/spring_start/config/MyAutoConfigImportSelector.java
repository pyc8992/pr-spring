package hello.spring_start.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigs = new ArrayList<>();
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);
//        return new String[] {
//                "hello.spring_start.config.autoconfig.DispatcherServletConfig",
//                "hello.spring_start.config.autoconfig.TomcatWebServerConfig"
//        };

//        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);
        return autoConfigs.toArray(new String[0]);
    }
}
