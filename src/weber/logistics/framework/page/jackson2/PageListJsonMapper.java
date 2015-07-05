package weber.logistics.framework.page.jackson2;

import weber.logistics.framework.page.domain.JsonPage;
import weber.logistics.framework.page.domain.PageList;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author miemiedev
 */
public class PageListJsonMapper extends ObjectMapper {

	public PageListJsonMapper() {
		//super();//weber
		SimpleModule module = new SimpleModule("PageListJSONModule",
				new Version(1, 0, 0, null, null, null));
		module.addSerializer(JsonPage.class, new PageListJsonSerializer(this));
		registerModule(module);
	}
}
