package sg.xueyu.efamily.config;

import sg.xueyu.zebra.annotation.Configuration;
import sg.xueyu.zebra.annotation.config.PackageScan;
import sg.xueyu.zebra.annotation.config.URLPatternSuffix;
import sg.xueyu.zebra.annotation.config.ViewPrefix;
import sg.xueyu.zebra.annotation.config.ViewSuffix;

@Configuration
@PackageScan("sg.xueyu.efamily")
@ViewPrefix("/WEB-INF/view/")
@ViewSuffix(".jsp")
@URLPatternSuffix(".do")
public class AppConfig {

}
